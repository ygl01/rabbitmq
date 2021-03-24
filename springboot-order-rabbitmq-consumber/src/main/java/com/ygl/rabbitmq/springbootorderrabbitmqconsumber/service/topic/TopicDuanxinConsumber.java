package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

//类监听的队列
@Service
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "duanxin.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "#.duanxin.#"

))
public class TopicDuanxinConsumber {

    //消息的落脚点   将从生产者获取到的消息传递给方法的参数上
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("duanxin fanout----接收到了订单信息是：->"+message);
    }
}
