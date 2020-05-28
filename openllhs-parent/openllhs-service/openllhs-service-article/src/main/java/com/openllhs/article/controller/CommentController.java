package com.openllhs.article.controller;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Comment;
import com.openllhs.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:duqiang
 * @Description:
 * @Date 2020/5/12 0:18
 *****/

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    /***
     * Comment分页条件搜索实现
     * @param comment
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Comment comment, @PathVariable  int page, @PathVariable  int size){
        //调用CommentService实现分页条件查询Comment
        PageInfo<Comment> pageInfo = commentService.findPage(comment, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Comment分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CommentService实现分页查询Comment
        PageInfo<Comment> pageInfo = commentService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param comment
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Comment>> findList(@RequestBody(required = false)  Comment comment){
        //调用CommentService实现条件查询Comment
        List<Comment> list = commentService.findList(comment);
        return new Result<List<Comment>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用CommentService实现根据主键删除
        commentService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Comment数据
     * @param comment
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Comment comment,@PathVariable Long id){
        //设置主键值
        comment.setId(id);
        //调用CommentService实现修改Comment
        commentService.update(comment);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Comment数据
     * @param comment
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Comment comment){
        //调用CommentService实现添加Comment
        commentService.add(comment);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Comment数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Comment> findById(@PathVariable Long id){
        //调用CommentService实现根据主键查询Comment
        Comment comment = commentService.findById(id);
        return new Result<Comment>(true,StatusCode.OK,"查询成功",comment);
    }

    /***
     * 查询Comment全部数据
     * @return
     */
    @GetMapping
    public Result<List<Comment>> findAll(){
        //调用CommentService实现查询所有Comment
        List<Comment> list = commentService.findAll();
        return new Result<List<Comment>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
