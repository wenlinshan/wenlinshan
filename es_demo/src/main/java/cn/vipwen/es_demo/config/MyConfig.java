package cn.vipwen.es_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author wls
 * @version 1.0
 * @date 2022/5/19 10:53
 */
@Configuration
public class MyConfig {

    @Bean
    public Executor executorThreadPool() {
        return new ThreadPoolExecutor(4, 8, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
