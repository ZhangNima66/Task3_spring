package cn.com.hnisi.service.impl;

import cn.com.hnisi.domain.Category;
import cn.com.hnisi.domain.CategoryExample;
import cn.com.hnisi.mapper.CategoryMapper;
import cn.com.hnisi.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private CategoryMapper mapper;

    @Override
    public void addCategory(Category category) throws Exception
    {
        mapper.insertSelective(category);
    }

    @Override
    public void deleteCategoryById(String id) throws Exception
    {
        Category category = mapper.selectByPrimaryKey(id);
        category.setIsdel((short) 1);
        mapper.updateByPrimaryKey(category);
    }

    @Override
    public Category findCategoryById(String id) throws Exception
    {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andIsdelEqualTo((short) 0);
        List<Category> categories = mapper.selectByExample(example);
        if (categories.isEmpty())
        {
            return null;
        }else if (categories.size() == 1)
        {
            return categories.get(0);
        }else
        {
            throw new Exception("查询异常");
        }
    }

    @Override
    public List<Category> findCategoryAll() throws Exception
    {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIsdelEqualTo((short) 0);
        return mapper.selectByExample(example);
    }

    @Override
    public void updateCategoryById(Category category) throws Exception
    {
        mapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Category findCategoryByName(String name)
    {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Category> categories = mapper.selectByExample(example);
        if (categories.isEmpty())
            return null;
        return categories.get(0);
    }
}
