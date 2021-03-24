package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//类监听的rabbitmq的队列名称
@Service
@RabbitListener(queues = {"email.direct.queue"})
public class DirectEmailConsumber {
    //消息的落脚点
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("Email direct------接受到的订单消息是："+message);
    }
}
