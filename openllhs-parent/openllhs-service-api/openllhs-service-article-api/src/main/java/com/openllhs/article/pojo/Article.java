package com.openllhs.article.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Article构建
 * @Date 2020/5/12 0:18
 *****/
@Data
@Table(name="tb_article")
public class Article implements Serializable{

	@Id
    @Column(name = "id")
	private Long id;//论文id

    @Column(name = "doi")
	private String doi;//doi

    @Column(name = "title")
	private String title;//标题

    @Column(name = "a_abstract")
	private String aAbstract;//摘要

    @Column(name = "content")
	private String content;//论文内容

    @Column(name = "key_words")
	private String keyWords;//关键字

    @Column(name = "image")
	private String image;//论文首图片

    @Column(name = "images")
	private String images;//论文内容图片列表

    @Column(name = "pdf")
	private String pdf;//pdf

    @Column(name = "pdfs")
	private String pdfs;//以往版本pdf列表

    @Column(name = "create_time")
	private Date createTime;//创建时间

    @Column(name = "update_time")
	private Date updateTime;//更新时间

    @Column(name = "submit_time")
	private Date submitTime;//

    @Column(name = "subject")
	private String subject;//领域

    @Column(name = "read_num")
	private Integer readNum;//阅读数

    @Column(name = "comment_num")
	private Integer commentNum;//评论数

    @Column(name = "download_num")
	private Integer downloadNum;//下载数

    @Column(name = "status")
	private String status;//审核状态 0-未审核，1-已审核

	@Column(name = "authors")
	private String authors;

    @Column(name = "version")
	private Integer version;//

    @Column(name = "category1_id")
	private Long category1Id;//一级分类

    @Column(name = "category2_id")
	private Long category2Id;//二级分类

    @Column(name = "category3_id")
	private Long category3Id;//三级分类

	@Column(name = "category_name1")
	private String categoryName1;//一级分类

	@Column(name = "category_name2")
	private String categoryName2;//二级分类

	@Column(name = "category_name3")
	private String categoryName3;//三级分类

    @Column(name = "is_delete")
	private String isDelete;//是否删除，0-未删除，1-已展示

	@Column(name = "is_show")
	private String isShow;//是否展示，0-未展示，1-已展示
}
