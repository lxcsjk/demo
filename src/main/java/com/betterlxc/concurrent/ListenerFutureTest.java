package com.betterlxc.concurrent;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
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

    Futures.addCallback(endFuture, new FutureCallback<String>() {
      @Override
      public void onSuccess(String result) {
        log.info(result);
        log.info("=======OK=======");
      }

      @Override
      public void onFailure(Throwable t) {
        log.error("异常 ============>", t);
      }
    });
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
    int CPUNum = Runtime.getRuntime().availableProcessors();
    log.info("CPU 个数  ---->   {}", CPUNum);

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
  public void randInt() {
    Random rand = new Random();
    IntStream.range(0, 20).forEach(i -> System.out.println(rand.nextInt(500) + 500));
  }
}
