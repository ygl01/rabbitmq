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
        //注意：队列创建后，又重新进行更改队列里的配置，那么你需要重新删除队列重新创建，因为队列一旦创建则无法进行更新修改
        hashMap.put("x-message-ttl",5000);//后面的5000是过期时间，单位是ms，
//        hashMap.put("x-dead-letter-exchange","dead_order_exchange");//设置死信队列名称
//        hashMap.put("x-dead-letter-routing-key","dead");//设置死信队列key   fanout模式不需要进行配置
        return new Queue("ttl.queue",true,false,false,hashMap);
//        return new Queue("ttl.queue",true,false,false);
    }
    @Bean
    public Queue ttlDirectQueue1(){
        HashMap<String, Object> hashMap = new HashMap<>();
        //注意：队列创建后，又重新进行更改队列里的配置，那么你需要重新删除队列重新创建，因为队列一旦创建则无法进行更新修改
        hashMap.put("x-message-ttl",5000);//后面的5000是过期时间，单位是ms，
        hashMap.put("x-dead-letter-exchange","dead_order_exchange");//设置死信队列名称
        hashMap.put("x-dead-letter-routing-key","dead");//设置死信队列key   fanout模式不需要进行配置
//        return new Queue("ttl.queue",true,false,false,hashMap);
        return new Queue("ttl.queue1",true,false,false,hashMap);
    }
    //队列内消息过期时间
    @Bean
    public Queue ttlMessageDirectQueue(){
        return new Queue("ttl.message.queue",true,false,false);
    }
    //3：完成绑定关系（队列和交换机完成绑定关系）
    @Bean
    public Binding ttlDirectBinding1(){
        return BindingBuilder.bind(ttlDirectQueue1()).to(ttlExchange()).with("ttl1");
    }
    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue1()).to(ttlExchange()).with("ttl1");
    }
    @Bean
    public Binding ttlMessageDirectBinding(){
        return BindingBuilder.bind(ttlMessageDirectQueue()).to(ttlExchange()).with("ttlmessage");
    }
}
