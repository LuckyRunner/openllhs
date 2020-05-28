package com.openllhs.article.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:duqiang
 * @Description:Journal构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_journal")
public class Journal implements Serializable{

	@Id
    @Column(name = "id")
	private Long id;//

    @Column(name = "name")
	private String name;//期刊名字

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
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
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
