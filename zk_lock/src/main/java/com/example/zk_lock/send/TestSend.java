package com.example.zk_lock.send;

import com.example.zk_lock.config.RabbitConfig;
import com.example.zk_lock.domain.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String sendMessage(User user){
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_EXCHANGE,RabbitConfig.TEST_KEY,user);
        return "发送成功";
    }
}
