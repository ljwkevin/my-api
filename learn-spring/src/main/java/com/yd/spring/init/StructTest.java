package com.yd.spring.init;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.Resource;

/**
 * @author Yd on  2018-05-31
 * @description Spring ioc 容器系列
 **/
public class StructTest {
    Resource resource = null;

    AbstractResource abstractResource = null;
//    BeanDefinitionResource


    ContextResource contextResource = null;

    BeanFactory beanFactory = new ClassPathXmlApplicationContext();

    ApplicationContext applicationContext = null;



    DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

    XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);

    FileSystemXmlApplicationContext applicationContext1 =  new FileSystemXmlApplicationContext("");

}
