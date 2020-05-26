package com.openllhs.article.controller;
import com.openllhs.article.pojo.CategorySubject;
import com.openllhs.article.service.CategorySubjectService;
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
@RequestMapping("/categorySubject")
@CrossOrigin
public class CategorySubjectController {

    @Autowired
    private CategorySubjectService categorySubjectService;

    /***
     * CategorySubject分页条件搜索实现
     * @param categorySubject
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  CategorySubject categorySubject, @PathVariable  int page, @PathVariable  int size){
        //调用CategorySubjectService实现分页条件查询CategorySubject
        PageInfo<CategorySubject> pageInfo = categorySubjectService.findPage(categorySubject, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * CategorySubject分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CategorySubjectService实现分页查询CategorySubject
        PageInfo<CategorySubject> pageInfo = categorySubjectService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param categorySubject
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<CategorySubject>> findList(@RequestBody(required = false)  CategorySubject categorySubject){
        //调用CategorySubjectService实现条件查询CategorySubject
        List<CategorySubject> list = categorySubjectService.findList(categorySubject);
        return new Result<List<CategorySubject>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用CategorySubjectService实现根据主键删除
        categorySubjectService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改CategorySubject数据
     * @param categorySubject
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  CategorySubject categorySubject,@PathVariable Long id){
        //设置主键值
        categorySubject.setSubjectId(id);
        //调用CategorySubjectService实现修改CategorySubject
        categorySubjectService.update(categorySubject);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增CategorySubject数据
     * @param categorySubject
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   CategorySubject categorySubject){
        //调用CategorySubjectService实现添加CategorySubject
        categorySubjectService.add(categorySubject);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询CategorySubject数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<CategorySubject> findById(@PathVariable Long id){
        //调用CategorySubjectService实现根据主键查询CategorySubject
        CategorySubject categorySubject = categorySubjectService.findById(id);
        return new Result<CategorySubject>(true,StatusCode.OK,"查询成功",categorySubject);
    }

    /***
     * 查询CategorySubject全部数据
     * @return
     */
    @GetMapping
    public Result<List<CategorySubject>> findAll(){
        //调用CategorySubjectService实现查询所有CategorySubject
        List<CategorySubject> list = categorySubjectService.findAll();
        return new Result<List<CategorySubject>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
