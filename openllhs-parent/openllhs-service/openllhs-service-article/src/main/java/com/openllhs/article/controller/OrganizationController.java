package com.openllhs.article.controller;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Organization;
import com.openllhs.article.service.OrganizationService;
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
@RequestMapping("/organization")
@CrossOrigin
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /***
     * Organization分页条件搜索实现
     * @param organization
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Organization organization, @PathVariable  int page, @PathVariable  int size){
        //调用OrganizationService实现分页条件查询Organization
        PageInfo<Organization> pageInfo = organizationService.findPage(organization, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Organization分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrganizationService实现分页查询Organization
        PageInfo<Organization> pageInfo = organizationService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param organization
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Organization>> findList(@RequestBody(required = false)  Organization organization){
        //调用OrganizationService实现条件查询Organization
        List<Organization> list = organizationService.findList(organization);
        return new Result<List<Organization>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用OrganizationService实现根据主键删除
        organizationService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Organization数据
     * @param organization
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Organization organization,@PathVariable Long id){
        //设置主键值
        organization.setId(id);
        //调用OrganizationService实现修改Organization
        organizationService.update(organization);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Organization数据
     * @param organization
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Organization organization){
        //调用OrganizationService实现添加Organization
        organizationService.add(organization);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Organization数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Organization> findById(@PathVariable Long id){
        //调用OrganizationService实现根据主键查询Organization
        Organization organization = organizationService.findById(id);
        return new Result<Organization>(true,StatusCode.OK,"查询成功",organization);
    }

    /***
     * 查询Organization全部数据
     * @return
     */
    @GetMapping
    public Result<List<Organization>> findAll(){
        //调用OrganizationService实现查询所有Organization
        List<Organization> list = organizationService.findAll();
        return new Result<List<Organization>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
