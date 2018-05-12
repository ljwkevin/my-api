package com.yd.concurrency.singletonCase;

/**
 * 线程安全的惰性初始化
 * @author Yd on  2018-05-12
 * @description
 **/
public class SafeLazyInit {
    private static Object object;

    public synchronized static Object getObject(){
        if (object ==null)
            object = new Object();
        return object;
    }
}
