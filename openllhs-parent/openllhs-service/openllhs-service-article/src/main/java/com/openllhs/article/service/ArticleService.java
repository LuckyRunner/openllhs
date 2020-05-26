package com.openllhs.article.service;
import com.openllhs.article.pojo.Article;
import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.ArticleVO;

import java.util.List;
/****
 * @Author:duqiang
 * @Description:Article业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface ArticleService {

    void saveArticle(ArticleVO articleVO);

    ArticleVO findByArticleId(Long articleId);

    List<Article> findByStatus(String status);

    void audit(Long articleId);

    void pull(Long articleId);

    void put(Long articleId);

    void putMany(Long[] ids);
    /***
     * Article多条件分页查询
     * @param article
     * @param page
     * @param size
     * @return
     */
    PageInfo<Article> findPage(Article article, int page, int size);

    /***
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Article> findPage(int page, int size);

    /***
     * Article多条件搜索方法
     * @param article
     * @return
     */
    List<Article> findList(Article article);

    /***
     * 删除Article
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Article数据
     * @param article
     */
    void update(Article article);

    /***
     * 新增Article
     * @param article
     */
    void add(Article article);

    /**
     * 根据ID查询Article
     * @param id
     * @return
     */
     Article findById(Long id);

    /***
     * 查询所有Article
     * @return
     */
    List<Article> findAll();
}
