package com.yd.feign.service;

import com.yd.entity.User;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * @author Yd on  2018-03-07
 * @description
 **/
public class FeignHelloServiceTest {
    public static void main(String[] args) {
        String context_path = "http://localhost:8080/activiti";
        FeignHelloService helloService = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignHelloService.class, context_path);

        String r1 = helloService.sayHello("Yd");
        System.out.println(r1);
        User user = new User() {{
            this.setId(123);
        }};
        user = helloService.getUser(user);
        System.out.println(user);
    }
}
