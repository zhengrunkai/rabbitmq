package com.example.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

    private static final Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendDirect() {
        String msg = "123abc";
        log.info("send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.DIRECT_QUEUE, msg);
    }

    public void sendTopic() {
        String msg = "123abc";
        log.info("send topic message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg + "1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg + "2");
    }

    public void sendFanout() {
        String msg = "123abc";
        log.info("send fanout message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
    }

    public void sendHeader() {
        String msg = "123abc";
        log.info("send head message:" + msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1", "value1");
        properties.setHeader("header2", "value2");
        Message obj = new Message(msg.getBytes(), properties);
        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
    }

}
