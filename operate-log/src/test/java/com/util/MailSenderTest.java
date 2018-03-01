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
        email.sendEmail("1406721322@qq.com","Test","Test text!");

    }
}
