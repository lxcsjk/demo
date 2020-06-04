package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/1
 */
public class LongestPalindromeTest {

    @Test
    public void longestPalindrome() {
        String s = "abba";

        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (check(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        }
        System.out.println(ans);
    }

    private boolean check(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void longestPalindrome1() {
        String s = "abba";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        System.out.println(s.substring(start, end + 1));
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;

        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
