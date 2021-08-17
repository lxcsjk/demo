package com.betterlxc.string;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.jsoniter.JsonIterator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    String str = "[{\n" +
        "\t\t\"key\": \"beijing\",\n" +
        "\t\t\"name\": \"北京\",\n" +
        "\t\t\"cities\": [{\n" +
        "\t\t\t\t\"key\": \"dongchengqu\",\n" +
        "\t\t\t\t\"name\": \"东城区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"xichengqu\",\n" +
        "\t\t\t\t\"name\": \"西城区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"chaoyangqu\",\n" +
        "\t\t\t\t\"name\": \"朝阳区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"fengtaiqu\",\n" +
        "\t\t\t\t\"name\": \"丰台区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"shijingshanqu\",\n" +
        "\t\t\t\t\"name\": \"石景山区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"haidianqu\",\n" +
        "\t\t\t\t\"name\": \"海淀区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"mentougouqu\",\n" +
        "\t\t\t\t\"name\": \"门头沟区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"fangshanqu\",\n" +
        "\t\t\t\t\"name\": \"房山区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"tongzhouqu\",\n" +
        "\t\t\t\t\"name\": \"通州区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"shunyiqu\",\n" +
        "\t\t\t\t\"name\": \"顺义区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"changpingqu\",\n" +
        "\t\t\t\t\"name\": \"昌平区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"daxingqu\",\n" +
        "\t\t\t\t\"name\": \"大兴区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"huairouqu\",\n" +
        "\t\t\t\t\"name\": \"怀柔区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"pingguqu\",\n" +
        "\t\t\t\t\"name\": \"平谷区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"miyunqu\",\n" +
        "\t\t\t\t\"name\": \"密云区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"yanqingqu\",\n" +
        "\t\t\t\t\"name\": \"延庆区\"\n" +
        "\t\t\t}\n" +
        "\t\t]\n" +
        "\t},\n" +
        "\t{\n" +
        "\t\t\"key\": \"tianjin\",\n" +
        "\t\t\"name\": \"天津\",\n" +
        "\t\t\"cities\": [{\n" +
        "\t\t\t\t\"key\": \"hepingqu\",\n" +
        "\t\t\t\t\"name\": \"和平区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"hedongqu\",\n" +
        "\t\t\t\t\"name\": \"河东区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"hexiqu\",\n" +
        "\t\t\t\t\"name\": \"河西区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"nankaiqu\",\n" +
        "\t\t\t\t\"name\": \"南开区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"hebeiqu\",\n" +
        "\t\t\t\t\"name\": \"河北区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"hongqiaoqu\",\n" +
        "\t\t\t\t\"name\": \"红桥区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"dongliqu\",\n" +
        "\t\t\t\t\"name\": \"东丽区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"xiqingqu\",\n" +
        "\t\t\t\t\"name\": \"西青区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"jinnanqu\",\n" +
        "\t\t\t\t\"name\": \"津南区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"beichenqu\",\n" +
        "\t\t\t\t\"name\": \"北辰区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"wuqingqu\",\n" +
        "\t\t\t\t\"name\": \"武清区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"baodiqu\",\n" +
        "\t\t\t\t\"name\": \"宝坻区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"binhaixinqu\",\n" +
        "\t\t\t\t\"name\": \"滨海新区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"ninghequ\",\n" +
        "\t\t\t\t\"name\": \"宁河区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"jinghaiqu\",\n" +
        "\t\t\t\t\"name\": \"静海区\"\n" +
        "\t\t\t},\n" +
        "\t\t\t{\n" +
        "\t\t\t\t\"key\": \"jizhouqu\",\n" +
        "\t\t\t\t\"name\": \"蓟州区\"\n" +
        "\t\t\t}\n" +
        "\t\t]\n" +
        "\t}\n" +
        "]";

    @Test
    public void joinTest() {
        Set<String> list = Sets.newHashSet("A", "B", "C", null);
        log.info("list  ----- > {}", list);
        String joinerStr = JOINER.join(list);
        log.info("分割链接后的字符串  ----- > {}", joinerStr);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        List<Map<String, String>> list = JsonIterator.deserialize(str, List.class);

        Map<String, String> map = list.stream().collect(Collectors.toMap(
            model -> ((Map) model).get("name").toString(),
            model -> ((Map) model).get("key").toString()));

        System.out.println(map);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test6() throws IOException {
        String str = Resources.toString(new URL("file://" +
            ClassLoader.getSystemResource("format.json").getPath()), Charsets.UTF_8);

        List<Map<String, Object>> place = JsonIterator.deserialize(str, List.class);
        System.out.println(place);
    }

    @Test
    public void test7() {
        String str = "" + null;
    }
}