package com.openllhs.article.service;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Subject;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:Subject业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface SubjectService {

    /***
     * Subject多条件分页查询
     * @param subject
     * @param page
     * @param size
     * @return
     */
    PageInfo<Subject> findPage(Subject subject, int page, int size);

    /***
     * Subject分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Subject> findPage(int page, int size);

    /***
     * Subject多条件搜索方法
     * @param subject
     * @return
     */
    List<Subject> findList(Subject subject);

    /***
     * 删除Subject
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Subject数据
     * @param subject
     */
    void update(Subject subject);

    /***
     * 新增Subject
     * @param subject
     */
    void add(Subject subject);

    /**
     * 根据ID查询Subject
     * @param id
     * @return
     */
     Subject findById(Long id);

    /***
     * 查询所有Subject
     * @return
     */
    List<Subject> findAll();
}
