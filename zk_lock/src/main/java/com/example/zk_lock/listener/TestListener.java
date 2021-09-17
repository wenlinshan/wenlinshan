package com.example.zk_lock.listener;

import com.example.zk_lock.config.RabbitConfig;
import com.example.zk_lock.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener {

    @RabbitListener
    @RabbitListener(queues = RabbitConfig.TEST_QUEUE)
    public void receiveMessage(User user, Channel channel, Message message) {
        System.out.println("接收到的user: " + user);
        System.out.println("接收到的message：" + message);
    }
}
