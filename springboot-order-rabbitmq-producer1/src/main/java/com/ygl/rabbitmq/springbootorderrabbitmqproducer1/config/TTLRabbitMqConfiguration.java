package com.ygl.rabbitmq.springbootorderrabbitmqproducer1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;

/**
 * TTL是设置队列内消息过期时间
 */
@Configuration
public class TTLRabbitMqConfiguration {
    //1：声明注册fanout模式的交换机
    @Bean
    public DirectExchange ttlExchange(){
        return new DirectExchange("ttl_order_exchange",true,false);
    }
    //2：声明队列：sms.fanout.queue   email.fanout.queue   duanxin.fanout.queue
    //队列过期时间
    @Bean
    public Queue ttlDirectQueue(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("x-message-ttl",5000);//后面的5000是过期时间，单位是ms，
        return new Queue("ttl.queue",true,false,false,hashMap);
    }
    //3：完成绑定关系（队列和交换机完成绑定关系）
    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue()).to(ttlExchange()).with("ttl");
    }
}
