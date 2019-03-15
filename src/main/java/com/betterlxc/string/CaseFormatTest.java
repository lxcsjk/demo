package com.betterlxc.string;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.sun.deploy.util.Waiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.google.common.base.Ascii.isLowerCase;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class CaseFormatTest {

    @Test
    public void lowerUnderscoreTest() {
        String underscoreStr = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "date_created");
        log.info("驼峰  转  小写下划线   -------->   {}", underscoreStr);
    }

    @Test
    public void lowerHyphenTest() {
        String lowerHyphenStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "dateCreated");
        log.info("驼峰  转  连字号   -------->   {}", lowerHyphenStr);
    }

    @Test
    public void upperUnderscoreTest() {
        List<Integer> list = Lists.newArrayList(1, 2);
        System.out.println(list.get(10));
    }

    @Test
    public void test() {
        toUpperCase("abcd");
    }

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
}
