package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/4
 */
public class IntToRomanTest {

    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};
    private static final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};


    @Test
    public void test() {
        System.out.println(intToRoman(3));
    }

    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < VALUES.length && num >= 0; i++) {
            while (VALUES[i] <= num) {
                num = num - VALUES[i];
                stringBuilder.append(SYMBOLS[i]);
            }
        }
        return stringBuilder.toString();
    }

    public String intToRoman2(int num) {
        return THOUSANDS[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }
}
