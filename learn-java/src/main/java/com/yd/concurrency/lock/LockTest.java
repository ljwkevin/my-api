package com.yd.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Yd on  2018-05-10
 * @description
 **/
public class LockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try{
            lock.lock();
        }finally {
            lock.unlock();
        }

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    }

}
