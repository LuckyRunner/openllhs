package com.openllhs.article.dao;
import com.openllhs.article.pojo.Article;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:duqiang
 * @Description:Article的Dao
 * @Date 2020/5/12 0:12
 *****/
@org.apache.ibatis.annotations.Mapper
public interface ArticleMapper extends Mapper<Article> {
}
