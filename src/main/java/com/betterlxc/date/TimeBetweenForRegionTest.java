package com.betterlxc.date;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static com.betterlxc.date.MwDateUtil.HH_mm;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class TimeBetweenForRegionTest {

    @Test
    public void test1() {
        System.out.println(JSON.toJSON(transform(LocalDateTime.now().minusMinutes(180), LocalDateTime.now(), 90)));
    }

    public List<Date[]> transform(LocalDateTime start, LocalDateTime end, int region) {
        LocalDateTime h1;
        int minute = start.getMinute();
        int a = minute / region;
        h1 = start.minusMinutes(minute - region * a);

        System.out.println(MwJdk8DateUtil.getDate2String(start));
        System.out.println(MwJdk8DateUtil.getDate2String(h1));
        System.out.println();

        LocalDateTime h2;
        int minute1 = end.getMinute();
        int b = minute1 / region;
        h2 = end.plusMinutes(region * (b + 1) - minute1);

        System.out.println(MwJdk8DateUtil.getDate2String(end));
        System.out.println(MwJdk8DateUtil.getDate2String(h2));
        System.out.println();

        long minutes = ChronoUnit.MINUTES.between(h1, h2);
        long i = minutes / region;
        List<Date[]> list = Lists.newArrayList();
        LocalDateTime temp = h1;
        for (int j = 1; j <= i; j++) {
            LocalDateTime next = h1.plusMinutes(region * j);
            list.add(new Date[]{MwJdk8DateUtil.localDatseTime2Date(temp), MwJdk8DateUtil.localDatseTime2Date(next)});
            temp = h1.plusMinutes(30 * j);
        }

        list.forEach(dates -> System.out.println(MwDateUtil.getDate2String(dates[1], HH_mm)));
        return list;
    }
}
