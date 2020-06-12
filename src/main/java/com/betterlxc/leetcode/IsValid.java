package com.betterlxc.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author liuxincheng
 * @date 2020/6/11
 */
public class IsValid {
    private static final Map<Character, Character> MAP = new HashMap<Character, Character>() {{
        put('}', '{');
        put(']', '[');
        put(')', '(');
    }};

    @Test
    public void test() {
        System.out.println(isValid(""));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (MAP.containsKey(c)) {
                char e = stack.empty() ? '#' : stack.pop();
                if (e != MAP.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }

        }
        return stack.isEmpty();
    }
}
