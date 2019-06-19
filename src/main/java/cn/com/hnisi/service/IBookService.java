package cn.com.hnisi.service;

import cn.com.hnisi.domain.Book;
import cn.com.hnisi.domain.BookCustom;
import cn.com.hnisi.domain.BookQueryResult;

import java.util.List;
import java.util.Map;

/**
 * 有关 书籍信息 的服务接口
 */
public interface IBookService
{
    //增
    void addBook(Book book) throws Exception;
    //删
    void deleteBookById(String id) throws Exception;
    //通过条件isDel查询
    Book findBookByIdNotDel(String id) throws Exception;
    //通过条件isDel查询携带分类对象的Book
    BookCustom findBookCustomByIdNotDel(String id) throws Exception;
    //查
    Book findBookById(String id) throws Exception;
    //查 携带分类对象的Book
    BookCustom findBookCustomById(String id) throws Exception;
    //查所有 isDel
    List<Book> findBookAllNotDel() throws Exception;
    //查所有
    List<Book> findBookAll() throws Exception;

    /**
     * 分页查询
     * @param startIndex 分页起始页
     * @param pageSize 每一个的记录数
     * @param paramMap 查找书籍的种类的id
     * @return 结果集：书籍列表、书籍总数
     */
    BookQueryResult findBookByPage(int startIndex, int pageSize, Map<String,String[]> paramMap);
    //更新
    void updateBookById(Book book) throws Exception;
    //通过条件name 查询
    Book findBookByName(String name);
}
