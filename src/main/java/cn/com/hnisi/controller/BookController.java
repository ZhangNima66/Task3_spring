package cn.com.hnisi.controller;

import cn.com.hnisi.domain.*;
import cn.com.hnisi.exception.BookException;
import cn.com.hnisi.service.IBookService;
import cn.com.hnisi.service.ICategoryService;
import cn.com.hnisi.utils.ControllerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manager")
public class BookController
{
    @Resource(name = "bookService")
    private IBookService service; //书籍相关服务实例
    @Resource(name = "categoryService")
    private ICategoryService categoryService; //分类相关服务实例

    /**
     * 添加书籍UI跳转，附加分类和标签 数据对象跳转
     * @param model 携带 分类和标签 实例对象
     * @return 添加书籍表单页面
     * @throws Exception @TODO 全局异常处理 暂未编写。
     */
    @RequestMapping("/addBook")
    public String addBook(Model model) throws Exception
    {
        String[] tags = Tag.tags;
        List<Category> categoryAll = categoryService.findCategoryAll();
        model.addAttribute("tags",tags);
        model.addAttribute("categoryAll", categoryAll);
        return "/manager/addBook";
    }

    /**
     * 添加书籍controller,
     * @param model 模板
     * @param book 表单绑定对象
     * @param image_pic 提交的图片
     * @return 添加成功页面
     * @throws BookException 书籍处理异常类
     * @throws IOException 存储图片时可能异常
     */
    @RequestMapping("/add")
    public String add(Model model, Book book, String[] tags, MultipartFile image_pic) throws BookException, IOException
    {
        Book bookByName = service.findBookByName(book.getName());
        if (bookByName != null)
        {
            model.addAttribute("check_tag",tags);
            model.addAttribute("book",book);
            model.addAttribute("error_message","书名已存在");
            return "forward:/manager/addBook";
        }
        saveImage(book, image_pic);
        try
        {
            book.setId(UUID.randomUUID().toString());
            String join = StringUtils.join(tags, "，");
            book.setDescription(join);
            service.addBook(book);
            model.addAttribute("success_message", "添加书籍成功！");
        } catch (Exception e)
        {
            throw new BookException("添加书籍失败！");
        }
        return "/message";
    }

    /**
     * 书籍列表，分页查询
     * @param model "/listBook"：默认分页条件 ；"/listBook/{currentPage}/{pageSize}"：带分页条件跳转
     * @param info 查询条件包装类，包含分页条件、查询条件
     * @return 分页数据展示页面
     * @throws Exception
     */
    @RequestMapping(value = {"/listBook","/listBook/{currentPage}/{pageSize}"})
    public String listBook(Model model, BookQueryInfo info) throws Exception
    {
        if (info.getPageSize() == 0)
        {
            info.setPageSize(5);
        }
        BookQueryBean bean = ControllerUtils.getBookQueryBean(info, service);
        List<Category> categories = categoryService.findCategoryAll();
        model.addAttribute("bean", bean);
        model.addAttribute("categories", categories);
        model.addAttribute("tags",Tag.tags);
        return "/manager/listbook";
    }

    /**
     * 书籍信息更新 页面跳转（附带后台数据跳转）
     * @param id 指定需要更新数据的书籍的 ID
     * @param model model
     * @return 更新表单页面
     * @throws Exception 统一异常处理
     */
    @RequestMapping("/updateBook/{id}")
    public String updateBook(@PathVariable String id, Model model) throws Exception
    {
        if (id == null)
        {
            throw new BookException("更新错误：ID为空");
        }
        Book book = service.findBookById(id);
        String[] check_tag = book.getDescription().split("，");
        List<Category> categoryAll = categoryService.findCategoryAll();
        model.addAttribute("book", book);
        model.addAttribute("categoryAll", categoryAll);
        model.addAttribute("tags",Tag.tags);
        model.addAttribute("check_tag",check_tag);
        return "/manager/updateBook";
    }

    /**
     * 书籍更新实施操作
     * @param model model
     * @param book 页面提交的更新数据 参数绑定对象
     * @param tags 标签字符串数组
     * @param image_pic 上传的图片
     * @return 更新成功或失败跳转页面
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update(Model model, Book book,String[] tags,  MultipartFile image_pic) throws Exception
    {
        saveImage(book, image_pic);
        String join = StringUtils.join(tags, "，");
        book.setDescription(join);
        service.updateBookById(book);
        model.addAttribute("update_message", "修改书籍成功！");
        return "/message";
    }

    /**
     * 书籍删除操作
     * @param id 要删除的书籍的id
     * @return 消息页面
     * @throws Exception 统一异常处理
     */
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable String id) throws Exception
    {
        if (id == null)
        {
            throw new BookException("删除错误：ID为空！");
        }
        service.deleteBookById(id);
        return "forward:/manager/listBook";
    }

    /**
     * 图片存储到本地提取方法
     * @param book 处理位置绑定到book实例类
     * @param image_pic 图片
     * @throws IOException IO异常
     */
    private void saveImage(Book book, MultipartFile image_pic) throws IOException
    {
        if (image_pic != null)
        {
            String fileName = image_pic.getOriginalFilename();
            if (fileName != null && !"".equals(fileName.trim()))
            {
                String pic_savePath = "D:\\Tomcat_upload\\Task3_Spring";
                String pic_saveName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
                File saveFile = new File(pic_savePath + pic_saveName);
                image_pic.transferTo(saveFile);
                //将存储在硬盘中的文件名 随bean持久化到数据库；
                book.setImage(pic_saveName);
            }
        }
    }

}
