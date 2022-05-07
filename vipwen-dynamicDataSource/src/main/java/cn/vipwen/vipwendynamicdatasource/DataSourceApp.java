package cn.vipwen.vipwendynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.vipwen.vipwendynamicdatasource.mapper")
public class DataSourceApp {
    public static void main(String[] args) {
        SpringApplication.run(DataSourceApp.class,args);
    }
}
