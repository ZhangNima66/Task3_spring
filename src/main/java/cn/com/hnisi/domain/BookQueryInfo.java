package cn.com.hnisi.domain;

import java.util.HashMap;
import java.util.Map;

public class BookQueryInfo
{
    private int currentPage = 1;
    private int pageSize = 0;
    private int startIndex;
    private Map<String, String[]> paramMap = new HashMap<>();

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getStartIndex()
    {
        this.startIndex = (this.currentPage - 1) * this.pageSize + 1;
        return startIndex;
    }

    public Map<String, String[]> getParamMap()
    {
        return paramMap;
    }

    public void setParamMap(Map<String, String[]> paramMap)
    {
        this.paramMap = paramMap;
    }
}
