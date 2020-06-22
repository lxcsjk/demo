package com.betterlxc.troubleShoutting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuxincheng
 * @date 2020/6/17
 */
public class OOMTest {

//    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(1);
//        int max = 10000;
//        List<Person> list = new ArrayList<>(max);
//        for (int j = 0; j < max; j++) {
//            Person p = new Person();
//            p.setAge(100);
//            p.setName("菩提树下的杨过");
//            list.add(p);
//        }
//        System.out.println("ready!");
//        latch.await();
//    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        int max = 100;
        for (int i = 0; i < max; i++) {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            t.setName("thread-" + i);
            t.start();
        }
        Thread t = new Thread(() -> {
            int i = 0;
            while (true) {
                i = (i++) / 10;
            }
        });
        t.setName("BUSY THREAD");
        t.start();
        System.out.println("ready");
        latch.await();
    }


    public static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
