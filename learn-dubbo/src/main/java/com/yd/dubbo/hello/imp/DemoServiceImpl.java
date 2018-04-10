package com.yd.dubbo.hello.imp;

import com.yd.dubbo.hello.service.DemoService;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}