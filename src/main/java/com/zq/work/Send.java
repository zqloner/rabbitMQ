package com.zq.work;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zq.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME = "test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接以及通道
        Connection connection = ConnectionUtils.getConnection();
        //从连接中创建通道,这是完成大部分API的地方
        Channel channel = connection.createChannel();
        //声明（创建）队列,必须声明队列才能够发送消息，我们可以吧消息发送到队列中
        //声明一个队列是幂等的 -只有当他不存在是才会被创建
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        //消息内容
        for (int i = 0; i < 50; i++) {
            //消息内容
            String message = "task...."+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("[x] Send=====>'"+message+"'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
