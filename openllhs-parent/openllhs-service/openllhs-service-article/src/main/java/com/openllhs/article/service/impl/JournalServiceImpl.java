package com.openllhs.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.openllhs.article.dao.JournalMapper;
import com.openllhs.article.pojo.Journal;
import com.openllhs.article.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:Journal业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalMapper journalMapper;


    /**
     * Journal条件+分页查询
     * @param journal 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Journal> findPage(Journal journal, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(journal);
        //执行搜索
        return new PageInfo<Journal>(journalMapper.selectByExample(example));
    }

    /**
     * Journal分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Journal> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Journal>(journalMapper.selectAll());
    }

    /**
     * Journal条件查询
     * @param journal
     * @return
     */
    @Override
    public List<Journal> findList(Journal journal){
        //构建查询条件
        Example example = createExample(journal);
        //根据构建的条件查询数据
        return journalMapper.selectByExample(example);
    }


    /**
     * Journal构建查询对象
     * @param journal
     * @return
     */
    public Example createExample(Journal journal){
        Example example=new Example(Journal.class);
        Example.Criteria criteria = example.createCriteria();
        if(journal!=null){
            //
            if(!StringUtils.isEmpty(journal.getId())){
                    criteria.andEqualTo("id",journal.getId());
            }
            // 期刊名字
            if(!StringUtils.isEmpty(journal.getName())){
                    criteria.andLike("name","%"+journal.getName()+"%");
            }
            // 首字母
            if(!StringUtils.isEmpty(journal.getLetter())){
                    criteria.andEqualTo("letter",journal.getLetter());
            }
            // 排序
            if(!StringUtils.isEmpty(journal.getSeq())){
                    criteria.andEqualTo("seq",journal.getSeq());
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
        journalMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Journal
     * @param journal
     */
    @Override
    public void update(Journal journal){
        journalMapper.updateByPrimaryKey(journal);
    }

    /**
     * 增加Journal
     * @param journal
     */
    @Override
    public void add(Journal journal){
        journalMapper.insert(journal);
    }

    /**
     * 根据ID查询Journal
     * @param id
     * @return
     */
    @Override
    public Journal findById(Long id){
        return  journalMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Journal全部数据
     * @return
     */
    @Override
    public List<Journal> findAll() {
        return journalMapper.selectAll();
    }
}
