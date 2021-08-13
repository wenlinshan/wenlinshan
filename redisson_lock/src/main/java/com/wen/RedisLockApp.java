package com.wen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wenlinshan
 * @version 1.0
 * @date 2021/6/15 10:11
 * @desc 启动类
 */
@SpringBootApplication
@MapperScan("com.wen.mapper")
public class RedisLockApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisLockApp.class, args);
    }
}
