package com.yd.dubbo.callback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yd on  2018-06-02
 * @description
 **/
public class CallbackConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/dubbo-callback-consumer.xml");
        context.start();

        CallbackService callbackService = (CallbackService) context.getBean("callbackService");

        callbackService.addListener("http://10.20.160.198/wiki/display/dubbo/foo.bar", new CallbackListener(){
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });
    }
}
