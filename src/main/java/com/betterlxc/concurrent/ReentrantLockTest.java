package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuxincheng
 * @date 2019-06-20
 */
public class ReentrantLockTest {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void test() {

        new Thread(()->{
            int i = atomicInteger.incrementAndGet();
            System.out.println(i);
        });

        new Thread(()->{
            int i = atomicInteger.incrementAndGet();
            System.out.println(i);
        });


//        ReentrantLock reentrantLock = new ReentrantLock();
//        try {
//            reentrantLock.lock();
//            reentrantLock.lock();
//
//        } finally {
//            reentrantLock.unlock();
//            reentrantLock.unlock();
//            reentrantLock.unlock();
//        }
//
//        int holdCount = reentrantLock.getHoldCount();
//        System.out.println(holdCount);
    }


}
