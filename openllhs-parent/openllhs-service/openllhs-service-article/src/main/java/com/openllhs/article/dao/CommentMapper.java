package com.openllhs.article.dao;
import com.openllhs.article.pojo.Comment;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:duqiang
 * @Description:Comment的Dao
 * @Date 2020/5/12 0:12
 *****/
@org.apache.ibatis.annotations.Mapper
public interface CommentMapper extends Mapper<Comment> {
}
