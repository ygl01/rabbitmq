package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//类监听的rabbitmq的队列名称
@Service
@RabbitListener(queues = {"email.fanout.queue"})
public class FanoutEmailConsumber {
    //消息的落脚点
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("Email fanout------接受到的订单消息是："+message);
    }
}
