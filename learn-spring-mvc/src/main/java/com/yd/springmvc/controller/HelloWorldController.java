package com.yd.springmvc.controller;

import com.yd.entity.User;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @Author: zhengcanbiao
 * @Description:
 * @Date: Created by zhengcanbiao on 2017/11/8.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("#{application['export2Excel.fileName']}")
    private String fileName;

    @RequestMapping(value = "/sayHello", method = {RequestMethod.GET})
    @ResponseBody
    public String sayHello(@RequestParam("name") String name) {
        return "sayHello " + name + " at" + fileName;
    }

    @RequestMapping(value = "/getUser", method = {RequestMethod.POST})
    @ResponseBody
    public User getUser(@RequestBody User user) {
        user.setId(RandomUtils.nextInt(1,100));
        return user;
    }

}
