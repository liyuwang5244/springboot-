package com.cy.demo312.mapper;

import com.cy.demo312.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//表示启动这个单元测试类，必须传递一个参数，必须是SpringRunner类型
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin32131");
        user.setPassword("admin131231");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("admin32131");
        System.out.println(user);
    }
}
