package com.betterlxc.concurrent;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class ListenerFutureTest {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    private static final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);

    private static final ThreadFactory THREAD_FACTORY =
        new ThreadFactoryBuilder().setNameFormat("listenerFutureTest-thread-%s").build();

    private static final ListeningExecutorService POOL =
        MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20, THREAD_FACTORY));

    ThreadPoolExecutor pool = new ThreadPoolExecutor(
        1,
        2,
        60,
        TimeUnit.SECONDS, new ArrayBlockingQueue<>(3)
        , THREAD_FACTORY
        , new ThreadPoolExecutor.AbortPolicy()
    );


    public void executeChain() {
        AsyncFunction<String, String> asyncFunction = input -> executorService.submit(() -> {
            log.info("STEP1 >>>" + Thread.currentThread().getName());
            return input + "|||step 1 ===--===||| ";
        });

        AsyncFunction<String, String> asyncFunction2 = input -> executorService.submit(() -> {
            log.info("STEP2 >>>" + Thread.currentThread().getName());
            return input + "|||step 2 ===--===---||| ";
        });

        ListenableFuture<String> startFuture = executorService.submit(() -> {
            log.info("BEGIN >>>" + Thread.currentThread().getName());
            return "BEGIN--->";
        });

        ListenableFuture<String> future = Futures.transformAsync(startFuture, asyncFunction, executor);

        ListenableFuture<String> endFuture = Futures.transformAsync(future, asyncFunction2, executor);

//        Futures.addCallback(endFuture, new FutureCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                log.info(result);
//                log.info("=======OK=======");
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                log.error("异常 ============>", t);
//            }
//        });
    }

    @Test
    public void listenerFutureTest() {
        log.info("========START=======");
        log.info("MAIN >>>" + Thread.currentThread().getName());
        ListenerFutureTest chain = new ListenerFutureTest();
        chain.executeChain();
        log.info("========END=======");
//    if (!executor.isShutdown()) executor.shutdown();
    }

    @Test
    public void listenerFutureTest1() throws ExecutionException, InterruptedException {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        log.info("CPU 个数  ---->   {}", cpuNum);

        Future<LocalDateTime> task = executorService.submit(() -> {
            log.info("{}", LocalDateTime.now());
            return LocalDateTime.now();
        });
        task.cancel(false);

        Boolean flag = task.isDone();
        log.info("是不是完成了  ----->      {}", flag);

        Boolean flags = task.isCancelled();
        log.info("是不是取消了  ----->      {}", flags);

        Object obj = task.get();
        log.info("{}", obj);

        log.info("end");
    }

    @Test
    public void listenerFutureTest2() throws InterruptedException {
        List<Map<String, Long>> mapList = Lists.newArrayList();

        IntStream.range(0, 10).forEach(i -> {
            Map<String, Long> tempMap = Maps.newHashMap();
            tempMap.put(UUID.randomUUID().toString(), System.currentTimeMillis());
            mapList.add(tempMap);
        });

        List<Callable<Void>> callbacks = mapList
            .stream()
            .map(map -> (Callable<Void>) () -> process(map))
            .collect(Collectors.toList());
        log.info("========invokeAll=======");
        POOL.invokeAll(callbacks);
        log.info("========JOIN=======   {}", mapList);
    }

    private Void process(Map<String, Long> map) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5000);
        log.info("========JOIN=======   {}", map);
        map.put(UUID.randomUUID().toString(), System.currentTimeMillis());
        return null;
    }

    @Test
    public void randInt() throws InterruptedException {

        List<Task> list = Lists.newArrayList();
        for (int i = 0; i < 200; i++) {
            list.add(new Task());
        }

        list.parallelStream().forEach(executor::submit);
        executor.isShutdown();
        List<Runnable> runnable = executor.shutdownNow();

        runnable.parallelStream().forEach(Runnable::run);

        System.out.println();
    }

    class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(3);
            return "韩伟大傻逼";
        }
    }
}
