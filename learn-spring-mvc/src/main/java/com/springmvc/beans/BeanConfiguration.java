package com.springmvc.beans;

import com.yd.common.util.format.DictionaryFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Yd on  2018-03-01
 * @description 以注解的方式代替xml 配置Bean
 **/
@Configuration
public class BeanConfiguration {
    @Value("#{configProperties['163mail.host']}")
    private String HOST;
    @Value("#{configProperties['163mail.port']}")
    private Integer PORT;
    @Value("#{configProperties['163mail.username']}")
    private String USERNAME;
    @Value("#{configProperties['163mail.password']}")
    private String PASSWORD;

    @Bean(name = "java163MailSender")
    public JavaMailSender init163MailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;

    }

}
