package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
/****
 * @Author:duqiang
 * @Description:Comment构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_comment")
public class Comment implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "user_id")
	private Long userId;//

    @Column(name = "article_id")
	private Long articleId;//

    @Column(name = "content")
	private String content;//

    @Column(name = "date")
	private Date date;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getUserId() {
		return userId;
	}

	//set方法
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getContent() {
		return content;
	}

	//set方法
	public void setContent(String content) {
		this.content = content;
	}
	//get方法
	public Date getDate() {
		return date;
	}

	//set方法
	public void setDate(Date date) {
		this.date = date;
	}


}
