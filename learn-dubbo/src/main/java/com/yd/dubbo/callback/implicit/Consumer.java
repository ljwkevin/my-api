package com.yd.dubbo.callback.implicit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo/dubbo-callback.implicit-consumer.xml"});
        context.start();
        IDemoService demoService = (IDemoService) context.getBean("demoService");
        NotifyImpl notify = (NotifyImpl) context.getBean("demoCallback");
        int requestId = 2;
        Person ret = demoService.get(requestId);
        System.out.println("-->" + (null == ret));
        //for Test：只是用来说明callback正常被调用，业务具体实现自行决定.
        for (int i = 0; i < 10; i++) {
            if (!notify.ret.containsKey(requestId)) {
                Thread.sleep(200);
            } else {
                break;
            }
        }
    }
}