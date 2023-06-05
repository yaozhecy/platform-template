package com.cy.platform.model.mq.base;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

public class DefaultPushConsumer {

    public void push(String groupName) throws MQClientException, InterruptedException {
        // 创建消息消费者, 指定消费者所属的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        // 指定Nameserver地址
        consumer.setNamesrvAddr("10.10.1.66:9876");
        // 指定消费者订阅的主题和标签
        consumer.subscribe("myTopic", "*");
        // 设置回调函数，编写处理消息的方法
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println("Receive Messages: " + msgs);
            //返回消费状态
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 启动消息消费者
        consumer.start();
        System.out.println("Consumer Started.....");
        Thread.sleep(600000);
    }
}
