package com.zwb.aproject.roketmq.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;

@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.file.group}", topic = "${rocketmq.consumer.file.topic}" )
public class MQConsumerListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        //todo 存储记录到mysql中
        //todo 调用接口桥传输
        String str = null;
        try {
            str = new String(messageExt.getBody(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("获取文件列表: tag is :"+messageExt.getTags() +", message is " + str);
    }
}
