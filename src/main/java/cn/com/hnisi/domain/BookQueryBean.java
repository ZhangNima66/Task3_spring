package cn.com.hnisi.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookQueryBean
{
    //记录相关
    private List<Book> books;
    private Integer totalRecord;
    //分页相关
    private int pageSize;       //每页的记录数
    private int totalPage;      //页面总数
    private int currentPage;    //当前页码
    private int previousPage;   //上一页页码
    private int nextPage;       //下一页页码
    private int[] pageBar;      //页码数组
    private Map<String, String[]> paramMap = new HashMap<>();

    public Map<String, String[]> getParamMap()
    {
        return paramMap;
    }

    public void setParamMap(Map<String, String[]> paramMap)
    {
        this.paramMap = paramMap;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    public Integer getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPage()
    {
        this.totalPage = (this.totalRecord + (this.pageSize - 1)) / this.pageSize;
        return totalPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getPreviousPage()
    {
        if (this.currentPage - 1 < 1)
        {
            this.previousPage = 1;
        } else
            this.previousPage = this.currentPage - 1;
        return previousPage;
    }

    public int getNextPage()
    {
        if (this.currentPage + 1 > getTotalPage())
        {
            this.nextPage = this.totalPage;
        } else
            this.nextPage = this.currentPage + 1;
        return nextPage;
    }

    public int[] getPageBar()
    {
        int startPage;
        int endPage;
        if (getTotalPage() <= 10)
        {
            startPage = 1;
            endPage = this.totalPage;
        } else
        {
            startPage = this.currentPage - 4;
            endPage = this.currentPage + 5;
            if (startPage < 1)
            {
                startPage = 1;
                endPage = 10;
            }
            if (endPage > this.totalPage)
            {
                startPage = this.totalPage - 9;
                endPage = this.totalPage;
            }
        }
        this.pageBar = new int[endPage - startPage +1];
        for (int i = startPage; i<= endPage; i++)
        {
            pageBar[i - startPage] = i;
        }

        return pageBar;
    }
}
