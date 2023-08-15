package com.cy.platform.model.mq.base;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class DefaultProducer {

    public void sendMessage() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建消息生产者, 指定生产者所属的组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        // 指定Nameserver地址
        producer.setNamesrvAddr("10.10.1.66:9876");
        // 启动生产者
        producer.start();

        // 创建消息对象，指定主题、标签和消息体
        Message msg = new Message("myTopic", "myTag", ("RocketMQ Message").getBytes());
        // 发送消息
        SendResult sendResult = producer.send(msg, 10000);
        System.out.println(sendResult);

        // 关闭生产者
        producer.shutdown();
    }
}
