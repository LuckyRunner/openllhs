package com.openllhs.article.service;
import com.openllhs.article.pojo.Organization;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Organization业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface OrganizationService {

    /***
     * Organization多条件分页查询
     * @param organization
     * @param page
     * @param size
     * @return
     */
    PageInfo<Organization> findPage(Organization organization, int page, int size);

    /***
     * Organization分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Organization> findPage(int page, int size);

    /***
     * Organization多条件搜索方法
     * @param organization
     * @return
     */
    List<Organization> findList(Organization organization);

    /***
     * 删除Organization
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Organization数据
     * @param organization
     */
    void update(Organization organization);

    /***
     * 新增Organization
     * @param organization
     */
    void add(Organization organization);

    /**
     * 根据ID查询Organization
     * @param id
     * @return
     */
     Organization findById(Long id);

    /***
     * 查询所有Organization
     * @return
     */
    List<Organization> findAll();
}
