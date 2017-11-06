package com.betterlxc.ip;

import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class IpUtilTest {

  private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

  private static final Splitter SPLITTER = Splitter.on('|');

  @Test
  public void test1() {
    List<String> list = Lists.newArrayList();

    IntStream.range(0, 1000).parallel().forEach(i -> list.add(randomIp()));

    Stopwatch stopwatch = Stopwatch.createStarted();
    list.parallelStream().forEach(ip -> {
      try {
        System.out.println(IpUtil.find1(ip));
      } catch (Exception e) {
        System.out.println("异常的IP ----> " + ip);
      }
    });

    System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
  }

  @Test
  public void test2() {
    List<String> list = Lists.newArrayList();

    list.add("223.129.117.1");

    Stopwatch stopwatch = Stopwatch.createStarted();
    list.forEach(ip -> {
      try {
        //城市ip,国家，区域，省份，城市，运营商
        List<String> temp = SPLITTER.splitToList(IpUtil.find2(ip).getRegion());

        System.out.println(ip + "  ----->  " + temp.get(2) + temp.get(3));
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("异常的IP ----> " + ip);
      }
    });

    System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
  }

  private static String randomIp() {
    return String.valueOf(RANDOM.nextInt(1000000) % 255) +
        "." +
        RANDOM.nextInt(1000000) % 255 +
        "." +
        RANDOM.nextInt(1000000) % 255 +
        "." +
        "1";
  }
}
