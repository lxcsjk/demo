package com.betterlxc.concurrent;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class CountDownLatchTest {

  private final Object lock = new Object();

  @Test
  @SneakyThrows
  public void countDownLatchTest() {
    int threadNum = 25;
    final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

    IntStream.range(0, 25).parallel().forEach(i -> {
      int j = i + 1;
      new Thread(() -> {
        log.info("thread {} start", j);

      doing();
        try {
          TimeUnit.MILLISECONDS.sleep(500 * j);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        log.info("thread {} finish", j);
        countDownLatch.countDown();
        log.info("countDownLatch ---> {}", countDownLatch.toString());
      }).start();
    });
    countDownLatch.await(10, TimeUnit.SECONDS);
    log.info(threadNum + " thread finish");
  }

  public void doing() {
    synchronized (lock) {
      System.out.println(LocalDateTime.now());
    }
  }
}
