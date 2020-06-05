package com.betterlxc.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author liuxincheng
 * @date 2019-06-20
 */
public class PriorityQueueTest {


    @Test
    public void test() throws InterruptedException {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int[] numss = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : numss) {
            priorityQueue.add(num);
            if (priorityQueue.size() > 5) {
                priorityQueue.poll();
            }
        }

        Integer peek = priorityQueue.peek();
        System.out.println(Arrays.toString(priorityQueue.toArray()));

    }

}
