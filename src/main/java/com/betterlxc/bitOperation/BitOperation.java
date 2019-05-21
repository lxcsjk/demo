package com.betterlxc.bitOperation;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2019-05-17
 */
public class BitOperation {

    @Test
    public void test() {
        int x = 1;
        int y = 12;

        x = x ^ y;
        y = x ^ y;
        x = x ^ y;

        System.out.println(x);
        System.out.println(y);
    }

    @Test
    public void test1() {
        System.out.println(find(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4}));
    }


    int find(int[] arr) {
        int tmp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            System.out.println(tmp + "||" + arr[i]);
            tmp = tmp ^ arr[i];
            System.out.println(tmp);
        }
        return tmp;
    }


}
