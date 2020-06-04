package com.betterlxc.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liuxincheng
 * @date 2020/6/1
 */
public class NotRepeatedCharactersTest {

    @Test
    public void lengthOfLongestSubstring() {
        String s = "abcabcdbb";

        Set<Character> set = new HashSet<>();
        int n = s.length();
        int rk = -1, ans = 0;

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans, set.size());

            System.out.println(Arrays.toString(set.toArray()));
        }
    }
}
