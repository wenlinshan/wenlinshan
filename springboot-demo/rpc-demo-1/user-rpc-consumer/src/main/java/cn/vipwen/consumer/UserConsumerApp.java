package cn.vipwen.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wls
 */
@ImportResource("classpath:dubbo.xml")
@SpringBootApplication
public class UserConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApp.class, args);
    }
}
