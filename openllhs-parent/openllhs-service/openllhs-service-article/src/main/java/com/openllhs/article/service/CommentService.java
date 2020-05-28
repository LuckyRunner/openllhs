package com.openllhs.article.service;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Comment;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:Comment业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface CommentService {

    /***
     * Comment多条件分页查询
     * @param comment
     * @param page
     * @param size
     * @return
     */
    PageInfo<Comment> findPage(Comment comment, int page, int size);

    /***
     * Comment分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Comment> findPage(int page, int size);

    /***
     * Comment多条件搜索方法
     * @param comment
     * @return
     */
    List<Comment> findList(Comment comment);

    /***
     * 删除Comment
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Comment数据
     * @param comment
     */
    void update(Comment comment);

    /***
     * 新增Comment
     * @param comment
     */
    void add(Comment comment);

    /**
     * 根据ID查询Comment
     * @param id
     * @return
     */
     Comment findById(Long id);

    /***
     * 查询所有Comment
     * @return
     */
    List<Comment> findAll();
}
