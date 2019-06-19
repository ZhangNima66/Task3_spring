package cn.com.hnisi.mapper;

import cn.com.hnisi.domain.Book;
import cn.com.hnisi.domain.BookCustom;
import cn.com.hnisi.domain.BookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookMapper
{
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(String id);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    List<Book> selectByExampleAndPage(BookExample example);

    BookCustom selectCustomByPrimaryKey(String id);

    int selectByExampleCount(BookExample bookExample);

    Book selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

}