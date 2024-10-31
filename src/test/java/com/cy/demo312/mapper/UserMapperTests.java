package com.cy.demo312.mapper;

import com.cy.demo312.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
//表示启动这个单元测试类，必须传递一个参数，必须是SpringRunner类型
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin321311");
        user.setPassword("admin1312311");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("admin32131");
        System.out.println(user);
    }

    @Test
    public void updatePasswordById() {
        userMapper.updatePasswordByUid(5, "123" , "管理员", new Date());
    }

    @Test
    public void findByUid () {
        System.out.println(userMapper.findByUid(5));
    }

    @Test
    public void updateInfoByUid () {
        User user = new User();
        user.setUid(12);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender(1);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateAvatarByUid () {
        userMapper.updateAvatarByUid(
                12,
                "/upload/atatar.png",
                "管理员",
                new Date());
    }
}
