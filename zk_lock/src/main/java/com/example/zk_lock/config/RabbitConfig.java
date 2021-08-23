package com.example.zk_lock.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * 队列
     */
    public static final String TEST_QUEUE = "testQueue";
    /**
     * 交换机
     */
    public static final String TEST_EXCHANGE = "testExchange";
    /**
     * key
     */
    public static final String TEST_KEY = "testKey";




    /**
     * 声明的队列
     *
     * @return 队列
     */
    @Bean
    public Queue testQueue() {

        return new Queue(TEST_QUEUE);
    }

    @Bean
    public DirectExchange testExchange() {
        return ExchangeBuilder.directExchange(TEST_EXCHANGE).build();
    }

    @Bean
    public Binding dlBinding(@Qualifier("testQueue") Queue testQueue, @Qualifier("testExchange") DirectExchange testExchange) {
        return BindingBuilder.bind(testQueue).to(testExchange).with(TEST_KEY);
    }


}
