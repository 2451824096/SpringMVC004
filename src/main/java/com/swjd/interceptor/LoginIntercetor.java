package com.swjd.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginIntercetor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri=request.getRequestURI();//获得请求地址
        //如果是登录页面我们就放行
        if (requestUri.indexOf("ogin")>=0||requestUri.indexOf("Main")>=0){
            return true;
        }
        //如果用户登录过
        HttpSession session=request.getSession();//获得session
        if (session.getAttribute("activeName")!=null){
            return true;
        }

        //不放行
        request.getRequestDispatcher("/toLogin").forward(request,response);
        return false;
    }
}
