package com.betterlxc.string;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Set;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class JoinerTest {

  private static final Joiner JOINER = Joiner.on(",").skipNulls();

  @Test
  public void joinTest() {
    Set<String> list = Sets.newHashSet("A", "B", "C", null);
    log.info("list  ----- > {}", list);
    String joinerStr = JOINER.join(list);
    log.info("分割链接后的字符串  ----- > {}", joinerStr);
  }
}
