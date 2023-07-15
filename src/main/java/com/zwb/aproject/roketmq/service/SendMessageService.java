package com.zwb.aproject.roketmq.service;

import com.zwb.aproject.roketmq.vo.MQSendVo;

import java.io.UnsupportedEncodingException;

public interface SendMessageService {

    void sendMessage(MQSendVo mqSendVo) throws UnsupportedEncodingException;

}
