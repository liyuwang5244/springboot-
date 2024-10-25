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
     * @param username 用户名
     * @param password 密码
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User login(String username, String password);

    /**
     * 修改密码
     * @param username 用户名
     * @param uid
     * @param oldPassword
     * @param newPassword
     */
    void changePassword (String username,
                         Integer uid,
                         String oldPassword,
                         String newPassword);

    User findByUid (Integer uid);

    void updateInfo (String username, Integer uid, User user);
}
