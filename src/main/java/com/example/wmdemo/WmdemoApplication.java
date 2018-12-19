package com.example.wmdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wmdemo.mapper")
public class WmdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmdemoApplication.class, args);
    }

}

