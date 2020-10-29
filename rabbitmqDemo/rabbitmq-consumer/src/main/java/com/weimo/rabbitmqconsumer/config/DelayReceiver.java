package com.weimo.rabbitmqconsumer.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/8
 * @description：消息监听类
 */
@Component
public class DelayReceiver {

    @RabbitListener(queues = "demo_delay_queue")
    public void process(String msg, Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("监听到延时队列消息: 消费时间: " + new Date().toString() + ", 消息内容: " + msg);
        /*// 手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手动签收
        channel.basicAck(deliveryTag, false);
        System.out.println(deliveryTag + "执行结束....");*/
    }

}
