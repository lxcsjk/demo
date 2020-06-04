package com.betterlxc.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author liuxincheng
 * @date 2020/5/14
 */
public class ThreadTest {

    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("线程1  开始");
            sleep();
            flag = false;
            System.out.println("线程1  结束");
        }).start();

        new Thread(() -> {
            System.out.println("线程2  开始, flag: " + flag);
//            boolean b = true;
            while (flag) {
//                if (b) {
                System.out.println("进来了");
//                    b = false;
//                }
            }
            System.out.println("线程2  结束, flag: " + flag);
        }).start();

        TimeUnit.MINUTES.sleep(1);
    }

    @SneakyThrows
    static void sleep() {
        TimeUnit.SECONDS.sleep(5);
    }
}
