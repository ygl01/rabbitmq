package com.ygl.rabbitmq.work.fair;

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
            //4、通过通道创建交换机，声明队列，绑定关系，路由key，发送消息，和接收消息
            String queueName = "queue1";
            /**
             * @params1 队列名称
             * @params2 是否要持久化 所谓持久化就是是否要进行存盘，如果false则不持久化  为true是持久化 非持久化会存盘吗？会存盘，但是会随着服务器重启而丢失数据
             * @params3 排他性  是否是独占独立
             * @params4 是否自动删除，随着最后一个消费者消息完毕以后，是否把队列自动删除
             * @params5 携带附属参数
             */
            channel.queueDeclare(queueName, true, false, false, null);
            for (int i = 0; i < 20; i++) {
                //5、准备消息内容
                String message = "闫高岭"+i;
                //6、发送消息给队列Queue
                //@param1：交换机  @param2：队列、路由key @param3：消息的状态控制 @param4：消息主题
                //面试题：可以存在没有交换机的队列吗？不可能，虽然没有指定交换机，但是一定会存在默认的交换机
                channel.basicPublish("", queueName, null, message.getBytes());
                System.out.println("消息已发送！");
            }

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
