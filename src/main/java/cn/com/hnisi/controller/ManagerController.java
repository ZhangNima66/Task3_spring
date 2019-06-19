package cn.com.hnisi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController
{
    /**
     * 首页分页各页面跳转
     * @param resource 页面指定
     * @return 跳转页面
     */
    @RequestMapping(value = {"","/{resource}"})
    public String UI(@PathVariable(required = false) String resource)
    {
        if (resource == null)
            return "/manager/manager";
        else if ("head".equals(resource))
            return "/manager/head";
        else if ("left".equals(resource))
            return "/manager/left";
        return null;
    }

    /**
     * 消息跳转页面
     * @return 消息页面
     */
    @RequestMapping("/message")
    public String message()
    {
        return "/message";
    }
}
