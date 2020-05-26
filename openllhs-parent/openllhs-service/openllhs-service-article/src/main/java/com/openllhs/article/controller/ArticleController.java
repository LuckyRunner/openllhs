package com.openllhs.article.controller;
import com.openllhs.article.pojo.Article;
import com.openllhs.article.pojo.ArticleVO;
import com.openllhs.article.service.ArticleService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:duqiang
 * @Description:
 * @Date 2020/5/12 0:18
 *****/

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PutMapping(value = "/audit/{id}")
    public Result audit(@PathVariable(value = "id")Long articleId){
        articleService.audit(articleId);
        return new Result(true,StatusCode.OK,"审核通过");
    }

    @PutMapping(value = "/pull/{id}")
    public Result pull(@PathVariable(value = "id")Long articleId){
        articleService.pull(articleId);
        return new Result(true,StatusCode.OK,"下架成功");
    }

    @PutMapping(value = "/put/{id}")
    public Result put(@PathVariable(value = "id")Long articleId){
        articleService.put(articleId);
        return new Result(true,StatusCode.OK,"上架成功");
    }

    @PutMapping(value = "/puts/{id}")
    public Result putMany(@PathVariable(value = "id")Long[] ids){
        articleService.putMany(ids);
        return new Result(true,StatusCode.OK,"上架成功");
    }

    @GetMapping(value = "/article/{id}")
    public Result<ArticleVO> findArticleById(@PathVariable(value = "id")Long id){
        ArticleVO articleVO = articleService.findByArticleId(id);
        return new Result<ArticleVO>(true,StatusCode.OK,"查询成功",articleVO);
    }

    @PostMapping("/save")
    public Result saveArticle(@RequestBody ArticleVO articleVO){
        articleService.saveArticle(articleVO);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @GetMapping("/status/{status}")
    public Result<List<Article>> findByStatus(@PathVariable String status){
        List<Article> list = articleService.findByStatus(status);
        return new Result<List<Article>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * Article分页条件搜索实现
     * @param article
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Article article, @PathVariable  int page, @PathVariable  int size){
        //调用ArticleService实现分页条件查询Article
        PageInfo<Article> pageInfo = articleService.findPage(article, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Article分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ArticleService实现分页查询Article
        PageInfo<Article> pageInfo = articleService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param article
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Article>> findList(@RequestBody(required = false)  Article article){
        //调用ArticleService实现条件查询Article
        List<Article> list = articleService.findList(article);
        return new Result<List<Article>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用ArticleService实现根据主键删除
        articleService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Article数据
     * @param article
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Article article,@PathVariable Long id){
        //设置主键值
        article.setId(id);
        //调用ArticleService实现修改Article
        articleService.update(article);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Article数据
     * @param article
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Article article){
        //调用ArticleService实现添加Article
        articleService.add(article);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Article数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Article> findById(@PathVariable Long id){
        //调用ArticleService实现根据主键查询Article
        Article article = articleService.findById(id);
        return new Result<Article>(true,StatusCode.OK,"查询成功",article);
    }

    /***
     * 查询Article全部数据
     * @return
     */
    @GetMapping
    public Result<List<Article>> findAll(){
        //调用ArticleService实现查询所有Article
        List<Article> list = articleService.findAll();
        return new Result<List<Article>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
