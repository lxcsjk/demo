package com.betterlxc.concurrent;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxincheng
 * @date 2020/5/25
 */
public class ThreadLocalTest {

    public static final InheritableThreadLocal<String> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static final TransmittableThreadLocal<String> TRANSMITTABLE_THREAD_LOCAL = new TransmittableThreadLocal<>();

    private static final ThreadFactory THREAD_FACTORY =
        new ThreadFactoryBuilder().setNameFormat("threadlocal-%s").build();

    private static final ExecutorService EXECUTOR_SERVICE =
        new ThreadPoolExecutor(2, 200, 0L, TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws InterruptedException {
        INHERITABLE_THREAD_LOCAL.set("Inheritable hello");
        THREAD_LOCAL.set("hello");
        TRANSMITTABLE_THREAD_LOCAL.set("transmittableThreadLocal hello");
        new Thread(() -> {
            System.out.println(String.format("子线程可继承值：%s", INHERITABLE_THREAD_LOCAL.get()));
            System.out.println(String.format("子线程值：%s", THREAD_LOCAL.get()));
            System.out.println(String.format("子线程值1：%s", TRANSMITTABLE_THREAD_LOCAL.get()));

            new Thread(() -> {
                System.out.println(String.format("孙子线程可继承值：%s", INHERITABLE_THREAD_LOCAL.get()));
                System.out.println(String.format("孙子线程值：%s", THREAD_LOCAL.get()));
                System.out.println(String.format("孙子线程值1：%s", TRANSMITTABLE_THREAD_LOCAL.get()));
            }).start();

        }).start();

        THREAD_LOCAL.remove();

        EXECUTOR_SERVICE.submit(() -> {
            System.out.println("=======");
            System.out.println(String.format(Thread.currentThread().getName() + " 值：%s", INHERITABLE_THREAD_LOCAL.get()));
            System.out.println(String.format(Thread.currentThread().getName() + " 值1：%s", TRANSMITTABLE_THREAD_LOCAL.get()));
            System.out.println("=======");
            INHERITABLE_THREAD_LOCAL.set("hi");
        });
        TimeUnit.SECONDS.sleep(1);

        EXECUTOR_SERVICE.submit(() -> {
            System.out.println("=======");
            System.out.println(String.format(Thread.currentThread().getName() + " 值：%s", INHERITABLE_THREAD_LOCAL.get()));
            System.out.println(String.format(Thread.currentThread().getName() + " 值1：%s", TRANSMITTABLE_THREAD_LOCAL.get()));
            System.out.println("=======");
        });

        TimeUnit.SECONDS.sleep(1);

        EXECUTOR_SERVICE.submit(() -> {
            System.out.println("=======");
            System.out.println(String.format(Thread.currentThread().getName() + " 值：%s", INHERITABLE_THREAD_LOCAL.get()));
            System.out.println(String.format(Thread.currentThread().getName() + " 值1：%s", TRANSMITTABLE_THREAD_LOCAL.get()));
            System.out.println("=======");
        });
        TimeUnit.SECONDS.sleep(1);

        EXECUTOR_SERVICE.submit(() -> {
            System.out.println("=======");
            System.out.println(String.format(Thread.currentThread().getName() + " 值：%s", INHERITABLE_THREAD_LOCAL.get()));
            System.out.println(String.format(Thread.currentThread().getName() + " 值1：%s", TRANSMITTABLE_THREAD_LOCAL.get()));
            System.out.println("=======");
        });

        TimeUnit.MINUTES.sleep(1);


    }
}
