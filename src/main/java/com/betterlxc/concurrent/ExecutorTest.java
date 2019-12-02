package com.betterlxc.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class ExecutorTest {

    private static final ThreadFactory THREAD_FACTORY =
        new ThreadFactoryBuilder().setNameFormat("showcase1-thread-%s").build();

    private static final ThreadFactory THREAD_FACTORY2 =
        new ThreadFactoryBuilder().setNameFormat("showcase2-thread-%s").build();

    private static final BlockingQueue<Runnable> SHOWCASE_BLOCKING_QUEUE = new LinkedBlockingQueue<>(16);

    private static final BlockingQueue<Runnable> SHOWCASE_BLOCKING_QUEUE2 = new LinkedBlockingQueue<>(32);


    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(
        4, 8, 5, TimeUnit.SECONDS, SHOWCASE_BLOCKING_QUEUE,
        THREAD_FACTORY, new ThreadPoolExecutor.CallerRunsPolicy());


    private static final ThreadPoolExecutor POOL2 = new ThreadPoolExecutor(
        4, 8, 5, TimeUnit.SECONDS, SHOWCASE_BLOCKING_QUEUE2,
        THREAD_FACTORY2, new ThreadPoolExecutor.CallerRunsPolicy());

//    @Test
//    public void test01() throws InterruptedException {
//        for (int i = 0; i < 1005; i++) {
//            POOL.submit(() -> {
//
//
//            });
//        }
//        System.out.println(213123);
//
////    TimeUnit.SECONDS.sleep(100000000);
//    }


    private Void safeProcess(Integer showcase) {
        System.out.println(showcase);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test02() {

        for (int i = 0; i < 100; i++) {
            POOL.execute(() -> {
                while (true) {

                    POOL2.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(
                            "name: " + Thread.currentThread().getName()
                                + " - date: " + LocalDateTime.now()
                                + " - ActiveCount: " + POOL.getActiveCount()
                                + " - PoolSize: " + POOL.getPoolSize()
                                + " - Queue:" + POOL.getQueue().size()
                                + ""
                                + " - ActiveCount2: " + POOL2.getActiveCount()
                                + " - PoolSize2: " + POOL2.getPoolSize()
                                + " - Queue2:" + POOL2.getQueue().size());

                    });
                }
            });
        }


    }

    class Consumer implements Runnable {

        private Integer val;

        public Consumer(Integer val) {
            this.val = val;
        }

        @Override
        public void run() {
            System.out.println("消费者 资源 队列大小 " + val);
            take(val);
        }

        void take(Object obj) {
            try {
                System.out.println(obj);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ignored) {
            }
            System.out.println("消费对象 " + obj);
        }
    }
}
