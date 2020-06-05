package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/3
 */
public class IsReverseNum {

    @Test
    public void reverse() {
        System.out.println(isPalindrome(2332));
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }

        return x == reverseNum || x == reverseNum / 10;
    }
}
