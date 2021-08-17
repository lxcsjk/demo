package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author liuxincheng
 * @date 2019-06-20
 */
public class BlockingDequeTest {


    @Test
    public void test() throws InterruptedException {

        BlockingDeque<String> deque = new LinkedBlockingDeque<>();

        deque.addFirst("1");
        deque.addLast("2");
        deque.put("3");
        String two = deque.takeLast();
        System.out.println(two);
        String one = deque.takeFirst();
        System.out.println(one);
        String s = deque.take();
        System.out.println(s);

    }

}
