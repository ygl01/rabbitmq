package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//类监听的rabbitmq的队列
@Service
@RabbitListener(queues = {"sms.fanout.queue"})
public class FanoutSMSConsumber {

    //消息的落脚点
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms fanout-----开始接受的订单消息是："+message);
    }
}
