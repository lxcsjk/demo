package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/5
 */
public class LongestCommonPrefix {

    @Test
    public void longestCommonPrefix() {
        System.out.println(longestCommonPrefix(new String[]{"12", "123", "1234"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String str = strs[0];
        char[] chars = str.toCharArray();

        StringBuilder ans = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (char aChar : chars) {
            String temp = String.valueOf(aChar);
            res.append(temp);
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].startsWith(res.toString())) {
                    return ans.toString();
                }
            }
            ans.append(temp);
        }
        return res.toString();
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
