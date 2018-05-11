package com.yd.concurrency.cache;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有限缓存使用显式的条件变量
 *
 * @author Yd on  2018-05-11
 * @description
 **/
public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final T[] items;
    private int tail, head, count;

    public ConditionBoundedBuffer(int capacity){
        items = (T[]) new Object[capacity];
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try{
            while (count == items.length)
                notFull.await();
            items[tail] = t;
            if (++tail ==items.length)
                tail=0;
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }

    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count==0)
                notEmpty.await();
            T t = items[head];
            items[head]=null;
            if (++head ==items.length)
                head = 0;
            --count;
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

}
