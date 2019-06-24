package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxincheng
 * @date 2019-06-20
 */
public class CyclicBarrierTest {

    @Test
    public void test() throws InterruptedException {
        Runnable barrier1Action = () -> System.out.println("BarrierAction 1 executed ");
        Runnable barrier2Action = () -> System.out.println("BarrierAction 2 executed ");

        CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);
        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1,
            barrier2);
        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1,
            barrier2);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();

        TimeUnit.MINUTES.sleep(1);

    }

    class CyclicBarrierRunnable implements Runnable {
        CyclicBarrier barrier1 = null;
        CyclicBarrier barrier2 = null;

        public CyclicBarrierRunnable(
            CyclicBarrier barrier1,
            CyclicBarrier barrier2) {
            this.barrier1 = barrier1;
            this.barrier2 = barrier2;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                    " waiting at barrier 1");
                this.barrier1.await();

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                    " waiting at barrier 2");
                this.barrier2.await();
                System.out.println(Thread.currentThread().getName() +
                    " done!");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
