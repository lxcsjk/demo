package com.betterlxc.sortAlgorithm;

import de.ailis.pherialize.Mixed;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2019-07-15
 */
public class BloomFilterTest {

    @Test
    public void test() {
//        BloomFilter<String> bloomFilter = new BloomFilter<>()

        String s = "a:3:{s:5:\"money\";d:100;s:6:\"reduce\";d:10;s:10:\"max_reduce\";d:20;}";

        Mixed mixed = Pherialize.unserialize(s);
        MixedArray mixedArray = mixed.toArray();

        Double money = mixedArray.getDouble("money");
        Double reduce = mixedArray.getDouble("reduce");
        Double maxReduce = mixedArray.getDouble("max_reduce");

        System.out.println(money);
    }
}
