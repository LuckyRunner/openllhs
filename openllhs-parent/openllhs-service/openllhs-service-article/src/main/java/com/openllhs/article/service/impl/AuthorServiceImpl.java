package com.openllhs.article.service.impl;
import com.openllhs.article.dao.AuthorMapper;
import com.openllhs.article.pojo.Author;
import com.openllhs.article.service.AuthorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Author业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;


    /**
     * Author条件+分页查询
     * @param author 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Author> findPage(Author author, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(author);
        //执行搜索
        return new PageInfo<Author>(authorMapper.selectByExample(example));
    }

    /**
     * Author分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Author> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Author>(authorMapper.selectAll());
    }

    /**
     * Author条件查询
     * @param author
     * @return
     */
    @Override
    public List<Author> findList(Author author){
        //构建查询条件
        Example example = createExample(author);
        //根据构建的条件查询数据
        return authorMapper.selectByExample(example);
    }


    /**
     * Author构建查询对象
     * @param author
     * @return
     */
    public Example createExample(Author author){
        Example example=new Example(Author.class);
        Example.Criteria criteria = example.createCriteria();
        if(author!=null){
            // ID
            if(!StringUtils.isEmpty(author.getId())){
                    criteria.andEqualTo("id",author.getId());
            }
            // 中文名
            if(!StringUtils.isEmpty(author.getNameCn())){
                    criteria.andLike("nameCn","%"+author.getNameCn()+"%");
            }
            // 邮箱
            if(!StringUtils.isEmpty(author.getEmail())){
                    criteria.andEqualTo("email",author.getEmail());
            }
            // 英文名
            if(!StringUtils.isEmpty(author.getNameEn())){
                    criteria.andLike("nameEn","%"+author.getNameEn()+"%");
            }
            // 
            if(!StringUtils.isEmpty(author.getArticleId())){
                    criteria.andEqualTo("articleId",author.getArticleId());
            }
            // 
            if(!StringUtils.isEmpty(author.getIsCorauthor())){
                    criteria.andEqualTo("isCorauthor",author.getIsCorauthor());
            }
            // 
            if(!StringUtils.isEmpty(author.getSeq())){
                    criteria.andEqualTo("seq",author.getSeq());
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
        authorMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Author
     * @param author
     */
    @Override
    public void update(Author author){
        authorMapper.updateByPrimaryKey(author);
    }

    /**
     * 增加Author
     * @param author
     */
    @Override
    public void add(Author author){
        authorMapper.insert(author);
    }

    /**
     * 根据ID查询Author
     * @param id
     * @return
     */
    @Override
    public Author findById(Long id){
        return  authorMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Author全部数据
     * @return
     */
    @Override
    public List<Author> findAll() {
        return authorMapper.selectAll();
    }
}
