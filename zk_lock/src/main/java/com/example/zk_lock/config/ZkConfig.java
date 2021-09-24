package com.example.zk_lock.config;

import com.example.zk_lock.util.DistributedLockUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkConfig {

    @Bean
    public DistributedLockUtil distributedLockUtil(){
        return new DistributedLockUtil();
    }
}
