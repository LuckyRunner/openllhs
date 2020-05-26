package com.openllhs.article.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:duqiang
 * @Description:Author构建
 * @Date 2020/5/12 0:18
 *****/
@Data
@Table(name="tb_author")
public class Author implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//ID

    @Column(name = "name_cn")
	private String nameCn;//中文名

    @Column(name = "email")
	private String email;//邮箱

    @Column(name = "name_en")
	private String nameEn;//英文名

    @Column(name = "article_id")
	private Long articleId;//

    @Column(name = "is_corauthor")
	private String isCorauthor;//

    @Column(name = "seq")
	private Integer seq;//

	@Column(name = "is_first_author")
	private String isFirstAuthor;//
}
