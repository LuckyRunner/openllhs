package com.openllhs.search.service;

import java.util.Map;

public interface ArticleService {

    Map<String,Object> search(Map<String, String> searchMap) throws Exception;

    void importData();
}
