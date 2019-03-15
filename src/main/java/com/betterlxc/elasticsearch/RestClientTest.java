package com.betterlxc.elasticsearch;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.*;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class RestClientTest {

  private static final File JSON_PATH =
      new File(ClassLoader.getSystemResource("accounts.json").getPath());

  private static RestHighLevelClient CLIENT;

  static {
    HttpHost[] httpHosts = new HttpHost[]{new HttpHost("111.231.75.84", 9200)};
    RestClientBuilder restClientBuilder = RestClient.builder(httpHosts);
    CLIENT = new RestHighLevelClient(restClientBuilder);
  }

  @Test
  public void save() throws IOException {
    BulkRequest bulkRequest = read();
    BulkResponse response = CLIENT.bulk(bulkRequest);

    System.out.println(response);
  }

  private BulkRequest read() throws IOException {
    BulkRequest bulkRequest = new BulkRequest();

    InputStreamReader reader = new InputStreamReader(new FileInputStream(JSON_PATH));
    BufferedReader br = new BufferedReader(reader);

    String line;
    line = br.readLine();
    while (line != null) {
      Any any = JsonIterator.deserialize(line);
      IndexRequest indexRequest = new IndexRequest("test", "test", any.get("index").get("_id").toString());
      line = br.readLine();
      if (line != null) {
        indexRequest.source(line, XContentType.JSON);
        bulkRequest.add(indexRequest);
      }
      line = br.readLine();
    }
    return bulkRequest;
  }

  @Test
  public void find(){

  }

}
