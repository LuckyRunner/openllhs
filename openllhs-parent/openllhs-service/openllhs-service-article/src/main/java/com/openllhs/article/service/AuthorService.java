package com.openllhs.article.service;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Author;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:Author业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface AuthorService {

    /***
     * Author多条件分页查询
     * @param author
     * @param page
     * @param size
     * @return
     */
    PageInfo<Author> findPage(Author author, int page, int size);

    /***
     * Author分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Author> findPage(int page, int size);

    /***
     * Author多条件搜索方法
     * @param author
     * @return
     */
    List<Author> findList(Author author);

    /***
     * 删除Author
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Author数据
     * @param author
     */
    void update(Author author);

    /***
     * 新增Author
     * @param author
     */
    void add(Author author);

    /**
     * 根据ID查询Author
     * @param id
     * @return
     */
     Author findById(Long id);

    /***
     * 查询所有Author
     * @return
     */
    List<Author> findAll();
}
