package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

//类监听的rabbitmq的队列名称
@Service
//注解配置绑定
@RabbitListener(bindings = @QueueBinding(
        //注解配置对列
        value = @Queue(value = "email.topic.queue",durable = "true",autoDelete = "false"),
        //注解配置交换机，且指明类型
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        //注解配置路由key
        key = "*.email.#"
))
public class TopicEmailConsumber {
    //消息的落脚点
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("Email Topic------接受到的订单消息是："+message);
    }
}
