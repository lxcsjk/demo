package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/4
 */
public class RomanToIntTest {

    @Test
    public void test() {
        System.out.println(romanToInt("III"));
    }

    /**
     * 当小值在大值的左边，则减小值，如 IV=5-1=4；
     * 当小值在大值的右边，则加小值，如 VI=5+1=6；
     *
     * @param num
     * @return
     */
    public int romanToInt(String num) {
        int sum = 0;
        int preNum = getValue(num.charAt(0));
        for (int i = 1; i < num.length(); i++) {
            int n = getValue(num.charAt(i));
            if (preNum < n) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = n;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
