package cn.com.hnisi.utils;

import cn.com.hnisi.domain.BookQueryBean;
import cn.com.hnisi.domain.BookQueryInfo;
import cn.com.hnisi.domain.BookQueryResult;
import cn.com.hnisi.service.IBookService;

public class ControllerUtils
{
    public static BookQueryBean getBookQueryBean(BookQueryInfo info, IBookService iBookService)
    {
        BookQueryResult result = iBookService.findBookByPage(info.getStartIndex(), info.getPageSize(), info.getParamMap());
        BookQueryBean bean = new BookQueryBean();
        bean.setBooks(result.getBookList());
        bean.setTotalRecord(result.getTotalRecord());
        bean.setCurrentPage(info.getCurrentPage());
        bean.setPageSize(info.getPageSize());
        bean.setParamMap(info.getParamMap());
        return bean;
    }
}
