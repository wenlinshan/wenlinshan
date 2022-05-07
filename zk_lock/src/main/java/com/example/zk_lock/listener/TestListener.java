package com.example.zk_lock.listener;

import com.example.zk_lock.config.RabbitConfig;
import com.example.zk_lock.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestListener {

    @RabbitListener(queues = RabbitConfig.TEST_QUEUE)
    public void receiveMessage(User user, Channel channel, Message message) throws IOException {
        try {
            System.out.println("接收到的user: " + user);
            System.out.println("接收到的message：" + message);
            //消息确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //消息回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }

    }
}
