package com.betterlxc.concurrent;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by LXC on 2017/5/10.
 * https://juejin.im/post/5c89b9515188257e5b2befdd?utm_source=gold_browser_extension#heading-1
 */
@Slf4j
public class SemaphoreTest {

    @Test
    @SneakyThrows
    public void semaphoreTest() {
        final Semaphore semaphore = new Semaphore(2);

        IntStream.range(0, 5).forEach(i -> {
            final int j = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    System.out.println("student " + j + " read book" + " permits: " + semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("student " + j + " finish read book");
                semaphore.release();
            }).start();
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void test() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        Task task = new Task();
        forkJoinPool.invoke(task);
    }

    class Task extends RecursiveAction {

        @Override
        protected void compute() {
            System.out.println("Inside Compute method");
        }
    }

    @Test
    public void test1() throws InterruptedException {
        final int[] result = {0};
        int N = 3;
        Thread[] threads = new Thread[N];
        final Semaphore[] syncObjects = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != N - 1) {
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < N; i++) {
            final Semaphore lastSemaphore = i == 0 ? syncObjects[N - 1] : syncObjects[i - 1];
            final Semaphore curSemaphore = syncObjects[i];
            final int index = i;
            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        lastSemaphore.acquire();
                        System.out.println("thread" + index + ": " + result[0]++);
                        if (result[0] > 100) {
                            System.exit(0);
                        }
                        curSemaphore.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            threads[i].start();
        }
    }

}
