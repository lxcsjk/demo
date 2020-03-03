package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuxincheng
 * @date 2019-06-20
 */
public class ReentrantLockTest {


    @Test
    public void test() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            reentrantLock.lock();

        } finally {
            reentrantLock.unlock();
        }

        int holdCount = reentrantLock.getHoldCount();
    }

}
