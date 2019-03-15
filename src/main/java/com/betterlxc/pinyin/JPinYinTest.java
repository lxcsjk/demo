package com.betterlxc.pinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.junit.Test;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class JPinYinTest {

  @Test
  public void pinyin() throws PinyinException {
    String str = "北京";
    String result = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
    System.out.println(result);
  }
}
