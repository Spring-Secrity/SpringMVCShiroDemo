package com.anlu.shiro.controller;

import com.anlu.common.model.UUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("authc")
public class AuthController {
    // /authc/** = authc 任何通过表单登录的用户都可以访问
    @RequestMapping("anyuser")
    public ModelAndView anyuser() {
        Subject subject = SecurityUtils.getSubject();
        UUser user = (UUser) subject.getSession().getAttribute("user");
        System.out.println(user);
        return new ModelAndView("inner");
    }

    // /authc/admin = user[admin] 只有具备admin角色的用户才可以访问，否则请求将被重定向至登录界面
    @RequestMapping("admin")
    public ModelAndView admin() {
        Subject subject = SecurityUtils.getSubject();
        UUser user = (UUser) subject.getSession().getAttribute("user");
        System.out.println(user);
        return new ModelAndView("inner");
    }
}
