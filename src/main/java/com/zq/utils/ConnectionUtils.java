package com.zq.utils;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("192.168.138.100");
        //设置端口
        factory.setPort(5672);
        //设置账号信息,用户名，密码，vhost
        factory.setVirtualHost("/zqloner");
        factory.setUsername("/zqloner");
        factory.setPassword("zqloner");
        //通过工程获取连接
        Connection connection = factory.newConnection();
        return  connection;
    }
}
