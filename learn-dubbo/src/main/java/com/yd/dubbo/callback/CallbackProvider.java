package com.yd.dubbo.callback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Yd on  2018-06-02
 * @description
 **/
public class CallbackProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"dubbo/dubbo-callback-provider.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}
