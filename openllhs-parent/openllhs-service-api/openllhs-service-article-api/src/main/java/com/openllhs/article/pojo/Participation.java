package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Participation构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_participation")
public class Participation implements Serializable{

	@Id
    @Column(name = "author_id")
	private Long authorId;//作者ID

    @Column(name = "article_id")
	private Long articleId;//论文ID

    @Column(name = "order")
	private Integer order;//排序

    @Column(name = "is_correspond")
	private String isCorrespond;//是否通讯作者，0-不是通讯作者，1-是通讯作者



	//get方法
	public Long getAuthorId() {
		return authorId;
	}

	//set方法
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
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
	public Integer getOrder() {
		return order;
	}

	//set方法
	public void setOrder(Integer order) {
		this.order = order;
	}
	//get方法
	public String getIsCorrespond() {
		return isCorrespond;
	}

	//set方法
	public void setIsCorrespond(String isCorrespond) {
		this.isCorrespond = isCorrespond;
	}


}
