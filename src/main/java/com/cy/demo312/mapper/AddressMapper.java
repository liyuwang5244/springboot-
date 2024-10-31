package com.cy.demo312.mapper;

import com.cy.demo312.entity.Address;

public interface AddressMapper {

    /**
     * 插入地址
     * @param address 地址
     * @return 受影响的行数
     */
    Integer insert (Address address);

    /**
     * 获取收货地址数量
     * @param uid 用户id
     * @return 地址数量
     */
    Integer countByUid (Integer uid);
}
