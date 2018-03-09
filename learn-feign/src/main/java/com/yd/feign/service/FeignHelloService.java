package com.yd.feign.service;

import com.yd.entity.User;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.MediaType;

/**
 * @author Yd on  2018-03-07
 * @description
 **/
public interface FeignHelloService {

    @RequestLine("GET /hello/sayHello?name={name}")
    String sayHello(@Param(value = "name") String name);

    @Headers({"Content-Type: " + MediaType.APPLICATION_JSON_VALUE, "Accept: " + MediaType.APPLICATION_JSON_VALUE})
    @RequestLine("POST /hello/getUser")
//    @Body("\"user.id\": \"{user.id}\", \"user.name\": \"{user.name}\"")
    User getUser(User user);
}
