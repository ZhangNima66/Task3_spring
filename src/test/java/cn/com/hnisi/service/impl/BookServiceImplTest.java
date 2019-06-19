package cn.com.hnisi.service.impl;

import cn.com.hnisi.domain.*;
//import cn.com.hnisi.mapper.OrderitemMapper;
import cn.com.hnisi.service.IBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class BookServiceImplTest
{
    @Autowired
    protected IBookService service;

//    @Autowired
//    protected OrderitemMapper mapper;
    @Test
    public void addBook()
    {
    }

/*    @Test
    public void deleteBookById()
    {
        OrderitemCustom orderitemCustom = mapper.selectCustomByPrimaryKey("606c867a-7391-42e8-9e5e-20c55f3d1132");
        System.out.println(orderitemCustom);

        BookCustom book = orderitemCustom.getBook();
        System.out.println(book);

        Category category = book.getCategory();
        System.out.println(category);


    }*/

    @Test
    public void findBookById() throws Exception
    {
        BookCustom bookCustomById = service.findBookCustomById("34323");
        System.out.println(bookCustomById);

        Category category = bookCustomById.getCategory();
        System.out.println(category);
    }

    @Test
    public void findBookAll()
    {

    }

    @Test
    public void findBookByPage()
    {
        BookQueryResult result = service.findBookByPage(1, 5, null);
        System.out.println(result);
    }

    @Test
    public void updateBookById()
    {
        Map<String, String> map = new HashMap<>();
        map.put("a","1");

        String b = map.get("b");
        System.out.println(b);

    }
}