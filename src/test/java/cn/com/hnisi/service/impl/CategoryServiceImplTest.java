package cn.com.hnisi.service.impl;

import cn.com.hnisi.domain.Category;
import cn.com.hnisi.service.ICategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class CategoryServiceImplTest
{
    @Autowired
    private ICategoryService service;

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void addCategory()
    {
        System.out.println(service);

    }

    @Test
    public void deleteCategoryById()
    {
    }

    @Test
    public void findCategoryById() throws Exception
    {
        Category category = service.findCategoryById("1");
        System.out.println(category);
    }

    @Test
    public void findCategoryAll() throws Exception
    {
        List<Category> all = service.findCategoryAll();
        System.out.println(all);
    }

    @Test
    public void updateCategory()
    {
    }
}