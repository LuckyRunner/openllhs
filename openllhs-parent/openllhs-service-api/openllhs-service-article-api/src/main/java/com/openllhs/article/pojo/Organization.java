package com.openllhs.article.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:duqiang
 * @Description:Organization构建
 * @Date 2020/5/12 0:18
 *****/
@Data
@Table(name="tb_organization")
public class Organization implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//id

    @Column(name = "name")
	private String name;//单位名

    @Column(name = "seq")
	private Integer seq;//

	@Column(name = "article_id")
	private Long articleId;//
}
