package com.betterlxc.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author liuxincheng
 * @date 2020/7/6
 */
public class Candy {

    @Test
    public void test() {
        System.out.println(candy(new int[]{2, 1, 1}));
    }

    public int candy(int[] ratings) {
        int[] arr1 = new int[ratings.length];
        int[] arr2 = new int[ratings.length];

        Arrays.fill(arr1, 1);
        Arrays.fill(arr2, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                arr1[i] = arr1[i - 1] + 1;
            }
        }

        int count = arr1[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                arr2[i] = arr2[i + 1] + 1;
            }
            count += Math.max(arr1[i], arr2[i]);
        }
        return count;
    }

}
