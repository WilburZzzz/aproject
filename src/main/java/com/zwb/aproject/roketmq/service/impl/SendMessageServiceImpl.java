package com.zwb.aproject.roketmq.service.impl;

import com.zwb.aproject.roketmq.service.SendMessageService;
import com.zwb.aproject.roketmq.vo.MQSendVo;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Value("${rocketmq.consumer.message.topic}")
    private String messageTopic;

    @Value("${rocketmq.consumer.file.topic}")
    private String fileTopic;

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @Override
    public void sendMessage(MQSendVo mqSendVo) throws UnsupportedEncodingException {
        //str和type拼装
        mqSendMessage(mqSendVo);

        //todo 查询mongodb 循环发送
        /*List<String> mgMsgList = new ArrayList<>();
        for(String mgMsg:mgMsgList){
            MqSendMessage(mgMsg,type);
        }*/

    }

    public void mqSendMessage(MQSendVo mqSendVo) throws UnsupportedEncodingException {
        rocketMQTemplate.setMessageQueueSelector(new SelectMessageQueueByHash());

        SendCallback callback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                //todo mongodb删除数据  str
                System.out.println("发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                //todo 保存到mongodb中 str
                System.out.println("发送异常");
            }
        };
        String topic = null;
        if(mqSendVo.getType().equals("file")){
            topic = fileTopic.concat(":").concat(mqSendVo.getType());
        } else {
            topic = messageTopic.concat(":").concat(mqSendVo.getType());
        }


        Message<?> message = MessageBuilder.withPayload(mqSendVo.getMessage())
                .setHeader("KEYS","keydata1").build();
        rocketMQTemplate.asyncSendOrderly(topic, message,mqSendVo.getType(), callback);
    }

}
