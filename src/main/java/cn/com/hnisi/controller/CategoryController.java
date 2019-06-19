package cn.com.hnisi.controller;

import cn.com.hnisi.domain.Category;
import cn.com.hnisi.exception.CategoryException;
import cn.com.hnisi.service.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manager")
public class CategoryController
{
    @Resource(name = "categoryService")
    private ICategoryService service;//分类相关服务实例

    /**
     * 分类添加页面跳转以及操作
     * @param add 跳转或服务操作识别
     * @param category 参数绑定的分类信息
     * @return ModelAndView 消息跳转页面以及消息携带
     * @throws Exception 分类相关统一异常处理  @TODO 暂未完成
     */
    @RequestMapping(value = {"/addCategory", "/addCategory/{add}"})
    public ModelAndView addCategory(@PathVariable(required = false) String add, Category category) throws Exception
    {
        ModelAndView mv = new ModelAndView();
        if (add == null)//跳转
            mv.setViewName("/manager/addCategory");
        else if ("add".equals(add))
        {
            try
            {
                Category byName = service.findCategoryByName(category.getName());
                if (byName != null) // 操作
                {
                    mv.addObject("category",category);
                    mv.addObject("error_message", "此分类已经存在");
                    mv.setViewName("/manager/addCategory");
                    return mv;
                }
                category.setId(UUID.randomUUID().toString());
                service.addCategory(category);
                mv.addObject("c_success_message", "添加分类成功！");
                mv.setViewName("/message");
            } catch (Exception e)
            {
                throw new CategoryException("添加分类失败！", e);
            }
        }
        return mv;
    }

    /**
     * 分类列表
     * @return ModelAndView
     * @throws Exception 统一异常处理
     */
    @RequestMapping("/ListCategory")
    public ModelAndView ListCategory() throws Exception
    {
        ModelAndView mv = new ModelAndView();
        List<Category> categories = service.findCategoryAll();
        mv.addObject("categories", categories);
        mv.setViewName("/manager/listcategory");

        return mv;
    }

    /**
     * 分类更新
     * @param id 更新分类id
     * @param category 参数绑定对象
     * @param model model
     * @return 更新页面跳转或消息跳转
     * @throws Exception 统一异常处理
     */
    @RequestMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable String id, Category category, Model model) throws Exception
    {
        if (category.getName() != null && category.getDescription() != null)
        {
            service.updateCategoryById(category);
            return "redirect:/manager/ListCategory";
        }else
        {
            model.addAttribute("id",id);//绑定要修改的分类id
            return "forward:/manager/ListCategory";
        }
    }

    /**
     * 分类逻辑删除
     * @param id 分类的id
     * @return 消息跳转
     * @throws Exception 统一异常处理
     */
    @RequestMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable String id) throws Exception
    {
        if (id == null)
        {
            throw new CategoryException("删除失败：ID不能为空！");
        }
        try
        {
            service.deleteCategoryById(id);

        } catch (Exception e)
        {
            throw new CategoryException("删除失败", e);
        }

        return "redirect:/manager/ListCategory";
    }

}
