package com.betterlxc.date;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuxincheng
 * @date 2019/10/9
 */
public class DateTest {

    @Test
    public void test() {
//        System.out.println(ChronoUnit.DAYS.between(LocalDateTime.of(2018, 6, 28, 0, 0), LocalDateTime.now()));

        int i = MwDateUtil.diffDay(new Date(1572883200000L), new Date());
        System.out.println();

    }
}
