package com.ygl.rabbitmq.springbootorderrabbitmqproducer1.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟用户下单  Fanout模式下
     */
    public void makeOrderFanout(String userId,String productId,int num){

        //1：根据商品id查询库存是否充足
        //2：保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功："+orderId);
        //3：通过mq来完成消息分发
        //参数1：交换机   参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);

    }

    /**
     * 模拟用户下单  Direct模式下
     */
    public void makeOrderDirect(String userId,String productId,int num){

        //1：根据商品id查询库存是否充足
        //2：保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功："+orderId);
        //3：通过mq来完成消息分发
        //参数1：交换机   参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "direct_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,"duanxin",orderId);
        rabbitTemplate.convertAndSend(exchangeName,"email",orderId);

    }
}
