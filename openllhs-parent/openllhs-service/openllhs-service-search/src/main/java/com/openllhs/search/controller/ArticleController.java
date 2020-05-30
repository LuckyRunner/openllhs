package com.openllhs.search.controller;

import com.openllhs.search.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/import")
    @ResponseBody
    public Result importData(){
        articleService.importData();
        return new Result(true, StatusCode.OK,"执行操作成功");
    }

    @GetMapping
    @ResponseBody
    public Map search(@RequestParam(required = false) Map<String, String> searchMap) throws Exception{
        return articleService.search(searchMap);
    }

}
