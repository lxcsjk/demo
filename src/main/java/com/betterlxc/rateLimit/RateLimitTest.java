package com.betterlxc.rateLimit;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class RateLimitTest {

    private static final ThreadFactory THREAD_FACTORY =
        new ThreadFactoryBuilder().setNameFormat("rate-limit-thread-%s").build();

    private static final ExecutorService EXECUTOR_SERVICE =
        new ThreadPoolExecutor(10, 200, 0L, TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws Exception {
        testRateLimiter();
    }

    private static void testRateLimiter() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(EXECUTOR_SERVICE);

        RateLimiter limiter = RateLimiter.create(1000);

        IntStream.range(0, 20).parallel().forEach(i -> {
            System.out.println(limiter.acquire());
            System.out.println(limiter.tryAcquire());
            executorService.submit(new Task("is " + i + "--" + limiter.getRate()));
        });
        executorService.shutdown();
    }
}

class Task implements Callable<Integer> {
    private String str;

    Task(String str) {
        this.str = str;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(System.currentTimeMillis() / 1000 + "---" + Thread.currentThread().getName() + "-call execute.." + str);
        TimeUnit.MILLISECONDS.sleep(500);
        return 7;
    }
}
