package com.cy.platform.model.mq.base;

import com.cy.platform.model.mq.TestInterface;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class DefaultProducerTest {

    @Test
    public void test_mq_producer() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultProducer producer = new DefaultProducer();
        producer.sendMessage();
    }

    @Test
    public void test_mq_consumer() throws MQClientException, InterruptedException {
        DefaultPushConsumer consumer = new DefaultPushConsumer();
        consumer.push("test1");
    }

    @Test
    public void test_mq_consumer2() throws MQClientException, InterruptedException {
        DefaultPushConsumer consumer = new DefaultPushConsumer();
        consumer.push("test2");
    }

    @Test
    public void test_mq_consumer3() throws MQClientException, InterruptedException {
        DefaultPushConsumer consumer = new DefaultPushConsumer();
        consumer.push("test2");
    }
}