package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:duqiang
 * @Description:Album构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_album")
public class Album implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//编号

    @Column(name = "title")
	private String title;//

    @Column(name = "image")
	private String image;//相册封面

    @Column(name = "image_items")
	private String imageItems;//图片

    @Column(name = "article_id")
	private Long articleId;//论文ID



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getTitle() {
		return title;
	}

	//set方法
	public void setTitle(String title) {
		this.title = title;
	}
	//get方法
	public String getImage() {
		return image;
	}

	//set方法
	public void setImage(String image) {
		this.image = image;
	}
	//get方法
	public String getImageItems() {
		return imageItems;
	}

	//set方法
	public void setImageItems(String imageItems) {
		this.imageItems = imageItems;
	}
	//get方法
	public Long getArticleId() {
		return articleId;
	}

	//set方法
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}


}
