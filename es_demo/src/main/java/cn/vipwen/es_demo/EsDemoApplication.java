package cn.vipwen.es_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = {"cn.vipwen.es_demo.mapper"})
@SpringBootApplication
public class EsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsDemoApplication.class, args);
    }

}
