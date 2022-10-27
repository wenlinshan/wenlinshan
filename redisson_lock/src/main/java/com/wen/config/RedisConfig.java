package com.wen.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
//@Configuration
public class RedisConfig {
    //@Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
        // 配置连接工厂
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        ObjectMapper om = new ObjectMapper();
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 值采用json序列化
        jacksonSeial.setObjectMapper(om);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setValueSerializer(jacksonSeial);
        // 设置hash key 和value序列化模式
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        return template;
    }



}
