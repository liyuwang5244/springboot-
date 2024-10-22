package com.cy.demo312.controller;

import com.cy.demo312.entity.User;
import com.cy.demo312.service.IUserService;
import com.cy.demo312.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cy.demo312.controller.BaseController.OK;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);

        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        return new JsonResult<User>(OK, data);
    }
}