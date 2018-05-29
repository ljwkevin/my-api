package com.yd.jdk.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CustomizeHandle implements InvocationHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomizeHandle.class);

    private Object target;

    public CustomizeHandle(Class clazz) {
        try {
            this.target = clazz.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("InstantiationException", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException",e);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(target, args);
        after();

        LOGGER.info("proxy class={}", proxy.getClass());
        return result;
    }


    private void before() {
        LOGGER.info("handle before");
    }

    private void after() {
        LOGGER.info("handle after");
    }

    public static void main(String[] args) {
        CustomizeHandle handle = new CustomizeHandle(HashMap.class) ;
        Map map = (Map) Proxy.newProxyInstance(CustomizeHandle.class.getClassLoader(), new Class[]{Map.class}, handle);
        map.put("a","a");
        System.out.println(map.get("a"));
    }
}