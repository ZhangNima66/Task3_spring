package cn.com.hnisi.domain;

import java.util.List;

public class BookQueryResult
{
    private List<Book> bookList;
    private Integer totalRecord;

    public List<Book> getBookList()
    {
        return bookList;
    }

    public void setBookList(List<Book> bookList)
    {
        this.bookList = bookList;
    }

    public Integer getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord)
    {
        this.totalRecord = totalRecord;
    }
}
