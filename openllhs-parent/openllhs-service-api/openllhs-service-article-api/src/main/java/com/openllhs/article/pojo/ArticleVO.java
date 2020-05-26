package com.openllhs.article.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleVO {
    private Article article;
    private List<AuthorVO> authors;
}
