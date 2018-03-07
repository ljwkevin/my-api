package com.yd.feign.service;

import com.yd.entity.User;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author Yd on  2018-03-07
 * @description
 **/
public interface FeignHelloService {

    @RequestLine("GET /hello/sayHello?name={name}")
    String sayHello(@Param(value="name")String name);

    @Headers({"Content-Type:application/json","Accept:application/json"})
    @RequestLine("Post /users/getUser")
    User getUser(User user);
}
