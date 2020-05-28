package com.openllhs.article.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:duqiang
 * @Description:Article构建
 * @Date 2020/5/12 0:18
 *****/
@Table(name="tb_article")
public class Article implements Serializable{

	@Id
    @Column(name = "id")
	private Long id;//论文id

    @Column(name = "doi")
	private String doi;//doi

    @Column(name = "title")
	private String title;//标题

    @Column(name = "a_abstract")
	private String aAbstract;//摘要

    @Column(name = "content")
	private String content;//论文内容

    @Column(name = "key_words")
	private String keyWords;//关键字

    @Column(name = "image")
	private String image;//论文首图片

    @Column(name = "images")
	private String images;//论文内容图片列表

    @Column(name = "pdf")
	private String pdf;//pdf

    @Column(name = "pdfs")
	private String pdfs;//以往版本pdf列表

    @Column(name = "create_time")
	private Date createTime;//创建时间

    @Column(name = "update_time")
	private Date updateTime;//更新时间

    @Column(name = "submit_time")
	private Date submitTime;//

    @Column(name = "publish_date")
	private Date publishDate;//

    @Column(name = "subject")
	private String subject;//领域

    @Column(name = "journal")
	private String journal;//期刊名

    @Column(name = "read_num")
	private Integer readNum;//阅读数

    @Column(name = "comment_num")
	private Integer commentNum;//评论数

    @Column(name = "download_num")
	private Integer downloadNum;//下载数

    @Column(name = "status")
	private String status;//审核状态，0-未审核，1-已审核

    @Column(name = "version")
	private Integer version;//

    @Column(name = "is_delete")
	private String isDelete;//是否删除，0-未删除

    @Column(name = "is_show")
	private String isShow;//是否展示，0-未展示

    @Column(name = "authors")
	private String authors;//

    @Column(name = "organizations")
	private String organizations;//

    @Column(name = "category_name1")
	private String categoryName1;//

    @Column(name = "category_name2")
	private String categoryName2;//

    @Column(name = "category_name3")
	private String categoryName3;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getDoi() {
		return doi;
	}

	//set方法
	public void setDoi(String doi) {
		this.doi = doi;
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
	public String getAAbstract() {
		return aAbstract;
	}

	//set方法
	public void setAAbstract(String aAbstract) {
		this.aAbstract = aAbstract;
	}
	//get方法
	public String getContent() {
		return content;
	}

	//set方法
	public void setContent(String content) {
		this.content = content;
	}
	//get方法
	public String getKeyWords() {
		return keyWords;
	}

	//set方法
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
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
	public String getImages() {
		return images;
	}

	//set方法
	public void setImages(String images) {
		this.images = images;
	}
	//get方法
	public String getPdf() {
		return pdf;
	}

	//set方法
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	//get方法
	public String getPdfs() {
		return pdfs;
	}

	//set方法
	public void setPdfs(String pdfs) {
		this.pdfs = pdfs;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public Date getUpdateTime() {
		return updateTime;
	}

	//set方法
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	//get方法
	public Date getSubmitTime() {
		return submitTime;
	}

	//set方法
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	//get方法
	public Date getPublishDate() {
		return publishDate;
	}

	//set方法
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	//get方法
	public String getSubject() {
		return subject;
	}

	//set方法
	public void setSubject(String subject) {
		this.subject = subject;
	}
	//get方法
	public String getJournal() {
		return journal;
	}

	//set方法
	public void setJournal(String journal) {
		this.journal = journal;
	}
	//get方法
	public Integer getReadNum() {
		return readNum;
	}

	//set方法
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
	//get方法
	public Integer getCommentNum() {
		return commentNum;
	}

	//set方法
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	//get方法
	public Integer getDownloadNum() {
		return downloadNum;
	}

	//set方法
	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}
	//get方法
	public Integer getVersion() {
		return version;
	}

	//set方法
	public void setVersion(Integer version) {
		this.version = version;
	}
	//get方法
	public String getIsDelete() {
		return isDelete;
	}

	//set方法
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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
	public String getAuthors() {
		return authors;
	}

	//set方法
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	//get方法
	public String getOrganizations() {
		return organizations;
	}

	//set方法
	public void setOrganizations(String organizations) {
		this.organizations = organizations;
	}
	//get方法
	public String getCategoryName1() {
		return categoryName1;
	}

	//set方法
	public void setCategoryName1(String categoryName1) {
		this.categoryName1 = categoryName1;
	}
	//get方法
	public String getCategoryName2() {
		return categoryName2;
	}

	//set方法
	public void setCategoryName2(String categoryName2) {
		this.categoryName2 = categoryName2;
	}
	//get方法
	public String getCategoryName3() {
		return categoryName3;
	}

	//set方法
	public void setCategoryName3(String categoryName3) {
		this.categoryName3 = categoryName3;
	}


}
