package com.joe.springbootshiro.controller;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class testController {

    @GetMapping("/test")
    public String getTest(){
        return "test";
    }


    @GetMapping("/add")
    public String add(){
        return "user/add";
    }

    @GetMapping("/update")
    public String update(){
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

    @PostMapping("/Login")
    public String toLogin(String name, String password, Model model){
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户名密码
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法
        try {
            subject.login(token);
            return "redirect:/test";
        }catch (UnknownAccountException e){
            //登录失败，用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "Login";
        }catch (IncorrectCredentialsException e){
            //登录失败，密码错误
            model.addAttribute("msg","密码错误");
            return "Login";
        }
    }
}
