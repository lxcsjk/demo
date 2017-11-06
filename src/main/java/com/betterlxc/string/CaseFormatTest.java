package com.betterlxc.string;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class CaseFormatTest {

  @Test
  public void lowerUnderscoreTest() {
    String underscoreStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "dateCreated");
    log.info("驼峰  转  小写下划线   -------->   {}", underscoreStr);
  }

  @Test
  public void lowerHyphenTest() {
    String lowerHyphenStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "dateCreated");
    log.info("驼峰  转  连字号   -------->   {}", lowerHyphenStr);
  }

  @Test
  public void upperUnderscoreTest() {
    String upperUnderscoreStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "dateCreated");
    log.info("驼峰  转  大写下划线   -------->   {}", upperUnderscoreStr);
  }
}
