package com.openllhs.article.service.impl;
import com.openllhs.article.dao.ParticipationMapper;
import com.openllhs.article.pojo.Participation;
import com.openllhs.article.service.ParticipationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Participation业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationMapper participationMapper;


    /**
     * Participation条件+分页查询
     * @param participation 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Participation> findPage(Participation participation, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(participation);
        //执行搜索
        return new PageInfo<Participation>(participationMapper.selectByExample(example));
    }

    /**
     * Participation分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Participation> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Participation>(participationMapper.selectAll());
    }

    /**
     * Participation条件查询
     * @param participation
     * @return
     */
    @Override
    public List<Participation> findList(Participation participation){
        //构建查询条件
        Example example = createExample(participation);
        //根据构建的条件查询数据
        return participationMapper.selectByExample(example);
    }


    /**
     * Participation构建查询对象
     * @param participation
     * @return
     */
    public Example createExample(Participation participation){
        Example example=new Example(Participation.class);
        Example.Criteria criteria = example.createCriteria();
        if(participation!=null){
            // 作者ID
            if(!StringUtils.isEmpty(participation.getAuthorId())){
                    criteria.andEqualTo("authorId",participation.getAuthorId());
            }
            // 论文ID
            if(!StringUtils.isEmpty(participation.getArticleId())){
                    criteria.andEqualTo("articleId",participation.getArticleId());
            }
            // 排序
            if(!StringUtils.isEmpty(participation.getOrder())){
                    criteria.andEqualTo("order",participation.getOrder());
            }
            // 是否通讯作者，0-不是通讯作者，1-是通讯作者
            if(!StringUtils.isEmpty(participation.getIsCorrespond())){
                    criteria.andEqualTo("isCorrespond",participation.getIsCorrespond());
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
        participationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Participation
     * @param participation
     */
    @Override
    public void update(Participation participation){
        participationMapper.updateByPrimaryKey(participation);
    }

    /**
     * 增加Participation
     * @param participation
     */
    @Override
    public void add(Participation participation){
        participationMapper.insert(participation);
    }

    /**
     * 根据ID查询Participation
     * @param id
     * @return
     */
    @Override
    public Participation findById(Long id){
        return  participationMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Participation全部数据
     * @return
     */
    @Override
    public List<Participation> findAll() {
        return participationMapper.selectAll();
    }
}
