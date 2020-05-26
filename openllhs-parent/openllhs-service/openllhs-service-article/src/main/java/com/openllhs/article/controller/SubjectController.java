package com.openllhs.article.controller;
import com.openllhs.article.pojo.Subject;
import com.openllhs.article.service.SubjectService;
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
@RequestMapping("/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /***
     * Subject分页条件搜索实现
     * @param subject
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Subject subject, @PathVariable  int page, @PathVariable  int size){
        //调用SubjectService实现分页条件查询Subject
        PageInfo<Subject> pageInfo = subjectService.findPage(subject, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Subject分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SubjectService实现分页查询Subject
        PageInfo<Subject> pageInfo = subjectService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param subject
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Subject>> findList(@RequestBody(required = false)  Subject subject){
        //调用SubjectService实现条件查询Subject
        List<Subject> list = subjectService.findList(subject);
        return new Result<List<Subject>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SubjectService实现根据主键删除
        subjectService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Subject数据
     * @param subject
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Subject subject,@PathVariable Long id){
        //设置主键值
        subject.setId(id);
        //调用SubjectService实现修改Subject
        subjectService.update(subject);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Subject数据
     * @param subject
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Subject subject){
        //调用SubjectService实现添加Subject
        subjectService.add(subject);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Subject数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Subject> findById(@PathVariable Long id){
        //调用SubjectService实现根据主键查询Subject
        Subject subject = subjectService.findById(id);
        return new Result<Subject>(true,StatusCode.OK,"查询成功",subject);
    }

    /***
     * 查询Subject全部数据
     * @return
     */
    @GetMapping
    public Result<List<Subject>> findAll(){
        //调用SubjectService实现查询所有Subject
        List<Subject> list = subjectService.findAll();
        return new Result<List<Subject>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
