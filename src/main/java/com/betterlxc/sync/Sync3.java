package com.betterlxc.sync;

/**
 * @author liuxincheng
 * @date 2019-03-19
 */
public class Sync3 implements Runnable {
    static int i = 0;

    /**
     * 作用于静态方法,锁是当前class对象,也就是
     * AccountingSyncClass类对应的class对象
     */
    public static synchronized void increase() {
        i++;
    }

    /**
     * 非静态,访问时锁不一样不会发生互斥
     */
    public synchronized void increase4Obj() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Sync3());
        Thread t2 = new Thread(new Sync3());
        //启动线程
        t1.start();
        t2.start();

        t1.join();
        t2.join();
//        new Sync3().increase4Obj();
        System.out.println(i);
    }
}