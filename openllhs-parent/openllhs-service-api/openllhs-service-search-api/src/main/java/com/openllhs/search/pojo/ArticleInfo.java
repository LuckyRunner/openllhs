package com.openllhs.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@Document(indexName = "articleinfo",type = "docs")
public class ArticleInfo implements Serializable {

    /**
     * type = FieldType.Text:类型， Text支持分词
     * index = true:添加数据时，是否分词
     * analyzer = "ik_smart":创建索引时的分词器
     * store = false:是否存储
     * searchAnalyzer = "ik_smart":搜索时使用的分词器
     */

    @Id
    @Field(index = true, store = true, type = FieldType.Keyword)
    private Long id;

    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Keyword)
    private String doi;//doi

    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String keyWords;//关键字

    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String aabstract;

    @Field(index = false, store = true, type = FieldType.Keyword)
    private String image;

    //文章状态： 1-正常， 2-下架，3-删除
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String status;

    private Date submitTime;//

    private Date createTime;

    private Date updateTime;

    @Field(index = true, store = true, type = FieldType.Keyword)
    private String idDefault;

    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer readNum;//阅读数

    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer commentNum;//评论数

    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer downloadNum;//下载数

    //类目ID
    @Field(index = true, store = true, type = FieldType.Long)
    private Long categoryId;

    //类目名称
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName1;

    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName2;

    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName3;

    @Field(index = true, store = true, type = FieldType.Keyword)
    private String subject;

    private String authors;

    private Map<String, Object> authorMap;

    //是否默认
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String isDefault;
}
