package com.betterlxc.elasticsearch;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class InsertTest {

  private static TransportClient client;

  static {
    Settings settings = Settings.builder().put("cluster.name", "yoruo").build();
    client = new PreBuiltTransportClient(settings);
    try {
      client.addTransportAddress(new TransportAddress(Inet4Address.getByName("127.0.0.1"), 9300));
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test1() {
    SearchResponse response = client.prepareSearch("yoruo")
        .setTypes("test")
        .setPostFilter(QueryBuilders.matchQuery("total_elements", 0))
        .setFrom(0).setSize(60).setExplain(true)
        .get();

    System.out.println(response);
  }

  @Test
  public void test2() throws IOException {
    IndexResponse response = client.prepareIndex("yoruo", "test", "111")
        .setSource(jsonBuilder()
            .startObject()
            .field("user", "kimchy")
            .field("postDate", new Date())
            .field("message", "trying out Elasticsearch")
            .endObject()
        )
        .execute()
        .actionGet();
    System.out.println(response);
  }

  @Test
  public void test3() throws IOException {
    String str = Resources.toString(new URL(ClassLoader.getSystemResource("2.json").getPath()), Charsets.UTF_8);
    IndexResponse response = client.prepareIndex("yoruo", "test", "113")
        .setSource(str, XContentType.JSON)
        .execute()
        .actionGet();
    System.out.println(response);
  }
}
