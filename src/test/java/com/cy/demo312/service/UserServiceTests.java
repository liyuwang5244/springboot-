package com.cy.demo312.service;

import com.cy.demo312.entity.User;
import com.cy.demo312.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("test004");
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

    @Test
    public void changePassword () {
        userService.changePassword("管理员", 12, "123456", "123");
    }

    @Test
    public void findByUid () {
            User user = userService.findByUid(12);
            System.out.println(user);
    }

    @Test
    public void updateInfo () {
            User user = new User();
            user.setEmail("test@qq.com");
            user.setPhone("27858802222");
            user.setGender(1);
            userService.updateInfo("管理员", 12, user);
    }
}
