package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liuxincheng
 * @date 2019-06-20
 */
public class BlockingQueueTest {

    public static final BlockingQueue<String> ARR_BLOCKING_QUEUE = new ArrayBlockingQueue<>(10);

    public static final ConcurrentLinkedQueue<String> CONCURRENT_LINKED_QUEUE = new ConcurrentLinkedQueue<>();
    @Test
    public void test01() throws InterruptedException {
        Producer producer = new Producer(ARR_BLOCKING_QUEUE);
        Consumer consumer = new Consumer(ARR_BLOCKING_QUEUE);
        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }

    class Consumer implements Runnable {
        protected BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Producer implements Runnable {
        protected BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.put("1");
                Thread.sleep(1000);
                queue.put("2");
                Thread.sleep(1000);
                queue.put("3");

                queue.add(null);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
