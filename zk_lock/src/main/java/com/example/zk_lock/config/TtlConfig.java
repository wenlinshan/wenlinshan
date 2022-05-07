package com.example.zk_lock.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wls
 */
@Configuration
public class TtlConfig {
    /**
     * ttl(延时)消息交换机名称
     */
    public static final String TTL_EXCHANGE = "ttlExchange";

    /**
     * ttl(延时)消息队列名称
     */
    public static final String TTL_QUEUE = "ttlQueue";
    /**
     * ttl(延时)key
     */
    public static final String TTL_KEY = "ttlKey";
    /**
     * 死信queue
     */
    public static final String DELAY_QUEUE = "delayQueue";
    /**
     * 死信交换机
     */
    public static final String DELAY_EXCHANGE = "delayExchange";

    /**
     * 死信key
     */
    public static final String DELAY_KEY = "delayKey";



    /**
     * 死信队列
     * durable 为持久队列创建构建器
     * withArgument 最终队列将包含用于声明队列的参数。
     * build 建立最终队列。
     *
     * @return 队列
     */
    @Bean(TTL_QUEUE)
    public Queue ttlQueue() {
        Map<String, Object> map = new HashMap<>(3);
        // 10 秒钟后成为死信
        //map.put("x-message-ttl", 10000);
        // 队列中的消息变成死信后，进入死信交换机
        map.put("x-dead-letter-exchange", DELAY_EXCHANGE);
        return new Queue(TTL_QUEUE,true,false,false,map);
    }

    /**
     * TTL 消息交换机配置
     *
     * @return 路由key交换机
     */
    @Bean(TTL_EXCHANGE)
    public DirectExchange ttlExchange() {
        return ExchangeBuilder.directExchange(TTL_EXCHANGE).durable(true).build();
    }

    /**
     * TTL绑定
     *
     * @return 绑定关系
     */
    @Bean
    public Binding ttlBinding(@Qualifier(TTL_QUEUE) Queue queue, @Qualifier(TTL_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TTL_KEY);
    }

    /**
     * 死信队列
     *
     * @return 队列
     */
    @Bean("dlxQueue")
    public Queue dlxQueue() {
        return QueueBuilder.durable(DELAY_QUEUE).build();
    }
    /**
     * 死信交换、机
     *
     * @return 交换机
     */
    @Bean("dlxExchange")
    public TopicExchange dlxExchange() {
        return ExchangeBuilder.topicExchange(DELAY_EXCHANGE).build();
    }


    /**
     * 绑定关系
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return 绑定
     */
    @Bean
    public Binding dlxBinding(@Qualifier("dlxQueue") Queue queue, @Qualifier("dlxExchange") TopicExchange exchange) {
        //不需要key
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }
}
