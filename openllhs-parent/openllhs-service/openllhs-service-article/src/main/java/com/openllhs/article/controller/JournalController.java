package com.openllhs.article.controller;

import com.github.pagehelper.PageInfo;
import com.openllhs.article.pojo.Journal;
import com.openllhs.article.service.JournalService;
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
@RequestMapping("/journal")
@CrossOrigin
public class JournalController {

    @Autowired
    private JournalService journalService;

    /***
     * Journal分页条件搜索实现
     * @param journal
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Journal journal, @PathVariable  int page, @PathVariable  int size){
        //调用JournalService实现分页条件查询Journal
        PageInfo<Journal> pageInfo = journalService.findPage(journal, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Journal分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用JournalService实现分页查询Journal
        PageInfo<Journal> pageInfo = journalService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param journal
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Journal>> findList(@RequestBody(required = false)  Journal journal){
        //调用JournalService实现条件查询Journal
        List<Journal> list = journalService.findList(journal);
        return new Result<List<Journal>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用JournalService实现根据主键删除
        journalService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Journal数据
     * @param journal
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Journal journal,@PathVariable Long id){
        //设置主键值
        journal.setId(id);
        //调用JournalService实现修改Journal
        journalService.update(journal);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Journal数据
     * @param journal
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Journal journal){
        //调用JournalService实现添加Journal
        journalService.add(journal);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Journal数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Journal> findById(@PathVariable Long id){
        //调用JournalService实现根据主键查询Journal
        Journal journal = journalService.findById(id);
        return new Result<Journal>(true,StatusCode.OK,"查询成功",journal);
    }

    /***
     * 查询Journal全部数据
     * @return
     */
    @GetMapping
    public Result<List<Journal>> findAll(){
        //调用JournalService实现查询所有Journal
        List<Journal> list = journalService.findAll();
        return new Result<List<Journal>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
