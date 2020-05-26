package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Subject构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_subject")
public class Subject implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//领域id

    @Column(name = "name")
	private String name;//领域名称

    @Column(name = "image")
	private String image;//领域图片地址

    @Column(name = "letter")
	private String letter;//领域的首字母

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
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
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
