package com.example.zk_lock.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PluginConfig {
    //延迟消费队列
    public static final String ACT_DELAY_QUEUE= "act_delay_queue";
    //延迟交换器
    public static final String ACT_DELAY_EXCHANGE = "act_delay_exchange";
    //延迟路由键
    public static final String ACT_DELAY_ROUTING_KEY = "act_delay_routing_key";

    /**
     * 延迟队列
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(ACT_DELAY_QUEUE);
    }

    /**
     *  声明延迟消费队列交换机-direct类型
     * 注：把消息投递到那些binding key与routing key完全匹配的队列中。
     * */
    @Bean
    public CustomExchange actDelayExchage(){
        // 一共有三种构造方法，可以只传exchange的名字，
        // 第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
        //第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(ACT_DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 把消费队列和立即消费交换机绑定, immediate_exchange, 路由键：immediate_routing_key
     *
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(delayQueue()).to(actDelayExchage()).with(ACT_DELAY_ROUTING_KEY).noargs();
    }


}
