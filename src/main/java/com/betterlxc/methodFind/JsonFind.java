package com.betterlxc.methodFind;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author liuxincheng
 * @date 2020/7/9
 */
public class JsonFind {

    private static final String TYPE = "PigeonCall";

    private static final String PATH = "/Users/lxc/Downloads/json";

    private static final String APP_KEY = "PigeonCall.app";

    private static final List<String> API = Lists.newArrayList("CheckIntervalManageApi", "TicketMgtApi", "FetchTicketWayMgtApi");

    private static final Jedis JEDIS;

    static {
        JEDIS = new Jedis("localhost");
    }

    @Test
    public void test() throws IOException {
        File file = new File(PATH);
        File[] files = file.listFiles();

        List<Map<String, String>> result = Lists.newArrayList();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            File f = files[i];
            String fileName = f.getName();
            if (!fileName.endsWith(".json")) {
                continue;
            }
            String json = Resources.toString(new URL("file://" + PATH + "/" + f.getName()), StandardCharsets.UTF_8);
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("message").getJSONArray("children");

            List<Map<String, String>> collect = jsonArray.stream()
                .map(obj -> JSON.parseObject(obj.toString()))
                .filter(a -> a.getString("type").equals(TYPE) && !a.getString("name").contains("saveLog"))
                .map(a -> {
                    Map<String, String> map = Maps.newHashMap();
                    String name = a.getString("name");
                    int z = name.lastIndexOf(".");
                    String pigeonCall = name.substring(z + 1);
                    map.put("pigeonCall", pigeonCall);

                    String appKey = a.getJSONArray("children").stream()
                        .map(obj -> JSON.parseObject(obj.toString()))
                        .filter(b -> b.getString("type").equals(APP_KEY))
                        .map(c -> c.getString("name")).findFirst().orElse("");
                    map.put("appKey", appKey);

                    int j = pigeonCall.indexOf(":");
                    String api = pigeonCall.substring(0, j);
                    map.put("api", api);
                    return map;
                }).collect(Collectors.toList());

            result.addAll(collect);
        }

        List<Map<String, String>> pigeonCall = result.stream()
            .filter(m -> "com.sankuai.mytarservice".equals(m.get("appKey")) && API.contains(m.get("api")))
            .collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(s -> s.get("pigeonCall")))), ArrayList::new));

        Map<String, List<Map<String, String>>> api = pigeonCall.stream().collect(Collectors.groupingBy(m -> m.get("api")));

        // appkey -> api -> model
        JEDIS.set("tar", JSON.toJSONString(api));
    }


}
