package com.ygl.rabbitmq.springbootorderrabbitmqproducer1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 死信队列
 */
@Configuration
public class DeadRabbitMqConfiguration {
    //1：声明注册fanout模式的交换机
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange("dead_order_exchange",true,false);
    }
    //2：声明队列：sms.fanout.queue   email.fanout.queue   duanxin.fanout.queue
    //队列过期时间
    @Bean
    public Queue deadDirectQueue(){
        return new Queue("dead.queue",true);
    }
    //3：完成绑定关系（队列和交换机完成绑定关系）
    @Bean
    public Binding deadMessageDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(deadExchange()).with("dead");
    }
}
