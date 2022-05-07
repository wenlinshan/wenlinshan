package cn.vipwen.config;

import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wls
 * @date 2021/12/14 15:40
 * @desc TODO
 */

@Component
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties extends Config {
    private String name;
    private Integer age;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
