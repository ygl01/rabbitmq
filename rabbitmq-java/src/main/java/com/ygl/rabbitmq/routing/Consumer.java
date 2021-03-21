package com.ygl.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 闫高岭同志
 * @Date: 2021/3/15  11:04
 * 消费者
 * @Version 1.0
 */
public class Consumer {

    private static Runnable runnable = new Runnable(){

        public void run() {
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
                //2、创建连接Connection
                connection = connectionFactory.newConnection("消费者");
                //3、创建连接获取通道Channel
                channel = connection.createChannel();
                //4、通过通道创建交换机，声明队列，绑定关系，路由key，发送消息，和接收消息
                //获取队列名称
                final String queueName = Thread.currentThread().getName();
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                        System.out.println("队列名称："+queueName+"；收到消息是：" + new String(delivery.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    public void handle(String s) throws IOException {
                        System.out.println("接受消息失败、、、");
                    }
                });
                System.out.println("开始接受消息！");
                System.in.read();
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
    };

    public static void main(String[] args) {
        //启动三个线程去执行
        new Thread(runnable,"queue1").start();
        new Thread(runnable,"queue2").start();
        new Thread(runnable,"queue3").start();
        new Thread(runnable,"queue4").start();
    }
}
