package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
/****
 * @Author:duqiang
 * @Description:CategorySubject构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_category_subject")
public class CategorySubject implements Serializable{

    @Column(name = "category_id")
	private Long categoryId;//分类ID

	@Id
    @Column(name = "subject_id")
	private Long subjectId;//领域ID



	//get方法
	public Long getCategoryId() {
		return categoryId;
	}

	//set方法
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	//get方法
	public Long getSubjectId() {
		return subjectId;
	}

	//set方法
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}


}
