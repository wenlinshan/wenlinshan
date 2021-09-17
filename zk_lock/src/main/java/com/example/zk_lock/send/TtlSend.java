package com.example.zk_lock.send;

import com.example.zk_lock.config.MessageCallback;
import com.example.zk_lock.config.PluginConfig;
import com.example.zk_lock.config.RabbitConfig;
import com.example.zk_lock.config.TtlConfig;
import com.example.zk_lock.domain.User;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author wls
 */
@Service
public class TtlSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageCallback messageCallback;

    public  String sendTtlMessage(User user){
        rabbitTemplate.setConfirmCallback(messageCallback);
        rabbitTemplate.setReturnCallback(messageCallback);
        //消息id
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(TtlConfig.TTL_EXCHANGE, TtlConfig.TTL_KEY, user, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(user.getTtlTime().toString());
                return message;
            }
        },correlationData);
        return "发送延迟成功";
    }

    public String pluginMessage(User user){
        rabbitTemplate.setConfirmCallback(messageCallback);
        rabbitTemplate.setReturnCallback(messageCallback);
        //消息id
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(PluginConfig.ACT_DELAY_EXCHANGE, PluginConfig.ACT_DELAY_ROUTING_KEY, user, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setHeader("x-delay",user.getTtlTime());
                return message;
            }
        },correlationData);
        return "发送成功";
    }
}
