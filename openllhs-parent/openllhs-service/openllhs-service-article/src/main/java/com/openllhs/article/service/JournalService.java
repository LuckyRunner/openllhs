package com.openllhs.article.service;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Journal;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:Journal业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface JournalService {

    /***
     * Journal多条件分页查询
     * @param journal
     * @param page
     * @param size
     * @return
     */
    PageInfo<Journal> findPage(Journal journal, int page, int size);

    /***
     * Journal分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Journal> findPage(int page, int size);

    /***
     * Journal多条件搜索方法
     * @param journal
     * @return
     */
    List<Journal> findList(Journal journal);

    /***
     * 删除Journal
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Journal数据
     * @param journal
     */
    void update(Journal journal);

    /***
     * 新增Journal
     * @param journal
     */
    void add(Journal journal);

    /**
     * 根据ID查询Journal
     * @param id
     * @return
     */
     Journal findById(Long id);

    /***
     * 查询所有Journal
     * @return
     */
    List<Journal> findAll();
}
