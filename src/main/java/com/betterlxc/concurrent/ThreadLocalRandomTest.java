package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liuxincheng
 * @date 2020/3/5
 */
public class ThreadLocalRandomTest {

    private static final ThreadLocalRandom THREAD_LOCAL_RANDOM = ThreadLocalRandom.current();

    @Test
    public void test() {
        for (int i = 0; i < 120; i++) {
            System.out.println(THREAD_LOCAL_RANDOM.nextInt(0, 100));
        }
    }

    @Test
    public void test1() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            callables.add(() -> {
                int i1 = THREAD_LOCAL_RANDOM.nextInt(0,10);
                System.out.println("i:"+ i1+",   "+Thread.currentThread().getName());
                return i1;
            });
        }
        executor.invokeAll(callables);
    }
}
