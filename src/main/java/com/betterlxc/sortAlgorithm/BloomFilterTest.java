package com.betterlxc.sortAlgorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

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
        String CAT_TYPE = "VALID_QUESTION-";
        String CAT_PARAMS = "[{\"type\":\"VALID_QUESTION\",\"names\":%s}]";

        List<String> names = Lists.newArrayList(335, 336, 337).stream().map(questionDTO -> 2154041 + "_" + questionDTO).collect(Collectors.toList());
        String format = String.format(CAT_PARAMS, JSON.toJSONString(names));
        System.out.println(format);

    }
}
