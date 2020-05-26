package com.openllhs.article.service;
import com.openllhs.article.pojo.CategorySubject;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:duqiang
 * @Description:CategorySubject业务层接口
 * @Date 2020/5/12 0:18
 *****/
public interface CategorySubjectService {

    /***
     * CategorySubject多条件分页查询
     * @param categorySubject
     * @param page
     * @param size
     * @return
     */
    PageInfo<CategorySubject> findPage(CategorySubject categorySubject, int page, int size);

    /***
     * CategorySubject分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<CategorySubject> findPage(int page, int size);

    /***
     * CategorySubject多条件搜索方法
     * @param categorySubject
     * @return
     */
    List<CategorySubject> findList(CategorySubject categorySubject);

    /***
     * 删除CategorySubject
     * @param id
     */
    void delete(Long id);

    /***
     * 修改CategorySubject数据
     * @param categorySubject
     */
    void update(CategorySubject categorySubject);

    /***
     * 新增CategorySubject
     * @param categorySubject
     */
    void add(CategorySubject categorySubject);

    /**
     * 根据ID查询CategorySubject
     * @param id
     * @return
     */
     CategorySubject findById(Long id);

    /***
     * 查询所有CategorySubject
     * @return
     */
    List<CategorySubject> findAll();
}
