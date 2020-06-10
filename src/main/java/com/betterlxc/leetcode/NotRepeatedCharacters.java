package com.betterlxc.leetcode;

import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liuxincheng
 * @date 2020/6/1
 */
public class NotRepeatedCharacters {

    public int lengthOfLongestSubstring(String s) {
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
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        String res = "";
        //使用hashmap记录遍历过的字符的索引，当发现重复的字符时，可以将窗口的左边直接跳到该重复字符的索引处
        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, List<String>> result = new HashMap<>();

        // try to extend the range [i, j]
        //j负责向右边遍历，i根据重复字符的情况进行调整
        for (int j = 0, i = 0; j < n; j++) {
            //当发现重复的字符时,将字符的索引与窗口的左边进行对比，将窗口的左边直接跳到该重复字符的索引处
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(map.get(c), i);
            }
            //记录子字符串的最大的长度
            ans = Math.max(ans, j - i + 1);
            List<String> orDefault = result.getOrDefault(ans, Lists.newArrayList());
            String substring = s.substring(i, j + 1);
            if (substring.length() == ans) {
                orDefault.add(substring);
                result.put(ans, orDefault);
            }

            //map记录第一次遍历到key时的索引位置，j+1,保证i跳到不包含重复字母的位置
            map.put(c, j + 1);
        }
        return ans;
    }

    @Test
    public void test() {
        lengthOfLongestSubstring2("asdabcdea");
    }
}
