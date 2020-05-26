package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Organization构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_organization")
public class Organization implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//id

    @Column(name = "name_cn")
	private String nameCn;//单位中文名

    @Column(name = "address_cn")
	private String addressCn;//地址

    @Column(name = "name_en")
	private String nameEn;//单位英文名

    @Column(name = "address_en")
	private String addressEn;//英文地址

    @Column(name = "seq")
	private Integer seq;//

    @Column(name = "author_id")
	private Long authorId;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
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
	public String getAddressCn() {
		return addressCn;
	}

	//set方法
	public void setAddressCn(String addressCn) {
		this.addressCn = addressCn;
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
	public String getAddressEn() {
		return addressEn;
	}

	//set方法
	public void setAddressEn(String addressEn) {
		this.addressEn = addressEn;
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
	public Long getAuthorId() {
		return authorId;
	}

	//set方法
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}


}
