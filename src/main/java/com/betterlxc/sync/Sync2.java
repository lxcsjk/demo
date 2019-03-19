package com.betterlxc.sync;

/**
 * @author liuxincheng
 * @date 2019-03-19
 */
public class Sync2 implements Runnable {
    private static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1 = new Thread(new Sync2());
        //new新实例
        Thread t2 = new Thread(new Sync2());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
