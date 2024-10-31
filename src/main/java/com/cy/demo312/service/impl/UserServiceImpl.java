package com.cy.demo312.service.impl;

import com.cy.demo312.entity.User;
import com.cy.demo312.mapper.UserMapper;
import com.cy.demo312.service.IUserService;
import com.cy.demo312.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/** 用户模块业务层实现 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //判断用户是否被注册过
        User result = userMapper.findByUsername(user.getUsername());
        //如果result不为null，则表示用户名被占用
        if (result != null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //加密
        String oldPassword = user.getPassword();
        //随机生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为整体加密处理
        String md5Password = getMD5(oldPassword, salt);
        user.setPassword(md5Password);
        user.setSalt(salt);

        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //注册业务层实现(rows == 1)
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("用户注册时产生未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);

        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }

        String salt = result.getSalt();
        String md5Password = getMD5(password, salt);
        String oldPassword = result.getPassword();
        if (!md5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密码错误");
        }

        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(String username, Integer uid, String oldPassword, String newPassword) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        String oldMd5Password = getMD5(oldPassword, user.getSalt());
        if (!user.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        //将新密码设置进数据库
        String newMd5Password = getMD5(newPassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public User findByUid(Integer uid) {
        User result = userMapper.findByUid(uid);

        if (result == null || result.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户不存在");
        }

        User user = new User();
        user.setUsername(result.getUsername());
        user.setEmail(result.getEmail());
        user.setPhone(result.getPhone());
        user.setGender(result.getGender());

        return user;
    }

    @Override
    public void updateInfo(String username, Integer uid, User user) {
        User result = userMapper.findByUid(uid);

        if (result == null || result.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户不存在");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete().equals(1)) {
            throw new UserNotFoundException("用户数据不存在");
        }

        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    /** 定义一个md5加密处理算法 */
    private String getMD5(String password, String salt) {
        for (int i = 0;i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }



}
