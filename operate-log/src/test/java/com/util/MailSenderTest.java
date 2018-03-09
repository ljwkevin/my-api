package com.util;

import com.BaseTest;
import com.yd.mail.EmailService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Yd on  2018-03-01
 * @description
 **/
public class MailSenderTest extends BaseTest{
    @Resource(name = "service.emailService")
    private EmailService email;

    @Test
    public void sendMail(){
        //发送邮件时，部分邮件服务器 会校验一些非法邮件，比如test、helloworld
        email.sendEmail("2629233600@qq.com","面试通知","你好，恭喜你通过了昨天的面试，请在明天来我司进行第二轮面试！");

    }
}
