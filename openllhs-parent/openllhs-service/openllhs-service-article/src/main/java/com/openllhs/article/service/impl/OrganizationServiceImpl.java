package com.openllhs.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.openllhs.article.dao.OrganizationMapper;
import com.openllhs.article.pojo.Organization;
import com.openllhs.article.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:Organization业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;


    /**
     * Organization条件+分页查询
     * @param organization 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Organization> findPage(Organization organization, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(organization);
        //执行搜索
        return new PageInfo<Organization>(organizationMapper.selectByExample(example));
    }

    /**
     * Organization分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Organization> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Organization>(organizationMapper.selectAll());
    }

    /**
     * Organization条件查询
     * @param organization
     * @return
     */
    @Override
    public List<Organization> findList(Organization organization){
        //构建查询条件
        Example example = createExample(organization);
        //根据构建的条件查询数据
        return organizationMapper.selectByExample(example);
    }


    /**
     * Organization构建查询对象
     * @param organization
     * @return
     */
    public Example createExample(Organization organization){
        Example example=new Example(Organization.class);
        Example.Criteria criteria = example.createCriteria();
        if(organization!=null){
            // id
            if(!StringUtils.isEmpty(organization.getId())){
                    criteria.andEqualTo("id",organization.getId());
            }
            // 单位名
            if(!StringUtils.isEmpty(organization.getName())){
                    criteria.andLike("name","%"+organization.getName()+"%");
            }
            //
            if(!StringUtils.isEmpty(organization.getSeq())){
                    criteria.andEqualTo("seq",organization.getSeq());
            }
            //
            if(!StringUtils.isEmpty(organization.getArticleId())){
                    criteria.andEqualTo("articleId",organization.getArticleId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        organizationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Organization
     * @param organization
     */
    @Override
    public void update(Organization organization){
        organizationMapper.updateByPrimaryKey(organization);
    }

    /**
     * 增加Organization
     * @param organization
     */
    @Override
    public void add(Organization organization){
        organizationMapper.insert(organization);
    }

    /**
     * 根据ID查询Organization
     * @param id
     * @return
     */
    @Override
    public Organization findById(Long id){
        return  organizationMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Organization全部数据
     * @return
     */
    @Override
    public List<Organization> findAll() {
        return organizationMapper.selectAll();
    }
}
