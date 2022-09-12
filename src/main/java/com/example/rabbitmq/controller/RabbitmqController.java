package com.example.rabbitmq.controller;

import com.example.rabbitmq.config.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mq")
public class RabbitmqController {

    @Autowired
    MQSender sender;

    @RequestMapping("/direct")
    @ResponseBody
    public void direct() {
        sender.sendDirect();
    }

    @RequestMapping("/topic")
    @ResponseBody
    public void topic() {
        sender.sendTopic();
    }

    @RequestMapping("fanout")
    @ResponseBody
    public void fanout() {
        sender.sendFanout();
    }

    @RequestMapping("/header")
    @ResponseBody
    public void header() {
        sender.sendHeader();
    }

}
