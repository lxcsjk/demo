package com.betterlxc.string;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.jsoniter.JsonIterator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class JoinerTest {

  private static final Joiner JOINER = Joiner.on(",").skipNulls();
  String str = "[\n" +
      "    {\n" +
      "        \"key\": \"cn-sh\", \n" +
      "        \"name\": \"上海\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-zj\", \n" +
      "        \"name\": \"浙江\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-3664\", \n" +
      "        \"name\": \"西沙群岛\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-3681\", \n" +
      "        \"name\": \"澳门\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"tw-tw\", \n" +
      "        \"name\": \"台湾\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-gs\", \n" +
      "        \"name\": \"甘肃\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-6657\", \n" +
      "        \"name\": \"香港\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-nx\", \n" +
      "        \"name\": \"宁夏\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-sa\", \n" +
      "        \"name\": \"陕西\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-ah\", \n" +
      "        \"name\": \"安徽\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-hu\", \n" +
      "        \"name\": \"湖北\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-gd\", \n" +
      "        \"name\": \"广东\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-fj\", \n" +
      "        \"name\": \"福建\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-bj\", \n" +
      "        \"name\": \"北京\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-hb\", \n" +
      "        \"name\": \"河北\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-sd\", \n" +
      "        \"name\": \"山东\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-tj\", \n" +
      "        \"name\": \"天津\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-js\", \n" +
      "        \"name\": \"江苏\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-ha\", \n" +
      "        \"name\": \"海南\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-qh\", \n" +
      "        \"name\": \"青海\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-jl\", \n" +
      "        \"name\": \"吉林\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-xz\", \n" +
      "        \"name\": \"西藏\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-xj\", \n" +
      "        \"name\": \"新疆\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-he\", \n" +
      "        \"name\": \"河南\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-nm\", \n" +
      "        \"name\": \"内蒙古\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-hl\", \n" +
      "        \"name\": \"黑龙江\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-yn\", \n" +
      "        \"name\": \"云南\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-gx\", \n" +
      "        \"name\": \"广西\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-ln\", \n" +
      "        \"name\": \"辽宁\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-sc\", \n" +
      "        \"name\": \"四川\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-cq\", \n" +
      "        \"name\": \"重庆\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-gz\", \n" +
      "        \"name\": \"贵州\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-hn\", \n" +
      "        \"name\": \"湖南\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-sx\", \n" +
      "        \"name\": \"山西\"\n" +
      "    }, \n" +
      "    {\n" +
      "        \"key\": \"cn-jx\", \n" +
      "        \"name\": \"江西\"\n" +
      "    }\n" +
      "]";

  @Test
  public void joinTest() {
    Set<String> list = Sets.newHashSet("A", "B", "C", null);
    log.info("list  ----- > {}", list);
    String joinerStr = JOINER.join(list);
    log.info("分割链接后的字符串  ----- > {}", joinerStr);
  }

  @Test
  public void test() {
    List<Map<String, String>> list = JsonIterator.deserialize(str, List.class);

    Map<String, String> map = list.stream().collect(Collectors.toMap(
        model -> ((Map) model).get("name").toString(),
        model -> ((Map) model).get("key").toString()));

    System.out.println(map);
  }
}
