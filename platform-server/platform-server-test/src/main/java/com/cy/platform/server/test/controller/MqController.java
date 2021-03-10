package com.cy.platform.server.test.controller;

import com.cy.platform.component.mq.MqProducer;
import com.cy.platform.component.mq.spring.RocketMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息 Controller
 *
 * @author 56807
 */
@RestController
@RequestMapping("/mq")
public class MqController {
    @Autowired
    private MqProducer mqProducer;
    @Autowired
    private RocketMqProducer producer;

    @GetMapping("/send")
    public void sendMsg() throws Exception {
        producer.sendMsg("haha");
    }

    @GetMapping("/achieve")
    public void achieveMsg() throws Exception {
        mqProducer.achieveMsg();
    }
}
