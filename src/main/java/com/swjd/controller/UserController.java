package com.swjd.controller;

import com.swjd.bean.User;
import com.swjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    //去登陆
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        User user=new User();
        model.addAttribute("user",user);

        return "login";
    }
    //做登录
    @RequestMapping("/doLogin")
    public String doLogin(Model model, User user, HttpSession session){
        User u=userService.login(user);
        if (u!=null){
            //账号密码没有问题
            if (u.getFlag().equals("1")){
                //登陆成功把名字存到session
                session.setAttribute("activeName",u.getUname());
                return "redirect:/customerController/toMain";
            }else {
                //账号被禁用了
                model.addAttribute("user",user);
                model.addAttribute("errorMsg","该账号被禁用，请联系管理员");
                return "login";
            }
        }else {
            //账号密码错了
            User user1=new User();
            model.addAttribute("user",user1);
            model.addAttribute("errorMsg","账号或者密码错误");
            return "login";
        }
    }

    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session,Model model){
        session.invalidate();
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }
    @RequestMapping("/logOne")
    public String logOne(){
        return "logOne";
    }
    @RequestMapping("/logTwe")
    public String logTwe(){
        return "logTwe";
    }
}
