package com.cy.platform.component.mq.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Msg消息监听
 *
 * @author 56807
 */
@Slf4j
@Component
@RocketMQMessageListener(
    topic = "queue_test_topic",
    consumerGroup = "queue_group_test",
    selectorExpression = "*",
    consumeThreadMax = 2
)
public class RocketMqMsgListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("spring rocketMq:" + s);
    }
}
