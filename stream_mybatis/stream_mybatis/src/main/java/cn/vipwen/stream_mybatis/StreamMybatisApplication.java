package cn.vipwen.stream_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.vipwen.stream_mybatis.mapper")
@SpringBootApplication
public class StreamMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamMybatisApplication.class, args);
    }

}
