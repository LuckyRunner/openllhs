package com.openllhs.article.service.impl;
import com.openllhs.article.dao.ArticleMapper;
import com.openllhs.article.dao.AuthorMapper;
import com.openllhs.article.dao.OrganizationMapper;
import com.openllhs.article.pojo.*;
import com.openllhs.article.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:Article业务层接口实现类
 * @Date 2020/5/12 0:18
 *****/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    public void saveArticle(ArticleVO articleVO) {
        System.out.println("1111111111111111111111");
        List<AuthorVO> authorList = articleVO.getAuthors();
        System.out.println("2222222222222222222222");
        Article article = articleVO.getArticle();
        System.out.println(article.toString());
        if(article.getId()==null){
            article.setId(idWorker.nextId());
            article.setVersion(1);
            article.setStatus("2");
            article.setCreateTime(new Date());
            articleMapper.insertSelective(article);

        }else {
            articleMapper.updateByPrimaryKeySelective(article);
            Author author = new Author();
            author.setArticleId(article.getId());
            List<Author> authors = authorMapper.select(author);
            for(Author each:authors){
                Organization organization = new Organization();
                organization.setAuthorId(each.getId());
                organizationMapper.delete(organization);
            }
            authorMapper.delete(author);
        }

        for(AuthorVO each : authorList){
            Author author = new Author();
            Long authorId = idWorker.nextId();
            author.setId(authorId);
            author.setArticleId(article.getId());
            author.setNameCn(each.getNameCn());
            author.setNameEn(each.getNameEn());
            author.setEmail(each.getEmail());
            author.setIsFirstAuthor(each.getIsFirstAuthor());
            author.setIsCorauthor(each.getIsCorauthor());
            author.setSeq(each.getSeq());
            authorMapper.insertSelective(author);
            for(Organization organization : each.getOrganizationList()){
                organizationMapper.insertSelective(organization);
            }
        }
    }

    @Override
    public ArticleVO findByArticleId(Long id) {

        Article article = articleMapper.selectByPrimaryKey(id);
        System.out.println(article.toString());
        ArticleVO articleVO = new ArticleVO();
        articleVO.setArticle(article);
        Author author = new Author();
        author.setArticleId(id);
        List<Author> authorList = authorMapper.select(author);
        List<AuthorVO> authorVOList = new ArrayList<>();
        for(Author each : authorList){
            AuthorVO authorVO = new AuthorVO();
            authorVO.setSeq(each.getSeq());
            authorVO.setArticleId(id);
            authorVO.setEmail(each.getEmail());
            authorVO.setId(each.getId());
            authorVO.setIsCorauthor(each.getIsCorauthor());
            authorVO.setNameCn(each.getNameCn());
            authorVO.setNameEn(each.getNameEn());
            authorVO.setIsFirstAuthor(each.getIsFirstAuthor());
            Organization organization = new Organization();
            organization.setAuthorId(each.getId());
            List<Organization> organizationList = organizationMapper.select(organization);
            authorVO.setOrganizationList(organizationList);
            authorVOList.add(authorVO);
        }
        articleVO.setAuthors(authorVOList);
        return articleVO;
    }

    @Override
    public void audit(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对已删除的商品进行审核！");
        }
        article.setStatus("1");
        article.setIsShow("1");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void pull(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对已删除的商品进行下架！");
        }

        article.setIsShow("0");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void put(Long articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article.getIsDelete().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对已删除的商品进行下架！");
        }
        if(!article.getStatus().equalsIgnoreCase("1")){
            throw new RuntimeException("不能对未审核商品进行上架！");
        }
        article.setIsShow("1");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void putMany(Long[] ids) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        criteria.andEqualTo("isDelete","0");
        criteria.andEqualTo("status","1");
        Article article =new Article();
        article.setIsShow("1");
        articleMapper.updateByExampleSelective(article,example);
    }

    /**
     * Article条件+分页查询
     * @param article 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Article> findPage(Article article, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(article);
        //执行搜索
        return new PageInfo<Article>(articleMapper.selectByExample(example));
    }

    /**
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Article> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Article>(articleMapper.selectAll());
    }

    /**
     * Article条件查询
     * @param article
     * @return
     */
    @Override
    public List<Article> findList(Article article){
        //构建查询条件
        Example example = createExample(article);
        //根据构建的条件查询数据
        return articleMapper.selectByExample(example);
    }


    /**
     * Article构建查询对象
     * @param article
     * @return
     */
    public Example createExample(Article article){
        Example example=new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        if(article!=null){
            // 论文id
            if(!StringUtils.isEmpty(article.getId())){
                    criteria.andEqualTo("id",article.getId());
            }
            // doi
            if(!StringUtils.isEmpty(article.getDoi())){
                    criteria.andEqualTo("doi",article.getDoi());
            }
            // 标题
            if(!StringUtils.isEmpty(article.getTitle())){
                    criteria.andLike("title","%"+article.getTitle()+"%");
            }
            // 摘要
            if(!StringUtils.isEmpty(article.getAAbstract())){
                    criteria.andEqualTo("aAbstract",article.getAAbstract());
            }
            // 论文内容
            if(!StringUtils.isEmpty(article.getContent())){
                    criteria.andEqualTo("content",article.getContent());
            }
            // 关键字
            if(!StringUtils.isEmpty(article.getKeyWords())){
                    criteria.andEqualTo("keyWords",article.getKeyWords());
            }
            // 论文首图片
            if(!StringUtils.isEmpty(article.getImage())){
                    criteria.andEqualTo("image",article.getImage());
            }
            // 论文内容图片列表
            if(!StringUtils.isEmpty(article.getImages())){
                    criteria.andEqualTo("images",article.getImages());
            }
            // pdf
            if(!StringUtils.isEmpty(article.getPdf())){
                    criteria.andEqualTo("pdf",article.getPdf());
            }
            // 以往版本pdf列表
            if(!StringUtils.isEmpty(article.getPdfs())){
                    criteria.andEqualTo("pdfs",article.getPdfs());
            }
            // 创建时间
            if(!StringUtils.isEmpty(article.getCreateTime())){
                    criteria.andEqualTo("createTime",article.getCreateTime());
            }
            // 更新时间
            if(!StringUtils.isEmpty(article.getUpdateTime())){
                    criteria.andEqualTo("updateTime",article.getUpdateTime());
            }
            //
            if(!StringUtils.isEmpty(article.getSubmitTime())){
                    criteria.andEqualTo("submitTime",article.getSubmitTime());
            }
            // 领域
            if(!StringUtils.isEmpty(article.getSubject())){
                    criteria.andEqualTo("subject",article.getSubject());
            }
            // 阅读数
            if(!StringUtils.isEmpty(article.getReadNum())){
                    criteria.andEqualTo("readNum",article.getReadNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(article.getCommentNum())){
                    criteria.andEqualTo("commentNum",article.getCommentNum());
            }
            // 下载数
            if(!StringUtils.isEmpty(article.getDownloadNum())){
                    criteria.andEqualTo("downloadNum",article.getDownloadNum());
            }
            // 审核状态
            if(!StringUtils.isEmpty(article.getStatus())){
                    criteria.andEqualTo("status",article.getStatus());
            }
            //
            if(!StringUtils.isEmpty(article.getVersion())){
                    criteria.andEqualTo("version",article.getVersion());
            }
            // 一级分类
            if(!StringUtils.isEmpty(article.getCategory1Id())){
                    criteria.andEqualTo("category1Id",article.getCategory1Id());
            }
            // 二级分类
            if(!StringUtils.isEmpty(article.getCategory2Id())){
                    criteria.andEqualTo("category2Id",article.getCategory2Id());
            }
            // 三级分类
            if(!StringUtils.isEmpty(article.getCategory3Id())){
                    criteria.andEqualTo("category3Id",article.getCategory3Id());
            }
            //
            if(!StringUtils.isEmpty(article.getIsDelete())){
                    criteria.andEqualTo("authorItems",article.getIsDelete());
            }
            if(!StringUtils.isEmpty(article.getIsShow())){
                criteria.andEqualTo("authorItems",article.getIsShow());
            }
        }
        return example;
    }

    @Override
    public List<Article> findByStatus(String status) {
        Article article = new Article();
        article.setStatus(status);
        return articleMapper.select(article);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        articleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Article
     * @param article
     */
    @Override
    public void update(Article article){
        articleMapper.updateByPrimaryKey(article);
    }

    /**
     * 增加Article
     * @param article
     */
    @Override
    public void add(Article article){
        articleMapper.insert(article);
    }

    /**
     * 根据ID查询Article
     * @param id
     * @return
     */
    @Override
    public Article findById(Long id){
        return  articleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Article全部数据
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }
}
