package com.zq.test;

import com.zq.MySpringApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringApplication.class)//这里的Application是springboot的启动类名
public class Mytest {
    @Resource
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello,SpringBoot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b",msg);
        //等待两秒钟
        Thread.sleep(10000);
    }
}
