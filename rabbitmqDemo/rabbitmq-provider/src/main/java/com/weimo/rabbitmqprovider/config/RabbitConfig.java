package com.weimo.rabbitmqprovider.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/8
 * @description：RabbitMQConfig配置类
 */
@Configuration
public class RabbitConfig {

    @Autowired
    RabbitAdmin rabbitAdmin;

    // 绑定键
    private final static String man = "topic.man";
    private final static String woman = "topic.woman";

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
//        rabbitTemplate.setMandatory(true);
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
    }

    /**
     * 队列
     *
     * @return
     */
    @Bean
    public Queue testDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("TestDirectQueue", true);
    }

    /**
     * Direct交换机 name：TestDirectExchange
     */
    @Bean
    DirectExchange testDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchange", true, false);
    }

    /**
     * 将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    public Queue firstQueue() {
        return new Queue(RabbitConfig.man);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(RabbitConfig.woman);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    // 将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    // 只有消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage1() {
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(man);
    }

    // 将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }


    /**
     * 创建三个队列 ：fanout.A   fanout.B  fanout.C
     * 将三个队列都绑定在交换机 fanoutExchange 上
     * 因为是扇型交换机, 路由键无需配置,配置也不起作用
     */
    @Bean
    public Queue queueA() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue queueB() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue queueC() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }


    // 创建初始化RabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }


    // 配置启动项目则创建交换机和对列
    @Bean
    public void createExchangeQueue() {
        rabbitAdmin.declareExchange(testDirectExchange());
        rabbitAdmin.declareQueue(testDirectQueue());

        rabbitAdmin.declareExchange(topicExchange());
        rabbitAdmin.declareQueue(firstQueue());
        rabbitAdmin.declareQueue(secondQueue());

        rabbitAdmin.declareExchange(fanoutExchange());
        rabbitAdmin.declareQueue(queueA());
        rabbitAdmin.declareQueue(queueB());
        rabbitAdmin.declareQueue(queueC());

        rabbitAdmin.declareExchange(lonelyDirectExchange());

    }

}
