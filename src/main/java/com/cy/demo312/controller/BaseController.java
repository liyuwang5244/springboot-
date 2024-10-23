package com.cy.demo312.controller;

import com.cy.demo312.service.ex.*;
import com.cy.demo312.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户名未被找到");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("密码不正确");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新数据时产生未知的异常");
        }
        return result;
    }

    /**
     * 获取session对象的uid
     * @param session
     * @return
     */

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
