package com.cy.platform.component.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mq消息发送者
 *
 * @author 56807
 */
@Component
public class MqProducer {
    /**
     * 消息发送
     *
     * @throws Exception 异常
     */
    public void sendMsg() throws Exception {
        //创建一个生产者，并设置生产者组名称
        DefaultMQProducer producer = new DefaultMQProducer("test1");
        producer.setInstanceName(UUID.randomUUID().toString());
        //指定Name Server 地址
        producer.setNamesrvAddr("10.1.1.16:9876");
        //启动实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            //创建一个消息示例，指定topic，tag和消息体
            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ " + i)
                    .getBytes(RemotingHelper.DEFAULT_CHARSET));
            //发送消息到brokers中
            SendResult sendResult = producer.send(msg);
        }
        //关闭生产者实例
        producer.shutdown();
    }

    @Bean
    public void achieveMsg() throws Exception {
        //创建消费着并指定消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test2");
        consumer.setInstanceName(UUID.randomUUID().toString());
        //指定Name Sever地址
        consumer.setNamesrvAddr("10.1.1.16:9876");
        //指定订阅Topic
        consumer.subscribe("TopicTest", "TagA");
        //定义回调方法接受brokers中数据
        consumer.setConsumeThreadMax(1);
        consumer.setConsumeThreadMin(1);
        consumer.registerMessageListener((MessageListenerConcurrently) (msg, context) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(System.currentTimeMillis() + " 接受消息:%s %s\r\n", Thread.currentThread().getName(), msg);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //启动示例
        consumer.start();
    }
}
