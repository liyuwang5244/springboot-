package com.cy.demo312;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Demo312ApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {

    }

    /*
    * 数据库连接池：
    * 1.DBCP
    * 2.C3P0
    * 3.Hikari
    */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
