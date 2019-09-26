package com.betterlxc.pinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.text.Collator;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class JPinYinTest {

  private static final List<String> DATA = Lists.newArrayList(
      "池座"
      , "一楼"
      , "三楼"
      , "二楼"
      , "2楼"
      , "楼座一层"
      , "楼座一层(后)"
      , "楼座一层(左)"
      , "楼座一层(右)"
      , "楼座二层"
      , "楼座二层(左)"
      , "楼座二层(右)"
      , "室内厅"
      , "室内厅(左)"
      , "室内厅(右)"
      , "一楼池座"
      , "二楼楼座"
      , "二楼单号包厢"
      , "二楼双号包厢"
      , "A1区"
      , "A2区"
      , "B1区"
      , "B2区"
      , "C1区"
      , "C2区"
      , "D1区"
      , "D2区"
      , "VIP1区"
      , "VIP2区"
      , "传奇小剧场"
      , "一层"
      , "二层"
      , "包厢"
      , "博纳星辉剧院"
      , "102通道"
      , "113通道"
      , "114通道"
      , "316a通道"
      , "316b通道"
      , "317a通道"
      , "317b通道"
      , "318通道"
      , "319通道"
      , "320通道"
      , "321a通道"
      , "321b通道"
      , "322b通道"
      , "301通道"
      , "322a通道"
      , "302通道"
      , "303通道"
      , "307通道"
      , "306b通道"
      , "306a通道"
      , "305a通道"
      , "305b通道"
      , "304通道"
      , "308通道"
      , "311a通道"
      , "310b通道"
      , "310a通道"
      , "309通道"
      , "313通道"
      , "314通道"
      , "311b通道"
      , "312通道"
      , "315通道"
      , "V02"
      , "V03"
      , "V05"
      , "V06"
      , "V07"
      , "V08"
      , "V09"
      , "V10"
      , "V11"
      , "V12"
      , "V15"
      , "V16"
      , "V17"
      , "V18"
      , "V19"
      , "V20"
      , "V21"
      , "V22"
      , "V23"
      , "V25"
      , "V26"
      , "V27"
      , "V28"
      , "V29"
      , "V30"
      , "V31"
      , "V32"
      , "V33"
      , "V35"
      , "V36"
      , "V37"
      , "V38"
      , "V39"
      , "V40"
      , "V41"
      , "V42"
      , "V43"
      , "V45"
      , "V46"
      , "V47"
      , "V48"
      , "V49"
      , "V50"
      , "V51"
      , "V52"
      , "V53"
      , "V01"
      , "117通道"
      , "116通道"
      , "115通道"
      , "112通道"
      , "111通道"
      , "110通道"
      , "108通道"
      , "107通道"
      , "106通道"
      , "105通道"
      , "103通道"
      , "101通道"
      , "122通道"
      , "121通道"
      , "119通道"
      , "118通道"
      , "场地区域"
      , "三层(右)"
      , "三层(左)"
      , "三层"
      , "二层(右)"
      , "二层(左)"
      , "二层(后)"
      , "二层单号包厢"
      , "二层双号包厢"
      , "二层VIP7"
      , "二层VIP5"
      , "二层VIP3"
      , "二层VIP1"
      , "二层VIP2"
      , "二层VIP4"
      , "二层VIP6"
      , "三层双号包厢"
      , "三层单号包厢"
      , "正厅前区"
      , "正厅后区"
      , "一层前区茶桌"
      , "一层后区排椅"
      , "二层包厢"
      , "二层排椅"
      , "11"
      , "123"
      , "wee"
      , "2"
      , "1"
      , "D区"
      , "A区"
      , "B区"
      , "C区"
      , "二楼坐席"
      , "一楼坐席"
      , "楼上"
      , "楼下"
      , "c"
      , "d"
      , "a"
      , "b"
      , "53修改a"
      , "34修改1"
      , "123123"
      , "但是sd"
      , "vip"
      , "二楼分区"
      , "一楼分区"
      , "分区一"
      , "分区二"
      , "分区三"
      , "四层"
      , "fa"
      , "二区"
      , "一区"
      , "e"
      , "f"
      , "4"
      , "q"
      , "3"
      , "aaa"
      , "5"
      , "ga"
      , "fads"
      , "g"
      , "eeeeeeeee"
      , "厅座"
      , "楼座"
      , "大厅无座"
      , "观众席正厅"
      , "西包厢一楼"
      , "东包厢一楼"
      , "西包厢二楼"
      , "东包厢二楼"
      , "观众席二楼"
      , "贵宾席"
      , "西包厢三楼"
      , "东包厢三楼"
      , "观众席三楼"
      , "二楼侧座"
      , "乐池"
      , "二层侧座"
      , "111"
      , "分区嗨"
      , "分区1"
      , "fad"
      , "fas"
      , "负一层"
      , "1楼 101区"
      , "1楼 102区"
      , "1楼 103区"
      , "1楼 201区"
      , "1楼 202区"
      , "1楼 203区"
      , "2楼 301区"
      , "2楼 303区"
      , "2楼 302区"
      , "实验空间"
      , "实验空间无座"
      , "A区后"
      , "A区前"
      , "区域一"
      , "区域二"
      , "高邮张家辉"
      , "高邮张学友"
      , "1楼"
      , "13"
      , "12"
      , "15"
      , "8"
      , "14"
      , "9"
      , "7"
      , "6"
      , "16"
      , "10"
      , "21"
      , "20"
      , "19"
      , "18"
      , "17"
      , "22"
      , "G区"
      , "J区"
      , "测试区域"
      , "2分区"
      , "1分区"
      , "超极电影世界 2厅"
      , "optest2"
      , "3楼"
      , "4台上"
      , "C4"
      , "D1"
      , "D4"
      , "3台上"
      , "D3"
      , "C2"
      , "C3"
      , "5台上"
      , "A4"
      , "B1"
      , "B2"
      , "B3"
      , "D2"
      , "7台下"
      , "6台下"
      , "5台下"
      , "B4"
      , "C1"
      , "A1"
      , "A2"
      , "A3"
      , "6台上"
      , "8台下"
      , "20台下"
      , "21台下"
      , "24台上"
      , "4台下"
      , "1台下"
      , "7台上"
      , "18台下"
      , "19台下"
      , "1台上"
      , "22台下"
      , "23台下"
      , "24台下"
      , "3台下"
      , "2台下"
      , "22台上"
      , "21台上"
      , "20台上"
      , "加自己"
      , "23台上"
      , "18台上"
      , "17台上"
      , "17台下"
      , "2台上"
      , "19台上"
      , "分区8"
      , "三楼D单"
      , "二楼C双"
      , "三楼D双"
      , "经典5区"
      , "十二号台下"
      , "十二号台上"
      , "十一号台下"
      , "经典2区"
      , "贵宾二区"
      , "VIP4区"
      , "经典6区"
      , "十四号台上"
      , "三号台下"
      , "三号台上"
      , "二号台下"
      , "二号台上"
      , "一号台上"
      , "经典1区"
      , "十一号台上"
      , "十三号台下"
      , "十三号台上"
      , "十四号台下"
      , "VIP3区"
      , "九号台上"
      , "六号台下"
      , "六号台上"
      , "七号台下"
      , "七号台上"
      , "八号台上"
      , "经典8区"
      , "一号台下"
      , "十号台下"
      , "十号台上"
      , "九号台下"
      , "主席台A区"
      , "嘉宾区"
      , "五号台上"
      , "经典7区"
      , "八号台下"
      , "主席台C区"
      , "四号台上"
      , "主席台B区"
      , "经典3区"
      , "经典4区"
      , "区域5"
      , "区域4"
      , "区域3"
      , "区域2"
      , "区域1"
      , "23"
      , "24"
      , "27"
      , "25"
      , "26"
      , "分区测试2"
      , "分区测试"
      , "分区3"
      , "分区4"
      , "分区2"
      , "分区5"
      , "adwdsf"
      , "202"
      , "201"
      , "306"
      , "305"
      , "307"
      , "304"
      , "408"
      , "302"
      , "402"
      , "205"
      , "203"
      , "406"
      , "409"
      , "401"
      , "403"
      , "407"
      , "207"
      , "309"
      , "内场"
      , "308"
      , "204"
      , "404"
      , "303"
      , "206"
      , "301"
      , "东三2区"
      , "东三3区"
      , "东三1区"
      , "333333333333333333333333333333"
      , "113"
      , "114"
      , "102"
      , "103"
      , "N区"
      , "M区"
      , "H区"
      , "F区"
      , "E区"
      , "B区单号"
      , "B区双号"
      , "C区单号"
      , "C区双号"
      , "D区单号"
      , "D区双号"
      , "A区单号"
      , "A区双号"
      , "测试分区1"
      , "咨询的是非得失"
      , "测试分区名称");


  private final static Comparator<Object> ENGLISH_COMPARE = Collator.getInstance(java.util.Locale.ENGLISH);


  @Test
  public void pinyin() throws PinyinException {
    String str = "北京";
    String result = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
    System.out.println(result);
  }

  @Test
  public void pinyin1() {
    Map<String, String> data = DATA.stream().distinct().collect(Collectors.toMap(c -> c, s -> s.replace("一", "1")
        .replace("二", "2")
        .replace("三", "3")
        .replace("四", "4")
        .replace("五", "5")
        .replace("六", "6")
        .replace("七", "7")
        .replace("八", "8")
        .replace("九", "9")));

    List<String> abdList = Lists.newArrayList();
    List<String> numList = Lists.newArrayList();
    List<String> chineseList = Lists.newArrayList();

    Map<String, String> chineseMap = Maps.newTreeMap();

    Collection<String> list = data.values();

    list.forEach(s -> {
      char[] value = s.toCharArray();
      char c = value[0];
      String a = String.valueOf(c);

      if (StringUtils.isNumberic(a)) {
        numList.add(s);
      } else if (StringUtils.isAbc(a)) {
        abdList.add(s);
      } else {
        chineseMap.put(py(s), s);
      }
    });

    abdList.sort(ENGLISH_COMPARE);
    numList.sort((o1, o2) -> {
      char[] value = o1.toCharArray();
      char c = value[0];

      char[] value2 = o2.toCharArray();
      char c2 = value2[0];

      Integer a = (int) c;
      Integer b = (int) c2;
      return a.compareTo(b);
    });
    chineseMap.forEach((k, v) -> chineseList.add(v));

    System.out.println();
  }


  private String py(String str) {
    try {
      return PinyinHelper.getShortPinyin(str);
    } catch (PinyinException e) {
      return "";
    }
  }

}

