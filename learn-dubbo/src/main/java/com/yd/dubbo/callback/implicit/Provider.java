package com.yd.dubbo.callback.implicit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"dubbo/dubbo-callback.implicit-provider.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}