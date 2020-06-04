package com.betterlxc.sortAlgorithm;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxincheng
 * @date 2019-07-15
 */
public class BloomFilterTest {

    @Test
    public void test() throws UnsupportedEncodingException {
//        BloomFilter<String> bloomFilter = new BloomFilter<>()

//        String s = "a:3:{s:5:\"money\";d:100;s:6:\"reduce\";d:10;s:10:\"max_reduce\";d:20;}";
//
//        Mixed mixed = Pherialize.unserialize(s);
//        MixedArray mixedArray = mixed.toArray();
//
//        Double money = mixedArray.getDouble("money");
//        Double reduce = mixedArray.getDouble("reduce");
//        Double maxReduce = mixedArray.getDouble("max_reduce");
//
//        System.out.println(money);


//        String encode = URLEncoder.encode("[{\"type\":\"VALID_QUESTION\",\"names\":[\"2154041_336\"]}]", "UTF-8");
//
//        System.out.println(encode);
//        String CAT_TYPE = "VALID_QUESTION-";
//        String CAT_PARAMS = "[{\"type\":\"VALID_QUESTION\",\"names\":%s}]";
//
//        List<String> names = Lists.newArrayList(335, 336, 337).stream().map(questionDTO -> 2154041 + "_" + questionDTO).collect(Collectors.toList());
//        String format = String.format(CAT_PARAMS, JSON.toJSONString(names));
//        System.out.println(format);


        int[] arr = new int[]{3, 3};
        int[] ints = twoSum(arr, 6);

        String s = Arrays.toString(ints);
        System.out.println(s);
    }

    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                arr[0] = i;
                arr[1] = map.get(nums[i]);
            }
            map.put(target - nums[i], i);
        }
        return arr;
    }
}
