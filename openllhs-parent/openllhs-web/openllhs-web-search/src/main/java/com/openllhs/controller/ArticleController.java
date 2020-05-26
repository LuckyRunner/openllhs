package com.openllhs.controller;

import com.openllhs.search.feign.ArticleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value="search")
public class ArticleController {

    @Autowired
    private ArticleFeign articleFeign;

    @GetMapping(value="search")
    public String search(@RequestParam(required = false)Map<String,String> searchMap, Model model) throws Exception{
        Map<String, Object> resultMap = articleFeign.search(searchMap);
        model.addAttribute("resultMap", resultMap);
        return "search";
    }

    @GetMapping(value="index")
    public String index() throws Exception{
        return "index";
    }

}
