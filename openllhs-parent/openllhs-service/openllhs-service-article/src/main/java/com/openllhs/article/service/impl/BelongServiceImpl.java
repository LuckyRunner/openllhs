package com.openllhs.article.service.impl;
import com.openllhs.article.dao.BelongMapper;
import com.openllhs.article.pojo.Belong;
import com.openllhs.article.service.BelongService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Belong业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class BelongServiceImpl implements BelongService {

    @Autowired
    private BelongMapper belongMapper;


    /**
     * Belong条件+分页查询
     * @param belong 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Belong> findPage(Belong belong, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(belong);
        //执行搜索
        return new PageInfo<Belong>(belongMapper.selectByExample(example));
    }

    /**
     * Belong分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Belong> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Belong>(belongMapper.selectAll());
    }

    /**
     * Belong条件查询
     * @param belong
     * @return
     */
    @Override
    public List<Belong> findList(Belong belong){
        //构建查询条件
        Example example = createExample(belong);
        //根据构建的条件查询数据
        return belongMapper.selectByExample(example);
    }


    /**
     * Belong构建查询对象
     * @param belong
     * @return
     */
    public Example createExample(Belong belong){
        Example example=new Example(Belong.class);
        Example.Criteria criteria = example.createCriteria();
        if(belong!=null){
            // 作者ID
            if(!StringUtils.isEmpty(belong.getAuthorId())){
                    criteria.andEqualTo("authorId",belong.getAuthorId());
            }
            // 单位ID
            if(!StringUtils.isEmpty(belong.getOrganizationId())){
                    criteria.andEqualTo("organizationId",belong.getOrganizationId());
            }
            // 排序
            if(!StringUtils.isEmpty(belong.getOrder())){
                    criteria.andEqualTo("order",belong.getOrder());
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
        belongMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Belong
     * @param belong
     */
    @Override
    public void update(Belong belong){
        belongMapper.updateByPrimaryKey(belong);
    }

    /**
     * 增加Belong
     * @param belong
     */
    @Override
    public void add(Belong belong){
        belongMapper.insert(belong);
    }

    /**
     * 根据ID查询Belong
     * @param id
     * @return
     */
    @Override
    public Belong findById(Long id){
        return  belongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Belong全部数据
     * @return
     */
    @Override
    public List<Belong> findAll() {
        return belongMapper.selectAll();
    }
}
