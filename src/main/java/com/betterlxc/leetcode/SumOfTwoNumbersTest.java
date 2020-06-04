package com.betterlxc.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxincheng
 * @date 2020/5/28
 */

public class SumOfTwoNumbersTest {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];
            if (map.containsKey(a)) {
                return new int[]{i, map.get(a)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    @Test
    public void twoSum() {
        twoSum(new int[]{1, 2, 3, 4}, 5);
    }
}
