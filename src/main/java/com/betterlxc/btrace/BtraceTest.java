package com.betterlxc.btrace;

import java.util.Random;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class BtraceTest {
  public static Random random = new Random();

  public static void main(String[] args) throws Exception {
    new BtraceTest().run();
  }

  public void run() throws Exception {
    while (true) {
      add(random.nextInt(10), random.nextInt(10));
    }
  }

  public int add(int a, int b) throws Exception {
    Thread.sleep(random.nextInt(10) * 100);
    return a + b;
  }
}
