package com.ygl.rabbitmq.springbootorderrabbitmqproducer1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitMqConfiguration {
    //1：声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange",true,false);
    }
    //2：生命队列：sms.fanout.queue   email.fanout.queue   duanxin.fanout.queue
    @Bean
    public Queue smsFanoutQueue(){
        return new Queue("sms.fanout.queue");
    }
    @Bean
    public Queue emailFanoutQueue(){
        return new Queue("email.fanout.queue");
    }
    @Bean
    public Queue duanxinFanoutQueue(){
        return new Queue("duanxin.fanout.queue");
    }
    //3：完成绑定关系（队列和交换机完成绑定关系）
    @Bean
    public Binding smsFanoutBinding(){
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailFanoutBinding(){
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding duanxinFanoutBinding(){
        return BindingBuilder.bind(duanxinFanoutQueue()).to(fanoutExchange());
    }
}
