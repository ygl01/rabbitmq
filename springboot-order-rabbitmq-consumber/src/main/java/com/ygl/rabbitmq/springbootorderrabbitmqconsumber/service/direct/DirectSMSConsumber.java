package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//类监听的rabbitmq的队列
@Service
@RabbitListener(queues = {"sms.direct.queue"})
public class DirectSMSConsumber {

    //消息的落脚点
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms direct-----开始接受的订单消息是："+message);
    }
}
