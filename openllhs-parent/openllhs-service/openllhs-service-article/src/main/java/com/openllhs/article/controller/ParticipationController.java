package com.openllhs.article.controller;
import com.openllhs.article.pojo.Participation;
import com.openllhs.article.service.ParticipationService;
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
@RequestMapping("/participation")
@CrossOrigin
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;

    /***
     * Participation分页条件搜索实现
     * @param participation
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Participation participation, @PathVariable  int page, @PathVariable  int size){
        //调用ParticipationService实现分页条件查询Participation
        PageInfo<Participation> pageInfo = participationService.findPage(participation, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Participation分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ParticipationService实现分页查询Participation
        PageInfo<Participation> pageInfo = participationService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param participation
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Participation>> findList(@RequestBody(required = false)  Participation participation){
        //调用ParticipationService实现条件查询Participation
        List<Participation> list = participationService.findList(participation);
        return new Result<List<Participation>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用ParticipationService实现根据主键删除
        participationService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Participation数据
     * @param participation
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Participation participation,@PathVariable Long id){
        //设置主键值
        participation.setAuthorId(id);
        //调用ParticipationService实现修改Participation
        participationService.update(participation);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Participation数据
     * @param participation
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Participation participation){
        //调用ParticipationService实现添加Participation
        participationService.add(participation);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Participation数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Participation> findById(@PathVariable Long id){
        //调用ParticipationService实现根据主键查询Participation
        Participation participation = participationService.findById(id);
        return new Result<Participation>(true,StatusCode.OK,"查询成功",participation);
    }

    /***
     * 查询Participation全部数据
     * @return
     */
    @GetMapping
    public Result<List<Participation>> findAll(){
        //调用ParticipationService实现查询所有Participation
        List<Participation> list = participationService.findAll();
        return new Result<List<Participation>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
