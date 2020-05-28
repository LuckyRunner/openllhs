package com.openllhs.search.service.Impl;

import com.alibaba.fastjson.JSON;

import com.openllhs.article.feign.ArticleFeign;
import com.openllhs.article.pojo.Article;
import com.openllhs.search.dao.ArticleEsMapper;
import com.openllhs.search.pojo.ArticleInfo;
import com.openllhs.search.service.ArticleService;
import entity.Result;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleFeign articleFeign;

    @Autowired
    private ArticleEsMapper articleEsMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Map<String, Object> search(Map<String, String> searchMap) throws Exception{

        NativeSearchQueryBuilder nativeSearchQueryBuilder = buildBasicQuery(searchMap);
        Map<String, Object> resultMap = searchList(nativeSearchQueryBuilder);
        Map<String, Object> groupMap = searchGroupList(nativeSearchQueryBuilder,searchMap);

        resultMap.putAll(groupMap);
        return resultMap;
    }

    private Map searchList(NativeSearchQueryBuilder builder){

        AggregatedPage<ArticleInfo> page = elasticsearchTemplate.queryForPage(builder.build(), ArticleInfo.class);
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<ArticleInfo> contents = page.getContent();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows",contents);
        resultMap.put("total",totalElements);
        resultMap.put("totalPages",totalPages);
        return resultMap;
    }

    public Map<String, Object> searchGroupList(NativeSearchQueryBuilder nativeSearchQueryBuilder,Map<String, String> searchMap){

        Map<String, Object> groupMapResult = new HashMap<String, Object>();
        if(searchMap==null || StringUtils.isEmpty(searchMap.get("categoryName1"))){
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("categoryName1").field("categoryName1"));
        }
        if(searchMap==null || StringUtils.isEmpty(searchMap.get("subject"))){
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("subject").field("subject"));
        }

        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("authors").field("author.keyword"));

        AggregatedPage<ArticleInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), ArticleInfo.class);

        if(searchMap==null || StringUtils.isEmpty(searchMap.get("categoryName1"))){
            StringTerms categoryTerms = aggregatedPage.getAggregations().get("categoryName1");
            List<String> categoryList = getGroupList(categoryTerms);
            groupMapResult.put("categoryList",categoryList);
        }
        if(searchMap==null || StringUtils.isEmpty(searchMap.get("subject"))){
            StringTerms subjectTerms = aggregatedPage.getAggregations().get("subject");
            List<String> subjectList = getGroupList(subjectTerms);
            groupMapResult.put("subjectList",subjectList);
        }

        StringTerms authorTerms = aggregatedPage.getAggregations().get("authorMap");
        List<String> authorList = getGroupList(authorTerms);
        groupMapResult.put("authorList",authorList);

        return groupMapResult;

    }
    public List<String> getGroupList(StringTerms stringTerms){
        List<String> groupList = new ArrayList<String>();
        if(stringTerms!= null){
            for(StringTerms.Bucket bucket : stringTerms.getBuckets()){
                String feildName = bucket.getKeyAsString();
                groupList.add(feildName);
            }
        }
        return groupList;
    }


    public NativeSearchQueryBuilder buildBasicQuery(Map<String, String> searchMap){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(searchMap!=null && searchMap.size()>0){
            String keywords = searchMap.get("keywords");
            if(!StringUtils.isEmpty(keywords)){
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("title"));
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("aabstract"));
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("keyWords"));
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("authorMap"));
            }

            for(Map.Entry<String, String> entry : searchMap.entrySet()){
                String key = entry.getKey();
                if(key.startsWith("author_")){
                    String value = entry.getValue();
                    boolQueryBuilder.must(QueryBuilders.termQuery("authorMap."+key.substring(7)+".keyword",value));
                }

            }
        }

        //开启分页查询
        String pageNum = searchMap.get("pageNum"); //当前页
        String pageSize = searchMap.get("pageSize"); //每页显示多少条
        if (StringUtils.isEmpty(pageNum)){
            pageNum ="1";
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize="10";
        }
        nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.parseInt(pageNum)-1,Integer.parseInt(pageSize)));

        if (StringUtils.isNotEmpty(searchMap.get("sortField")) && StringUtils.isNotEmpty(searchMap.get("sortRule"))){
            if ("ASC".equals(searchMap.get("sortRule"))){
                //升序
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort((searchMap.get("sortField"))).order(SortOrder.ASC));
            }else{
                //降序
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort((searchMap.get("sortField"))).order(SortOrder.DESC));
            }
        }

        //设置高亮域以及高亮的样式
        HighlightBuilder.Field field = new HighlightBuilder.Field("title")//高亮域
                .preTags("<span style='color:red'>")//高亮样式的前缀
                .postTags("</span>");//高亮样式的后缀
        nativeSearchQueryBuilder.withHighlightFields(field);

        AggregatedPage<ArticleInfo> resultInfo = elasticsearchTemplate.
                queryForPage(
                        nativeSearchQueryBuilder.build(),
                        ArticleInfo.class,
                        new SearchResultMapper() {
                            @Override
                            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                                List<T> list = new ArrayList<>();

                                for(SearchHit hit : searchResponse.getHits()){
                                    ArticleInfo articleInfo = JSON.parseObject(hit.getSourceAsString(),ArticleInfo.class);
                                    HighlightField highlightField = hit.getHighlightFields().get("title");
                                    if(highlightField!=null&&highlightField.getFragments()!=null){
                                        Text[] fragments = highlightField.getFragments();
                                        StringBuffer buffer = new StringBuffer();
                                        for(Text fragment : fragments) {
                                            buffer.append(fragment.toString());
                                        }

                                        articleInfo.setTitle(buffer.toString());
                                    }
                                    list.add((T)articleInfo);
                                };
                                return new AggregatedPageImpl<T>(list,pageable,searchResponse.getHits().getTotalHits(),searchResponse.getAggregations());
                            }

                            @Override
                            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                                return null;
                            }
                        }
                );
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return nativeSearchQueryBuilder;
    }







    @Override
    public void importData() {
        //应该尽量使用分页查询,分批导入数据
        Result<List<Article>> articleResult =  articleFeign.findByStatus("1");
        List<ArticleInfo> articleInfoList = JSON.parseArray(JSON.toJSONString(articleResult.getData()),ArticleInfo.class);

        for(ArticleInfo articleInfo : articleInfoList){
            Map authorMap = JSON.parseObject(articleInfo.getAuthors(), Map.class);
            articleInfo.setAuthorMap(authorMap);
        }
        articleEsMapper.saveAll(articleInfoList);
    }
}
