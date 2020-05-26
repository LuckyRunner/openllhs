package com.openllhs.article.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Belong构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_belong")
public class Belong implements Serializable{

    @Column(name = "author_id")
	private Long authorId;//作者ID

	@Id
    @Column(name = "organization_id")
	private Long organizationId;//单位ID

    @Column(name = "order")
	private Integer order;//排序



	//get方法
	public Long getAuthorId() {
		return authorId;
	}

	//set方法
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	//get方法
	public Long getOrganizationId() {
		return organizationId;
	}

	//set方法
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	//get方法
	public Integer getOrder() {
		return order;
	}

	//set方法
	public void setOrder(Integer order) {
		this.order = order;
	}


}
