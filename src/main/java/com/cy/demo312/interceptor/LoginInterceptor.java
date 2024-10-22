package com.cy.demo312.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/** 定义一个拦截器 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            //重定向到login.html
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
