package com.betterlxc.pinyin;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class JPinYinTest {

  private static final List<String> DATA = Lists.newArrayList("楼层3","楼层1");

  @Test
  public void pinyinSort() {
    List<String> list = PinyinSortUtils.pinyinSort(DATA);

    System.out.println();
  }
}

