package com.openllhs.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "search")
@RequestMapping(value = "/search")
public interface ArticleFeign {
    @GetMapping
    Map search(@RequestParam(required = false) Map<String, String>stringMap) throws Exception;
}
