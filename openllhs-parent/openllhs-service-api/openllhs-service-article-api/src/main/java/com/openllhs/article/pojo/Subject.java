package com.openllhs.article.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:duqiang
 * @Description:Subject构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_subject")
public class Subject implements Serializable{

	@Id
    @Column(name = "id")
	private Long id;//

    @Column(name = "name_en")
	private String nameEn;//

    @Column(name = "name_cn")
	private String nameCn;//

    @Column(name = "letter")
	private String letter;//首字母

    @Column(name = "seq")
	private Integer seq;//排序



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getNameEn() {
		return nameEn;
	}

	//set方法
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	//get方法
	public String getNameCn() {
		return nameCn;
	}

	//set方法
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	//get方法
	public String getLetter() {
		return letter;
	}

	//set方法
	public void setLetter(String letter) {
		this.letter = letter;
	}
	//get方法
	public Integer getSeq() {
		return seq;
	}

	//set方法
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


}
