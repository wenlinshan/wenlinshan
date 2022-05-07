package com.example.zk_lock.send;

import cn.hutool.core.bean.BeanUtil;
import com.example.zk_lock.config.MessageCallback;
import com.example.zk_lock.config.RabbitConfig;
import com.example.zk_lock.domain.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageCallback messageCallback;

    /**
     * 初始消息
     */
    public String sendMessage(User user){
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_EXCHANGE,RabbitConfig.TEST_KEY, BeanUtil.beanToMap(user,false,true));
        return "发送成功";
    }

    /**
     * 发送确认消息

     */
    public String sendConfirmMessage(User user){
        rabbitTemplate.setConfirmCallback(messageCallback);
        rabbitTemplate.setReturnCallback(messageCallback);
        //消息id
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_EXCHANGE,RabbitConfig.TEST_KEY,user,correlationData);
        return "发送成功";
    }


}
