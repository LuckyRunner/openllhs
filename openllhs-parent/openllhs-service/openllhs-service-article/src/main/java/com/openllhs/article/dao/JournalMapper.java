package com.openllhs.article.dao;
import com.openllhs.article.pojo.Journal;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:duqiang
 * @Description:Journal的Dao
 * @Date 2020/5/12 0:12
 *****/
@org.apache.ibatis.annotations.Mapper
public interface JournalMapper extends Mapper<Journal> {
}
