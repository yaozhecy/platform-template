package com.cy.platform.component.mq.spring;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author 56807
 */
@Component
public class RocketMqProducer {
    @Autowired
    private RocketMQTemplate template;

    /**
     * 发送普通消息
     */
    public void sendMsg(String msgBody) {
        for (int i = 0; i < 100; i++) {
            template.syncSend("queue_test_topic:tag", "Hello Mq" + i);
        }
    }
}
