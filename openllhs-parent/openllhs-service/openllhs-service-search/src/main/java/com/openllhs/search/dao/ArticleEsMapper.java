package com.openllhs.search.dao;

import com.openllhs.search.pojo.ArticleInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleEsMapper extends ElasticsearchRepository<ArticleInfo,Long> {
}
