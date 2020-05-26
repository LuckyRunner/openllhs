package com.openllhs.article.service;
import com.openllhs.article.pojo.Participation;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Participation业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface ParticipationService {

    /***
     * Participation多条件分页查询
     * @param participation
     * @param page
     * @param size
     * @return
     */
    PageInfo<Participation> findPage(Participation participation, int page, int size);

    /***
     * Participation分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Participation> findPage(int page, int size);

    /***
     * Participation多条件搜索方法
     * @param participation
     * @return
     */
    List<Participation> findList(Participation participation);

    /***
     * 删除Participation
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Participation数据
     * @param participation
     */
    void update(Participation participation);

    /***
     * 新增Participation
     * @param participation
     */
    void add(Participation participation);

    /**
     * 根据ID查询Participation
     * @param id
     * @return
     */
     Participation findById(Long id);

    /***
     * 查询所有Participation
     * @return
     */
    List<Participation> findAll();
}
