package com.cy.demo312.mapper;

import com.cy.demo312.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数（增删改查）
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 找到返回用户数据，未找到返回null
     */
    User findByUsername(String username);

    /**
     * 根据uid修改用户密码
     * @param uid 用户id
     * @param password 密码
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                               String password,
                               String modifiedUser,
                               Date modifiedTime);

    /**
     * 根据uid查询用户数据
     * @param uid 用户id
     * @return 返回值为用户数据
     */
    User findByUid (Integer uid);

    Integer updateInfoByUid(User user);
}
