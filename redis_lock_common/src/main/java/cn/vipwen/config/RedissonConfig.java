package cn.vipwen.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wls
 * @date 2021/12/14 15:42
 * @desc TODO
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient getRedissonClient(RedissonProperties redissonProperties){
        return Redisson.create(redissonProperties);
    }
}
