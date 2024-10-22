package com.cy.demo312.service;

import com.cy.demo312.entity.User;

//用户模块业务层接口
public interface IUserService {
    /**
     * 用户注册
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User login(String username, String password);
}
