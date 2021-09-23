package com.example.zk_lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.zk_lock.mapper")
@SpringBootApplication
public class ZkLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkLockApplication.class, args);
    }

}
