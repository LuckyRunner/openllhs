package com.openllhs.article.service.impl;
import com.openllhs.article.dao.CategorySubjectMapper;
import com.openllhs.article.pojo.CategorySubject;
import com.openllhs.article.service.CategorySubjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:CategorySubject业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class CategorySubjectServiceImpl implements CategorySubjectService {

    @Autowired
    private CategorySubjectMapper categorySubjectMapper;


    /**
     * CategorySubject条件+分页查询
     * @param categorySubject 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<CategorySubject> findPage(CategorySubject categorySubject, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(categorySubject);
        //执行搜索
        return new PageInfo<CategorySubject>(categorySubjectMapper.selectByExample(example));
    }

    /**
     * CategorySubject分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<CategorySubject> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<CategorySubject>(categorySubjectMapper.selectAll());
    }

    /**
     * CategorySubject条件查询
     * @param categorySubject
     * @return
     */
    @Override
    public List<CategorySubject> findList(CategorySubject categorySubject){
        //构建查询条件
        Example example = createExample(categorySubject);
        //根据构建的条件查询数据
        return categorySubjectMapper.selectByExample(example);
    }


    /**
     * CategorySubject构建查询对象
     * @param categorySubject
     * @return
     */
    public Example createExample(CategorySubject categorySubject){
        Example example=new Example(CategorySubject.class);
        Example.Criteria criteria = example.createCriteria();
        if(categorySubject!=null){
            // 分类ID
            if(!StringUtils.isEmpty(categorySubject.getCategoryId())){
                    criteria.andEqualTo("categoryId",categorySubject.getCategoryId());
            }
            // 领域ID
            if(!StringUtils.isEmpty(categorySubject.getSubjectId())){
                    criteria.andEqualTo("subjectId",categorySubject.getSubjectId());
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
        categorySubjectMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改CategorySubject
     * @param categorySubject
     */
    @Override
    public void update(CategorySubject categorySubject){
        categorySubjectMapper.updateByPrimaryKey(categorySubject);
    }

    /**
     * 增加CategorySubject
     * @param categorySubject
     */
    @Override
    public void add(CategorySubject categorySubject){
        categorySubjectMapper.insert(categorySubject);
    }

    /**
     * 根据ID查询CategorySubject
     * @param id
     * @return
     */
    @Override
    public CategorySubject findById(Long id){
        return  categorySubjectMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询CategorySubject全部数据
     * @return
     */
    @Override
    public List<CategorySubject> findAll() {
        return categorySubjectMapper.selectAll();
    }
}
