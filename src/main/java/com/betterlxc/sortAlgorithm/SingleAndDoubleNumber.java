package com.betterlxc.sortAlgorithm;

/**
 * @author liuxincheng
 * @date 2019-05-09
 */
public class SingleAndDoubleNumber implements Runnable {

    private int i = 1;

    public static void main(String[] args) {
        SingleAndDoubleNumber t = new SingleAndDoubleNumber();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();

    }

    @Override
    public void run() {
        while (i <= 100) {
            synchronized (this) {
                // 先唤醒另外一个线程
                notify();

                System.out.println(Thread.currentThread().getName() + ":" + i);
                i++;
                try {
                    // 打印完之后，释放资源，等待下次被唤醒
                    if (i < 100) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
