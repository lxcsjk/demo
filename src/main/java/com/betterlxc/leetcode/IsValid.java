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
        System.out.println(isValid("()())))((()))(())(()()()"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.empty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
