package com.ygl.rabbitmq.all;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单模式
 *
 * @Author: 闫高岭同志
 * @Date: 2021/3/15  11:03
 * 生产者
 * @Version 1.0
 */
public class Producter {
    public static void main(String[] args) {
        //所有的中间件技术都是基于tcp/ip协议基础之上构建新型的协议规范，只不过rabbitmq遵循的是amqp协议
        //IP  port

        //1、创建链接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("49.235.125.139");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            //2、创建连接Connection rabbitmq为什么是基于channel去处理，而不是基于连接去处理的呢？？？ 长连接---信道channel
            connection = connectionFactory.newConnection("生产者");
            //3、创建连接获取通道Channel
            channel = connection.createChannel();
            //5、准备消息内容
            String message = "我是测试不存在的交换机course";
            //准备交换机
            String exchangeName = "direct_message_exchange";
            //定义路由key
            String routeKey = "";
            //指定交换机类型  direct/topic/fanout/headers
            String exchangeType = "direct";
            //声明交换机
            channel.exchangeDeclare(exchangeName,exchangeType,true);
            //声明队列
            channel.queueDeclare("queue5",true,false,false,null);
            channel.queueDeclare("queue6",true,false,false,null);
            channel.queueDeclare("queue7",true,false,false,null);
            //交换机和队列进行绑定
            channel.queueBind("queue5",exchangeName,"order");
            channel.queueBind("queue6",exchangeName,"order");
            channel.queueBind("queue7",exchangeName,"course");
            //6、发送消息给队列Queue
            //@param1：交换机  @param2：队列、路由key @param3：消息的状态控制 @param4：消息主题
            //面试题：可以存在没有交换机的队列吗？不可能，虽然没有指定交换机，但是一定会存在默认的交换机
                channel.basicPublish(exchangeName, "course", null, message.getBytes());
            System.out.println("消息已发送！");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //7、关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            //8、关闭连接
            if (connection!=null&&connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
