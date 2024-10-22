package com.cy.demo312.mapper;

import com.cy.demo312.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
}
