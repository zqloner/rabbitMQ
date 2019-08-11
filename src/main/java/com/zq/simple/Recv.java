package com.zq.simple;

import com.rabbitmq.client.*;
import com.zq.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private final static String QUEUE_NAME="simple_queue";
    public  static void  main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //获取到通道
        Channel channel = connection .createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body
                String msg = new String(body);
                System.out.println("[x]received===="+msg+"!");
                //手动进行ACK
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听队列,第二个参数:是否自动进行消息确认,一般设置为false关闭自动提交
        //channel.basicConsume(QUEUE_NAME,true,consumer);
        int i = 10/0;
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
