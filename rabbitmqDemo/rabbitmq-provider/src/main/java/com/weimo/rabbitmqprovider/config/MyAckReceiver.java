package com.weimo.rabbitmqprovider.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/7/9
 * @description：手动确认消息监听类
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            // 可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            String[] msgArray = msg.split("'");
            System.out.println(msgArray[1].trim());
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim());
            String messageId = msgMap.get("messageId");
            String messageData = msgMap.get("messageData");
            String createTime = msgMap.get("createTime");

            if ("TestDirectQueue".equals(message.getMessageProperties().getConsumerQueue())){
                System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
                System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
                System.out.println("执行TestDirectQueue中的消息的业务处理流程......");
            }
            if ("fanout.A".equals(message.getMessageProperties().getConsumerQueue())){
                System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
                System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
                System.out.println("执行fanout.A中的消息的业务处理流程......");
            }
            System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliveryTag, true);
            // 为true会重新放回队列
//			channel.basicReject(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    // {key=value,key=value,key=value} 格式转换成map（bug：消息内容有“,”时会报下标越界）
    private Map<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            System.out.println("===" + string);
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }

    @Override
    public void onMessage(Message message) {

    }

    @Override
    public void containerAckMode(AcknowledgeMode mode) {

    }

    @Override
    public void onMessageBatch(List<Message> messages) {

    }

    @Override
    public void onMessageBatch(List<Message> messages, Channel channel) {

    }
}
