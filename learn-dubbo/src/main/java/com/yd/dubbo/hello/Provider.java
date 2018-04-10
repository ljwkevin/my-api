package com.yd.dubbo.hello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"dubbo/dubbo-service-provider.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}