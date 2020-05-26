package com.openllhs.article.controller;
import com.openllhs.article.pojo.Belong;
import com.openllhs.article.service.BelongService;
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
@RequestMapping("/belong")
@CrossOrigin
public class BelongController {

    @Autowired
    private BelongService belongService;

    /***
     * Belong分页条件搜索实现
     * @param belong
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Belong belong, @PathVariable  int page, @PathVariable  int size){
        //调用BelongService实现分页条件查询Belong
        PageInfo<Belong> pageInfo = belongService.findPage(belong, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Belong分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用BelongService实现分页查询Belong
        PageInfo<Belong> pageInfo = belongService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param belong
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Belong>> findList(@RequestBody(required = false)  Belong belong){
        //调用BelongService实现条件查询Belong
        List<Belong> list = belongService.findList(belong);
        return new Result<List<Belong>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用BelongService实现根据主键删除
        belongService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Belong数据
     * @param belong
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Belong belong,@PathVariable Long id){
        //设置主键值
        belong.setOrganizationId(id);
        //调用BelongService实现修改Belong
        belongService.update(belong);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Belong数据
     * @param belong
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Belong belong){
        //调用BelongService实现添加Belong
        belongService.add(belong);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Belong数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Belong> findById(@PathVariable Long id){
        //调用BelongService实现根据主键查询Belong
        Belong belong = belongService.findById(id);
        return new Result<Belong>(true,StatusCode.OK,"查询成功",belong);
    }

    /***
     * 查询Belong全部数据
     * @return
     */
    @GetMapping
    public Result<List<Belong>> findAll(){
        //调用BelongService实现查询所有Belong
        List<Belong> list = belongService.findAll();
        return new Result<List<Belong>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
