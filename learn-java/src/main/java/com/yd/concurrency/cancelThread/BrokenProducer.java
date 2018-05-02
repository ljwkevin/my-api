package com.yd.concurrency.cancelThread;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 把不可靠的取消把生产者置于阻塞的操作中
 */
public class BrokenProducer extends Thread {
    private volatile boolean cancelled = false;
    private final BlockingQueue<BigInteger> queue;

    BrokenProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void cancel() {
        cancelled = true;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {

        }
    }

    public static void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(8);
        BrokenProducer producer = new BrokenProducer(primes);
        producer.start();

        try{
            while (!primes.isEmpty()){
               BigInteger p = primes.take();
               System.out.println(p);
            }
        }finally {
            producer.cancel();
        }

    }

}
