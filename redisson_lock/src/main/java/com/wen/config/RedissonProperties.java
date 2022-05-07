package com.wen.config;

import lombok.Data;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wls
 * @date 2021/12/14 14:58
 * @desc TODO
 */
//@Data
//@Component
//@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties extends Config {

    private String name;
}
