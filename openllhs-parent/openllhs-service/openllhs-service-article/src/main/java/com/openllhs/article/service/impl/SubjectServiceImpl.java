package com.openllhs.article.service.impl;
import com.openllhs.article.dao.SubjectMapper;
import com.openllhs.article.pojo.Subject;
import com.openllhs.article.service.SubjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Subject业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;


    /**
     * Subject条件+分页查询
     * @param subject 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Subject> findPage(Subject subject, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(subject);
        //执行搜索
        return new PageInfo<Subject>(subjectMapper.selectByExample(example));
    }

    /**
     * Subject分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Subject> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Subject>(subjectMapper.selectAll());
    }

    /**
     * Subject条件查询
     * @param subject
     * @return
     */
    @Override
    public List<Subject> findList(Subject subject){
        //构建查询条件
        Example example = createExample(subject);
        //根据构建的条件查询数据
        return subjectMapper.selectByExample(example);
    }


    /**
     * Subject构建查询对象
     * @param subject
     * @return
     */
    public Example createExample(Subject subject){
        Example example=new Example(Subject.class);
        Example.Criteria criteria = example.createCriteria();
        if(subject!=null){
            // 领域id
            if(!StringUtils.isEmpty(subject.getId())){
                    criteria.andEqualTo("id",subject.getId());
            }
            // 领域名称
            if(!StringUtils.isEmpty(subject.getName())){
                    criteria.andLike("name","%"+subject.getName()+"%");
            }
            // 领域图片地址
            if(!StringUtils.isEmpty(subject.getImage())){
                    criteria.andEqualTo("image",subject.getImage());
            }
            // 领域的首字母
            if(!StringUtils.isEmpty(subject.getLetter())){
                    criteria.andEqualTo("letter",subject.getLetter());
            }
            // 排序
            if(!StringUtils.isEmpty(subject.getSeq())){
                    criteria.andEqualTo("seq",subject.getSeq());
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
        subjectMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Subject
     * @param subject
     */
    @Override
    public void update(Subject subject){
        subjectMapper.updateByPrimaryKey(subject);
    }

    /**
     * 增加Subject
     * @param subject
     */
    @Override
    public void add(Subject subject){
        subjectMapper.insert(subject);
    }

    /**
     * 根据ID查询Subject
     * @param id
     * @return
     */
    @Override
    public Subject findById(Long id){
        return  subjectMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Subject全部数据
     * @return
     */
    @Override
    public List<Subject> findAll() {
        return subjectMapper.selectAll();
    }
}
