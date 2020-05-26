package com.openllhs.article.pojo;

import lombok.Data;

import java.util.List;


@Data
public class AuthorVO {
    private Long id;//ID
    private String nameCn;//中文名
    private String email;//邮箱
    private String nameEn;//英文名
    private Long articleId;//
    private String isCorauthor;//
    private Integer seq;//
    private String isFirstAuthor;
    private List<Organization> organizationList;
}
