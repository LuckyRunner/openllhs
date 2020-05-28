package com.openllhs.article.service.impl;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openllhs.article.dao.ArticleMapper;
import com.openllhs.article.dao.AuthorMapper;
import com.openllhs.article.dao.OrganizationMapper;
import com.openllhs.article.pojo.*;
import com.openllhs.article.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.IdWorker;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.beans.Expression;
import java.util.*;

/****
 * @Author:duqiang
 * @Description:Article业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private IdWorker idWorker;

    public String impo() throws Exception{
        List<Article> articleList = articleMapper.selectAll();

        for (Article each :
                articleList) {
            Author author = new Author();
            author.setArticleId(each.getId());
            Organization organization = new Organization();
            organization.setArticleId(each.getId());


            List<Author> authorList = authorMapper.select(author);
            List<Organization> organizationList = organizationMapper.select(organization);


            Map<String, Object> authorMap = new HashMap<String, Object>();
            Map<String, Object> organizationMap = new HashMap<String, Object>();
            authorMap.put("authorList",authorList);
            organizationMap.put("organizationList",organizationList);
            ObjectMapper json = new ObjectMapper();
            String authors = json.writeValueAsString(authorMap);
            String organizations = json.writeValueAsString(organizationMap);

            each.setAuthors(authors);
            each.setOrganizations(organizations);


            articleMapper.updateByPrimaryKeySelective(each);
        }
        return "导入成功";
    }


    @Override
    public void saveArticle(ArticleVO articleVO) {

        List<Author> authorList = articleVO.getAuthors();
        List<Organization> organizationList = articleVO.getOrganizations();

        Article article = articleVO.getArticle();
        if(article.getId()==null){
            article.setId(idWorker.nextId());
            article.setVersion(1);
            article.setStatus("1");
            article.setAuthors(authorList.toString());
            article.setAuthors(organizationList.toString());

            article.setCreateTime(new Date());
            articleMapper.insertSelective(article);

        }else {
            Organization organization = new Organization();
            Author author = new Author();
            organization.setArticleId(article.getId());
            author.setArticleId(article.getId());
            authorMapper.delete(author);
            organizationMapper.delete(organization);

            article.setUpdateTime(new Date());
            article.setVersion(article.getVersion()+1);
            article.setAuthors(authorList.toString());
            article.setAuthors(organizationList.toString());
            articleMapper.updateByPrimaryKeySelective(article);
        }
        for(Organization each : organizationList){
            each.setId(idWorker.nextId());
            organizationMapper.insertSelective(each);
        }
        for(Author each : authorList){
            each.setId(idWorker.nextId());
            authorMapper.insertSelective(each);
        }

    }

    @Override
    public ArticleVO findByArticleId(Long id) {


        ArticleVO articleVO = new ArticleVO();

        Article article = articleMapper.selectByPrimaryKey(id);
        articleVO.setArticle(article);

        Author author = new Author();
        author.setArticleId(id);
        List<Author> authorList = authorMapper.select(author);
        articleVO.setAuthors(authorList);

        Organization organization = new Organization();
        organization.setArticleId(id);
        List<Organization> organizationList = new ArrayList<>();
        articleVO.setOrganizations(organizationList);

        return articleVO;
    }

    @Override
    public List<Article> findByStatus(String status) {
        Article article = new Article();
        article.setStatus(status);
        return articleMapper.select(article);
    }

    @Override
    public void audit(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对已删除的商品进行审核！");
        }
        article.setStatus("1");
        article.setIsShow("1");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void pull(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对已删除的商品进行下架！");
        }

        article.setIsShow("0");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void put(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对已删除的商品进行下架！");
        }
        if(!article.getStatus().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对未审核商品进行上架！");
        }
        article.setIsShow("1");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void putMany(Long[] ids) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        criteria.andEqualTo("isDelete","0");
        criteria.andEqualTo("status","1");
        Article article =new Article();
        article.setIsShow("1");
        articleMapper.updateByExampleSelective(article,example);
    }

    /**
     * Article条件+分页查询
     * @param article 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Article> findPage(Article article, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(article);
        //执行搜索
        return new PageInfo<Article>(articleMapper.selectByExample(example));
    }

    /**
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Article> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Article>(articleMapper.selectAll());
    }

    /**
     * Article条件查询
     * @param article
     * @return
     */
    @Override
    public List<Article> findList(Article article){
        //构建查询条件
        Example example = createExample(article);
        //根据构建的条件查询数据
        return articleMapper.selectByExample(example);
    }


    /**
     * Article构建查询对象
     * @param article
     * @return
     */
    public Example createExample(Article article){
        Example example=new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        if(article!=null){
            // 论文id
            if(!StringUtils.isEmpty(article.getId())){
                criteria.andEqualTo("id",article.getId());
            }
            // doi
            if(!StringUtils.isEmpty(article.getDoi())){
                criteria.andEqualTo("doi",article.getDoi());
            }
            // 标题
            if(!StringUtils.isEmpty(article.getTitle())){
                criteria.andLike("title","%"+article.getTitle()+"%");
            }
            // 摘要
            if(!StringUtils.isEmpty(article.getAAbstract())){
                criteria.andEqualTo("aAbstract",article.getAAbstract());
            }
            // 论文内容
            if(!StringUtils.isEmpty(article.getContent())){
                criteria.andEqualTo("content",article.getContent());
            }
            // 关键字
            if(!StringUtils.isEmpty(article.getKeyWords())){
                criteria.andEqualTo("keyWords",article.getKeyWords());
            }
            // 论文首图片
            if(!StringUtils.isEmpty(article.getImage())){
                criteria.andEqualTo("image",article.getImage());
            }
            // 论文内容图片列表
            if(!StringUtils.isEmpty(article.getImages())){
                criteria.andEqualTo("images",article.getImages());
            }
            // pdf
            if(!StringUtils.isEmpty(article.getPdf())){
                criteria.andEqualTo("pdf",article.getPdf());
            }
            // 以往版本pdf列表
            if(!StringUtils.isEmpty(article.getPdfs())){
                criteria.andEqualTo("pdfs",article.getPdfs());
            }
            // 创建时间
            if(!StringUtils.isEmpty(article.getCreateTime())){
                criteria.andEqualTo("createTime",article.getCreateTime());
            }
            // 更新时间
            if(!StringUtils.isEmpty(article.getUpdateTime())){
                criteria.andEqualTo("updateTime",article.getUpdateTime());
            }
            //
            if(!StringUtils.isEmpty(article.getSubmitTime())){
                criteria.andEqualTo("submitTime",article.getSubmitTime());
            }
            //
            if(!StringUtils.isEmpty(article.getPublishDate())){
                criteria.andEqualTo("publishDate",article.getPublishDate());
            }
            // 领域
            if(!StringUtils.isEmpty(article.getSubject())){
                criteria.andEqualTo("subject",article.getSubject());
            }
            // 期刊名
            if(!StringUtils.isEmpty(article.getJournal())){
                criteria.andEqualTo("journal",article.getJournal());
            }
            // 阅读数
            if(!StringUtils.isEmpty(article.getReadNum())){
                criteria.andEqualTo("readNum",article.getReadNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(article.getCommentNum())){
                criteria.andEqualTo("commentNum",article.getCommentNum());
            }
            // 下载数
            if(!StringUtils.isEmpty(article.getDownloadNum())){
                criteria.andEqualTo("downloadNum",article.getDownloadNum());
            }
            // 审核状态，0-未审核，1-已审核
            if(!StringUtils.isEmpty(article.getStatus())){
                criteria.andEqualTo("status",article.getStatus());
            }
            //
            if(!StringUtils.isEmpty(article.getVersion())){
                criteria.andEqualTo("version",article.getVersion());
            }
            // 是否删除，0-未删除
            if(!StringUtils.isEmpty(article.getIsDelete())){
                criteria.andEqualTo("isDelete",article.getIsDelete());
            }
            // 是否展示，0-未展示
            if(!StringUtils.isEmpty(article.getIsShow())){
                criteria.andEqualTo("isShow",article.getIsShow());
            }
            //
            if(!StringUtils.isEmpty(article.getAuthors())){
                criteria.andEqualTo("authors",article.getAuthors());
            }
            //
            if(!StringUtils.isEmpty(article.getOrganizations())){
                criteria.andEqualTo("organizations",article.getOrganizations());
            }
            //
            if(!StringUtils.isEmpty(article.getCategoryName1())){
                criteria.andEqualTo("categoryName1",article.getCategoryName1());
            }
            //
            if(!StringUtils.isEmpty(article.getCategoryName2())){
                criteria.andEqualTo("categoryName2",article.getCategoryName2());
            }
            //
            if(!StringUtils.isEmpty(article.getCategoryName3())){
                criteria.andEqualTo("categoryName3",article.getCategoryName3());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        articleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Article
     * @param article
     */
    @Override
    public void update(Article article){
        articleMapper.updateByPrimaryKey(article);
    }

    /**
     * 增加Article
     * @param article
     */
    @Override
    public void add(Article article){
        articleMapper.insert(article);
    }

    /**
     * 根据ID查询Article
     * @param id
     * @return
     */
    @Override
    public Article findById(Long id){
        return  articleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Article全部数据
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }
}
