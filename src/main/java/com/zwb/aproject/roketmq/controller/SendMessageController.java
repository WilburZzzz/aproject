package com.zwb.aproject.roketmq.controller;

import com.zwb.aproject.roketmq.service.SendMessageService;
import com.zwb.aproject.roketmq.vo.MQSendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/mq")
public class SendMessageController {

    @Autowired
    private SendMessageService sendMessageService;

    @PostMapping("/sendMsg")
    public void sendMsg(@RequestBody MQSendVo mqSendVo) throws UnsupportedEncodingException {
        sendMessageService.sendMessage(mqSendVo);
    }

}
