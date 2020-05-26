package com.openllhs.article.service.impl;
import com.openllhs.article.dao.CommentMapper;
import com.openllhs.article.pojo.Comment;
import com.openllhs.article.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Comment业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    /**
     * Comment条件+分页查询
     * @param comment 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Comment> findPage(Comment comment, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(comment);
        //执行搜索
        return new PageInfo<Comment>(commentMapper.selectByExample(example));
    }

    /**
     * Comment分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Comment> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Comment>(commentMapper.selectAll());
    }

    /**
     * Comment条件查询
     * @param comment
     * @return
     */
    @Override
    public List<Comment> findList(Comment comment){
        //构建查询条件
        Example example = createExample(comment);
        //根据构建的条件查询数据
        return commentMapper.selectByExample(example);
    }


    /**
     * Comment构建查询对象
     * @param comment
     * @return
     */
    public Example createExample(Comment comment){
        Example example=new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        if(comment!=null){
            // 
            if(!StringUtils.isEmpty(comment.getId())){
                    criteria.andEqualTo("id",comment.getId());
            }
            // 
            if(!StringUtils.isEmpty(comment.getUserId())){
                    criteria.andEqualTo("userId",comment.getUserId());
            }
            // 
            if(!StringUtils.isEmpty(comment.getArticleId())){
                    criteria.andEqualTo("articleId",comment.getArticleId());
            }
            // 
            if(!StringUtils.isEmpty(comment.getContent())){
                    criteria.andEqualTo("content",comment.getContent());
            }
            // 
            if(!StringUtils.isEmpty(comment.getDate())){
                    criteria.andEqualTo("date",comment.getDate());
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
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Comment
     * @param comment
     */
    @Override
    public void update(Comment comment){
        commentMapper.updateByPrimaryKey(comment);
    }

    /**
     * 增加Comment
     * @param comment
     */
    @Override
    public void add(Comment comment){
        commentMapper.insert(comment);
    }

    /**
     * 根据ID查询Comment
     * @param id
     * @return
     */
    @Override
    public Comment findById(Long id){
        return  commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Comment全部数据
     * @return
     */
    @Override
    public List<Comment> findAll() {
        return commentMapper.selectAll();
    }
}
