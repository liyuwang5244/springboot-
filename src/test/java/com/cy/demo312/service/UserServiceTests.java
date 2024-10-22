package com.cy.demo312.service;

import com.cy.demo312.entity.User;
import com.cy.demo312.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("root2");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("注册成功");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }
    }

    @Test
    public void login(){
        User user = userService.login("test003","123456");
        System.out.println(user);
    }
}
