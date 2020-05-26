package com.openllhs.article.service;
import com.openllhs.article.pojo.Belong;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Belong业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface BelongService {

    /***
     * Belong多条件分页查询
     * @param belong
     * @param page
     * @param size
     * @return
     */
    PageInfo<Belong> findPage(Belong belong, int page, int size);

    /***
     * Belong分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Belong> findPage(int page, int size);

    /***
     * Belong多条件搜索方法
     * @param belong
     * @return
     */
    List<Belong> findList(Belong belong);

    /***
     * 删除Belong
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Belong数据
     * @param belong
     */
    void update(Belong belong);

    /***
     * 新增Belong
     * @param belong
     */
    void add(Belong belong);

    /**
     * 根据ID查询Belong
     * @param id
     * @return
     */
     Belong findById(Long id);

    /***
     * 查询所有Belong
     * @return
     */
    List<Belong> findAll();
}
