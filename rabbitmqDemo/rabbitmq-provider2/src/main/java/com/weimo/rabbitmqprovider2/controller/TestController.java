package com.weimo.rabbitmqprovider2.controller;

import com.weimo.rabbitmqprovider2.config.DelayQueueConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/27
 * @description：
 */
@RestController
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public void send() {
        System.out.println("发送时间：" + new Date().toString());
        this.rabbitTemplate.convertAndSend(DelayQueueConfig.exchangeName, DelayQueueConfig.routingKey, "hello, delayMessage", message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration("10000");
            return message;
        });
    }

}
