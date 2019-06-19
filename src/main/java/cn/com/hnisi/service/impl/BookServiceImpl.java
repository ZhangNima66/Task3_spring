package cn.com.hnisi.service.impl;

import cn.com.hnisi.domain.Book;
import cn.com.hnisi.domain.BookCustom;
import cn.com.hnisi.domain.BookExample;
import cn.com.hnisi.domain.BookQueryResult;
import cn.com.hnisi.mapper.BookMapper;
import cn.com.hnisi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class BookServiceImpl implements IBookService
{
    @Autowired
    private BookMapper mapper;

    @Override
    public void addBook(Book book) throws Exception
    {
        mapper.insertSelective(book);
    }

    @Override
    public void deleteBookById(String id) throws Exception
    {
        Book book = mapper.selectByPrimaryKey(id);
        book.setIsdel((short) 1);
        mapper.updateByPrimaryKey(book);
    }

    @Override
    public Book findBookByIdNotDel(String id) throws Exception
    {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andIsdelEqualTo((short) 0).andIdEqualTo(id);
        List<Book> books = mapper.selectByExample(example);
        if (books.isEmpty())
        {
            return null;
        }else if (books.size() == 1)
        {
            return  books.get(0);
        }else
        {
            throw new Exception("查询书籍异常");
        }
    }

    @Override
    public BookCustom findBookCustomByIdNotDel(String id) throws Exception
    {
        return null;
    }

    @Override
    public Book findBookById(String id) throws Exception
    {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public BookCustom findBookCustomById(String id) throws Exception
    {
        return mapper.selectCustomByPrimaryKey(id);
    }

    @Override
    public List<Book> findBookAllNotDel() throws Exception
    {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andIsdelEqualTo((short) 0);
        return mapper.selectByExample(example);
    }

    @Override
    public List<Book> findBookAll() throws Exception
    {
        return mapper.selectByExample(null);
    }


    /**
     * 分页查询
     * @param startIndex 分页起始页
     * @param pageSize 每一个的记录数
     * @param paramMap 查找书籍的种类的id
     * @return 结果集：书籍列表、书籍总数
     */
    @Override
    public BookQueryResult findBookByPage(int startIndex, int pageSize, Map<String,String[]> paramMap)
    {
        BookQueryResult bookQueryResult = new BookQueryResult();
        //查询条件
        BookExample bookExample = new BookExample();
        bookExample.setStartIndex(startIndex);
        bookExample.setEndIndex(startIndex + pageSize);
        BookExample.Criteria criteria = bookExample.createCriteria();
        //查询条件解析
        queryKey(paramMap, criteria);
        //查询结果
        List<Book> bookList = mapper.selectByExampleAndPage(bookExample);
        int totalRecord = mapper.selectByExampleCount(bookExample);
        bookQueryResult.setBookList(bookList);
        bookQueryResult.setTotalRecord(totalRecord);

        return bookQueryResult;
    }

    /**
     * 查询条件解析
     * @param paramMap 查询条件
     * @param criteria 查询条件集合器
     */
    private void queryKey(Map<String, String[]> paramMap, BookExample.Criteria criteria)
    {
        for (Map.Entry<String,String[]> entry : paramMap.entrySet())
        {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if ("name".equals(key) && values.length ==1 && !"".equals(values[0]))
            {
                criteria.andNameLike("%"+values[0]+"%");
            }else if("author".equals(key) && values.length ==1 && !"".equals(values[0]))
            {
                criteria.andAuthorLike("%"+values[0]+"%");
            }else if("price".equals(key) && values.length ==2)
            {
                if (!"".equals(values[0]))
                {
                    if (!"".equals(values[1]))
                    {
                        criteria.andPriceBetween(new BigDecimal(values[0]), new BigDecimal(values[1]));
                    } else
                    {
                        criteria.andPriceGreaterThanOrEqualTo(new BigDecimal(values[0]));
                    }
                }else
                {
                    if (!"".equals(values[1]))
                    {
                        criteria.andPriceLessThanOrEqualTo(new BigDecimal(values[1]));
                    }
                }
            }else if("publicationDate".equals(key) && values.length ==2)
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try
                {


                    if (!"".equals(values[0]))
                    {
                        Date date1 = dateFormat.parse(values[0]);
                        if (!"".equals(values[1]))
                        {
                            Date date2 = dateFormat.parse(values[1]);
                            criteria.andPublicationdateBetween(date1,date2);
                        }else
                        {
                            criteria.andPublicationdateGreaterThanOrEqualTo(date1);
                        }
                    }else
                    {
                        if (!"".equals(values[1]))
                        {
                            Date date2 = dateFormat.parse(values[1]);
                            criteria.andPublicationdateLessThanOrEqualTo(date2);
                        }
                    }
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }else if("tags".equals(key) && values.length > 0)
            {
                for (String value : values)
                {
                    criteria.andDescriptionLike("%"+value+"%");
                }
            }else if("categoryId".equals(key) && values.length > 0)
            {
                List<String> strings = Arrays.asList(values);
                criteria.andCategoryIdIn(strings);
            }else if("isDel".equals(key) && values.length == 1 && !"".equals(values[0]))
            {
                criteria.andIsdelEqualTo(Short.valueOf(values[0]));
            }
        }
    }

    @Override
    public void updateBookById(Book book) throws Exception
    {
        mapper.updateByPrimaryKey(book);
    }

    @Override
    public Book findBookByName(String name)
    {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Book> books = mapper.selectByExample(example);
        if (books.isEmpty())
            return null;
        else
            return books.get(0);
    }


}

