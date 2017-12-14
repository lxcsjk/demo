package com.betterlxc.elasticsearch;

import com.jsoniter.output.JsonStream;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.Inet4Address;
import java.net.UnknownHostException;

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
        .setPostFilter(QueryBuilders.matchAllQuery())
        .setFrom(0).setSize(60).setExplain(true)
        .get();

    System.out.println(response);
  }
}
