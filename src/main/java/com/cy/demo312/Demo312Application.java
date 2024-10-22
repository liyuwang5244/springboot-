package com.cy.demo312;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//指定当前项目mapper的位置
@MapperScan("com.cy.demo312.mapper")
public class Demo312Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo312Application.class, args);
    }

}
