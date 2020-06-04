package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/3
 */
public class ReverseNumTest {

    @Test
    public void reverse() {
        long x = Long.parseLong("12345");

        long ans = 0;
        while (x != 0) {
            long pop = x % 10;
            // 2147483647
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                System.out.println(0);
            }
            // -2147483648
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                System.out.println(0);
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        System.out.println(ans);
    }
}
