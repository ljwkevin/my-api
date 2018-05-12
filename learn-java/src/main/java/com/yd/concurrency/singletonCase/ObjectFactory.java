package com.yd.concurrency.singletonCase;

/**
 * 惰性初始化holder 类
 * @author Yd on  2018-05-12
 * @description
 **/
public class ObjectFactory {

    private static class ObjectHolder{
        public static Object object = new Object();
    }

    public static Object getObject(){
        return ObjectHolder.object;
    }
}
