package com.wen.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;

/**
 * @author wenlinshan
 * @version 1.0
 * @date 2021/6/16 10:57
 * @desc
 */
//@Configuration
public class RedissonConfig {

   /* @Value("${redisson.address}")
    private String addressUrl;
    @Value("${redisson.password}")
    private String password;*/


    /**
     * 获取连接客户端
     *
     * @return org.redisson.api.RedissonClient
     * @Description //TODO 单机模式配置
     **/
    /*@Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(addressUrl).setPassword(password)
                .setReconnectionTimeout(10000)
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000);
        return Redisson.create(config);
    }*/

    /**
     * @return
     * @Description //TODO 主从模式
     * @Date 20203/19 10:54
     * @Param
     **/
    /** @Bean public RedissonClient getRedisson() {
    RedissonClient redisson;
    Config config = new Config();
    config.useMasterSlaveServers()
    //可以用"rediss://"来启用SSL连接
    .setMasterAddress("redis://***(主服务器IP):6379").setPassword("web2017")
    .addSlaveAddress("redis://***（从服务器IP）:6379")
    .setReconnectionTimeout(10000)
    .setRetryInterval(5000)
    .setTimeout(10000)
    //（连接超时，单位：毫秒 默认值：3000）;
    .setConnectTimeout(10000);
    //  哨兵模式config.useSentinelServers().setMasterName("mymaster").setPassword("web2017").addSentinelAddress("***(哨兵IP):26379", "***(哨兵IP):26379", "***(哨兵IP):26380");
    redisson = Redisson.create(config);
    return redisson;
    }
    }*/

    //@Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        return Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson-single.yml").getInputStream()));
        //return redisson;
    }


}
