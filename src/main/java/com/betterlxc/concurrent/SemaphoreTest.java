package com.betterlxc.concurrent;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by LXC on 2017/5/10.
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
          System.out.println("student " + j + " read book");
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

}
