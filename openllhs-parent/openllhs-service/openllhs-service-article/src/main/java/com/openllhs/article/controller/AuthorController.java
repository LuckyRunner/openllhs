package com.openllhs.article.controller;
import com.openllhs.article.pojo.Author;
import com.openllhs.article.service.AuthorService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /***
     * Author分页条件搜索实现
     * @param author
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Author author, @PathVariable  int page, @PathVariable  int size){
        //调用AuthorService实现分页条件查询Author
        PageInfo<Author> pageInfo = authorService.findPage(author, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Author分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AuthorService实现分页查询Author
        PageInfo<Author> pageInfo = authorService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param author
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Author>> findList(@RequestBody(required = false)  Author author){
        //调用AuthorService实现条件查询Author
        List<Author> list = authorService.findList(author);
        return new Result<List<Author>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用AuthorService实现根据主键删除
        authorService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Author数据
     * @param author
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Author author,@PathVariable Long id){
        //设置主键值
        author.setId(id);
        //调用AuthorService实现修改Author
        authorService.update(author);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Author数据
     * @param author
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Author author){
        //调用AuthorService实现添加Author
        authorService.add(author);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Author数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Author> findById(@PathVariable Long id){
        //调用AuthorService实现根据主键查询Author
        Author author = authorService.findById(id);
        return new Result<Author>(true,StatusCode.OK,"查询成功",author);
    }

    /***
     * 查询Author全部数据
     * @return
     */
    @GetMapping
    public Result<List<Author>> findAll(){
        //调用AuthorService实现查询所有Author
        List<Author> list = authorService.findAll();
        return new Result<List<Author>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
