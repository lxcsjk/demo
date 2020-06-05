package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/4
 */
public class MaxArea {

    @Test
    public void test() {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] arr) {
        int i = 0, j = arr.length - 1, res = 0;
        while (i < j) {
            res = arr[i] < arr[j] ?
                Math.max(res, (j - i) * arr[i++]) :
                Math.max(res, (j - i) * arr[j--]);
        }
        return res;
    }
}
