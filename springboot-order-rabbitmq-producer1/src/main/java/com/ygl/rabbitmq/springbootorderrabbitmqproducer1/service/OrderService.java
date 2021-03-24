package com.ygl.rabbitmq.springbootorderrabbitmqproducer1.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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
    /**
     * 模拟用户下单  Topic模式下
     */
    public void makeOrderTopic(String userId,String productId,int num){

        //1：根据商品id查询库存是否充足
        //2：保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功："+orderId);
        //3：通过mq来完成消息分发
        //参数1：交换机   参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "topic_order_exchange";
        String routingKey = "sms.email";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
    /**
     * 模拟用户下单  Direct模式下 增加队列过期时间TTL
     */
    public void makeOrderDirectTtl(String userId,String productId,int num){

        //1：根据商品id查询库存是否充足
        //2：保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功："+orderId);
        //3：通过mq来完成消息分发
        //参数1：交换机   参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "ttl_order_exchange";
        String routingKey = "ttl";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
    /**
     * 模拟用户下单  Direct模式下 增加队列内消息过期时间TTL
     */
    public void makeOrderDirectTtlMessage(String userId,String productId,int num){

        //1：根据商品id查询库存是否充足
        //2：保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功："+orderId);
        //3：通过mq来完成消息分发
        //参数1：交换机   参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "ttl_order_exchange";
        String routingKey = "ttlmessage";
        //给消息设置过期时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置消息的过期时间，这里是字符串
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };

        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId,messagePostProcessor);
    }
    /**
     * 模拟用户下单  Direct模式下 增加队列内消息过期时间TTL
     */
    public void makeOrderDirectTtlMessage1(String userId,String productId,int num){

        //1：根据商品id查询库存是否充足
        //2：保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功："+orderId);
        //3：通过mq来完成消息分发
        //参数1：交换机   参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "ttl_order_exchange";
        String routingKey = "ttlmessage";

        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
}
