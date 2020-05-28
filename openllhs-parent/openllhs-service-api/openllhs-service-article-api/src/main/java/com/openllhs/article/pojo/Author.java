package com.openllhs.article.pojo;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:duqiang
 * @Description:Author构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_author")
public class Author implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//ID

    @Column(name = "article_id")
	private Long articleId;//

    @Column(name = "name")
	private String name;//姓名

    @Column(name = "email")
	private String email;//邮箱

    @Column(name = "seq")
	private Integer seq;//排序

    @Column(name = "organization_seq")
	private String organizationSeq;//所属单位编码顺序

    @Column(name = "is_corauthor")
	private String isCorauthor;//是否通讯作者，1-是，0-不是

    @Column(name = "is_first_author")
	private String isFirstAuthor;//是否一作，1-是，0-不是



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getArticleId() {
		return articleId;
	}

	//set方法
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public Integer getSeq() {
		return seq;
	}

	//set方法
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	//get方法
	public String getOrganizationSeq() {
		return organizationSeq;
	}

	//set方法
	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}
	//get方法
	public String getIsCorauthor() {
		return isCorauthor;
	}

	//set方法
	public void setIsCorauthor(String isCorauthor) {
		this.isCorauthor = isCorauthor;
	}
	//get方法
	public String getIsFirstAuthor() {
		return isFirstAuthor;
	}

	//set方法
	public void setIsFirstAuthor(String isFirstAuthor) {
		this.isFirstAuthor = isFirstAuthor;
	}


}
