package com.wan.api.controller;

import com.wan.api.amqp.TaskMessage;
import com.wan.api.amqp.TaskProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send")
public class SendMessageController {

    @Autowired
    private TaskProducer taskProducer;
    @RequestMapping("/message")
    public void send() {
        TaskMessage task = new TaskMessage();
        task.setUrl("www.wan.com");
        taskProducer.sendNewTask(task);
    }
}
