package com.betterlxc.string;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static com.google.common.base.Ascii.isLowerCase;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class CaseFormatTest {

    public static String toUpperCase(String string) {
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (isLowerCase(string.charAt(i))) {
                char[] chars = string.toCharArray();
                for (; i < length; i++) {
                    char c = chars[i];
                    if (isLowerCase(c)) {
                        chars[i] = (char) (c & 0x5f);
                    }
                }
                return String.valueOf(chars);
            }
        }
        return string;
    }

    @Test
    public void lowerUnderscoreTest() {
        String collect = Lists.newArrayList("1", "2", "3", "4").stream()
            .collect(Collectors.joining(","));

        String join = Joiner.on(',').join(Lists.newArrayList(1, 2, 3,4));
        System.out.println(join);


        String underscoreStr = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "date_created");
        log.info("驼峰  转  小写下划线   -------->   {}", underscoreStr);
    }

    @Test
    public void lowerHyphenTest() {
        String lowerHyphenStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "DateCreated");
        log.info("驼峰  转  连字号   -------->   {}", lowerHyphenStr);
    }

    @Test
    public void upperUnderscoreTest() {
//        List<Integer> list = Lists.newArrayList(1, 2);
//        System.out.println(list.get(10));
//
//        BigDecimal num = BigDecimal.valueOf(8.8);
//
//        System.out.println(num.negate());

        System.out.println("915 2011 5789 7525".replaceAll(" ", ""));
    }

    @Test
    public void test() {
        toUpperCase("abcd");
    }
}
