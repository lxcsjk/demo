package com.betterlxc.list;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author LXC
 * @date 2017/4/24
 */
@Slf4j
public class ListsTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Splitter SPLITTER = Splitter.on(':');

    @Test
    public void test1() {
        char[] a = {'a', 'b', 'c'};
        char[] b = a;
        b[1] = 'x';
        System.out.println(a);
        System.out.println(b);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode resp = objectMapper
            .readTree(Files.toString(new File("/Users/lxc/Desktop/11111"), StandardCharsets.UTF_8));

        ArrayNode itemsNode = (ArrayNode) resp.findValue("item");

        Map<Long, List<SkuInfo>> map = Maps.newHashMap();

        for (JsonNode node : itemsNode) {
            JsonNode imgNode = node.findValue("prop_img");
            JsonNode skuNode = node.findValue("sku");

            List<PropImg> propImgs = OBJECT_MAPPER.convertValue(imgNode, PropImg.TYPE);
            Map<String, String> imgMap = propImgs
                .stream().collect(Collectors.toMap(PropImg::getProperties, PropImg::getUrl));

            List<Sku> skuList = OBJECT_MAPPER.convertValue(skuNode, Sku.TYPE);
            List<SkuInfo> skuInfoList = Lists.newArrayList();

            skuList.forEach(sku -> {
                skuInfoList.add(new SkuInfo(sku, imgMap));
                map.put(node.findValue("num_iid").asLong(), skuInfoList);
            });
        }
        System.out.println(map);
    }

    @Test
    public void test3() {
        Set<Integer> set1 = Sets.newHashSet(0, 1, 2, 3, 4, 5, 6, 7);
        Set<Integer> set2 = Sets.newHashSet(8, 1, 2, 3, 4, 5, 6, 7);

        Sets.SetView setView1 = Sets.difference(set1, set2);
        Sets.SetView setView2 = Sets.difference(set2, set1);

//    System.out.println(setView1.stream().collect(Collectors.toList()));
//    System.out.println(setView2.stream().collect(Collectors.toList()));

    }

    @Test
    public void test4() {
        List<Integer> s = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> ss = Lists.newArrayList(1, 2, 3, 8);

        List<Integer> collect = ss.stream().filter(i -> i > 2).sorted(Comparator.comparingInt(p -> p)).collect(Collectors.toList());


        ss.removeAll(s);

//    System.out.println(s);
        System.out.println(ss);

    }

    @Test
    public void test5() {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Object list = intStream.collect(ArrayList::new, ArrayList::add, ArrayList::remove)
            .parallelStream().findFirst().orElse(null);

        System.out.println(list);

    }

    @Test
    public void test6() {
        List<String> list = Lists.newArrayList("1496517402155926", "1496517402155927", "1000")
            .stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(list);
    }
}
