package com.ygl.rabbitmq.springbootorderrabbitmqconsumber.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//类监听的队列
@Service
@RabbitListener(queues = {"duanxin.fanout.queue"})
public class FanoutDuanxinConsumber {

    //消息的落脚点   将从生产者获取到的消息传递给方法的参数上
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("duanxin fanout----接收到了订单信息是：->"+message);
    }
}
