package com.openllhs.article.feign;

import com.openllhs.article.pojo.Article;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "article")
@RequestMapping(value = "article")
public interface ArticleFeign {

    @GetMapping("/status/{status}")
    Result<List<Article>> findByStatus(@PathVariable String status);
}
