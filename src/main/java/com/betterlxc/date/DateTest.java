package com.betterlxc.date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author liuxincheng
 * @date 2019/10/9
 */
public class DateTest {

    @Test
    public void test() {
        System.out.println(ChronoUnit.DAYS.between(LocalDateTime.of(2018, 6, 28, 0, 0), LocalDateTime.now()));
    }

}
