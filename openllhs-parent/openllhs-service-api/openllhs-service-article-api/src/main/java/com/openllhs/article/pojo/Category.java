package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Category构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_category")
public class Category implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//分类ID

    @Column(name = "name")
	private String name;//分类名称

    @Column(name = "articles_num")
	private Integer articlesNum;//论文数量

    @Column(name = "is_show")
	private String isShow;//是否显示

    @Column(name = "is_menu")
	private String isMenu;//是否导航

    @Column(name = "seq")
	private Integer seq;//排序

    @Column(name = "parent_id")
	private Long parentId;//上级ID



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
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
	public Integer getArticlesNum() {
		return articlesNum;
	}

	//set方法
	public void setArticlesNum(Integer articlesNum) {
		this.articlesNum = articlesNum;
	}
	//get方法
	public String getIsShow() {
		return isShow;
	}

	//set方法
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	//get方法
	public String getIsMenu() {
		return isMenu;
	}

	//set方法
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
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
	public Long getParentId() {
		return parentId;
	}

	//set方法
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


}
