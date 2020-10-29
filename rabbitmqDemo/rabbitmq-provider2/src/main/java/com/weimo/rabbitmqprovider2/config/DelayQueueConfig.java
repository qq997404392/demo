package com.weimo.rabbitmqprovider2.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/8
 * @description：RabbitMQConfig配置类
 */
@Configuration
public class DelayQueueConfig {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    // 队列名称
    public final static String queue = "demo_queue";

    // 交换机名称
    public final static String exchangeName = "demo_exchange";

    // routingKey
    public final static String routingKey = "demo_routing_key";

    // 死信消息队列名称
    public final static String delay_queue = "demo_delay_queue";

    // 死信交换机名称
    public final static String delay_exchangeName = "demo_delay_exchange";

    // 死信routingKey
    public final static String dead_RoutingKey = "dead_routing_key";

    //死信队列 交换机标识符
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";

    //死信队列交换机绑定键标识符
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue queueDemo() {
        // 将普通队列绑定到死信队列交换机上
        Map<String, Object> args = new HashMap<>(2);
        // 直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活
        // args.put("x-message-ttl", 5 * 1000);
        // 这里采用发送消息动态设置延迟时间
        args.put(DEAD_LETTER_QUEUE_KEY, delay_exchangeName);
        args.put(DEAD_LETTER_ROUTING_KEY, dead_RoutingKey);
        return new Queue(DelayQueueConfig.queue, true, false, false, args);
    }

    @Bean
    DirectExchange exchangeDemo() {
        return new DirectExchange(DelayQueueConfig.exchangeName);
    }

    @Bean
    Binding bindingDirectExchangeDemo() {
        return BindingBuilder.bind(queueDemo()).to(exchangeDemo()).with(routingKey);
    }

    // 创建配置死信队列
    @Bean
    public Queue deadQueue() {
        return new Queue(delay_queue, true);
    }

    // 创建死信交换机
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(delay_exchangeName);
    }

    // 死信队列与死信交换机绑定
    @Bean
    public Binding bindingDeadExchange() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(dead_RoutingKey);
    }

/*    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback: " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback: " + "确认情况：" + ack);
                System.out.println("ConfirmCallback: " + "原因：" + cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback: " + "消息：" + message);
                System.out.println("ReturnCallback: " + "回应码：" + replyCode);
                System.out.println("ReturnCallback: " + "回应信息：" + replyText);
                System.out.println("ReturnCallback: " + "交换机：" + exchange);
                System.out.println("ReturnCallback: " + "路由键：" + routingKey);
            }
        });

        return rabbitTemplate;
    }*/

}
