package com.betterlxc.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class ExecutorTest {

  private static final ThreadFactory THREAD_FACTORY =
      new ThreadFactoryBuilder().setNameFormat("showcase-thread-%s").build();

  private static final BlockingQueue<Runnable> SHOWCASE_BLOCKING_QUEUE = new LinkedBlockingQueue<>(200);

  private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(
      100, 200, 5, TimeUnit.SECONDS, SHOWCASE_BLOCKING_QUEUE,
      THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy()
  );

  @Test
  public void test01() throws InterruptedException {
    for (int i = 0; i < 1005; i++) {
      POOL.submit(()->{


      });
    }
    System.out.println(213123);

//    TimeUnit.SECONDS.sleep(100000000);
  }


  private Void safeProcess(Integer showcase) {
    System.out.println(showcase);
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
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
