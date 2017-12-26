package com.betterlxc.concurrent;

import com.google.common.collect.Sets;
import com.jsoniter.output.JsonStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class SemaphoreTest {

  @Test
  @SneakyThrows
  public void semaphoreTest() {
//    final Semaphore semaphore = new Semaphore(2);
//
//    IntStream.range(0, 5).forEach(i -> {
//      final int j = i;
//      new Thread(() -> {
//        try {
//          semaphore.acquire();
//          System.out.println("student " + j + " read book");
//          TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//        System.out.println("student " + j + " finish read book");
//        semaphore.release();
//      }).start();
//    });
//    TimeUnit.SECONDS.sleep(10);
    JsonStream.serialize(Sets.newHashSet("1", 2, 4, 4));
  }

}
