package com.betterlxc.string;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by LXC on 2017/4/24.
 */
public class CharMatcherTest {

    @Test
    public void test() {
        System.out.println(aplusb(2, 4));
        System.out.println(aplusb2(2, 4));
    }

    private int aplusb(int a, int b) {
        if (b == 0) {
            return a;
        }
        return aplusb(a ^ b, (a & b) << 1);
    }

    private int aplusb2(int a, int b) {
        if (b == 0) {
            return a;
        }
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }

    @Test
    public void test1() {
        System.out.println(2 << 1);
        System.out.println(2 << 2);
        System.out.println(2 << 3);
        System.out.println(2 >> 4);
        System.out.println(2 >> 5);
        System.out.println(2 >> 6);

    }

    @Test
    public void test2() {
//    List<String> list = Lists.newArrayList("1", "1", "1", "1", "1", "1");
//
//    Iterator<String> iterator = list.iterator();
//
//    for (; iterator.hasNext(); ) {
//      System.out.println(iterator.next());
//    }

        String keyword = "个性定制/设计服务/DIY->设计服务->设计素材\\源文件";
        if (keyword.contains("\\")) {
            keyword = keyword.replace("\\", "\\\\\\\\");
        }
        System.out.println(keyword);
    }

    @Test
    public void test3() {
        int n = 20;
        Random rand = new Random();
        boolean[] bool = new boolean[n];

        int randInt;
        for (int i = 0; i < 3; i++) {
            do {
                randInt = rand.nextInt(n);
            } while (bool[randInt]);
            bool[randInt] = true;
            System.out.println(randInt);
        }
    }

    @Test
    public void test4() throws ParseException {
        String str = "2018-04-08 10:20:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date date = formatter.parse(str);
        System.out.println(date.getHours());


    }
}
