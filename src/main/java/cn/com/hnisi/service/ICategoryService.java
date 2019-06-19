package cn.com.hnisi.service;

import cn.com.hnisi.domain.Category;

import java.util.List;

/**
 * 分类服务接口
 */
public interface ICategoryService
{
    //增
    void addCategory(Category category) throws Exception;
    //删
    void deleteCategoryById(String id) throws Exception;
    //查
    Category findCategoryById(String id) throws Exception;
    //查询所有
    List<Category> findCategoryAll() throws Exception;
    //改
    void updateCategoryById(Category category) throws Exception;
    //通过条件name查询
    Category findCategoryByName(String name);
}
