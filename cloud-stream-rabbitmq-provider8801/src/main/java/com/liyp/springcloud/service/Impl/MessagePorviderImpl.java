package com.liyp.springcloud.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.liyp.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class) //定义消息的推送管道
public class MessagePorviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output; // 消息发送管道

    @Override
    public String sendMassger() {
        String serila = IdUtil.fastUUID();
        output.send(MessageBuilder.withPayload(serila).build());
        System.out.println("*********serila: " + serila);
        return null;
    }
}
