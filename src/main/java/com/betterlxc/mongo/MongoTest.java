package com.betterlxc.mongo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jsoniter.output.JsonStream;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Slf4j
public class MongoTest {

  private static final MongoClientURI MONGO_CLIENT_URI =
      new MongoClientURI("mongodb://ysfro:1nJW49jLYi0QxQwNak1T@mbxc-mongo.mcltd.cn/ysf");

  private static final MongoClient MONGO_CLIENT = new MongoClient(MONGO_CLIENT_URI);

  private static final MongoDatabase MONGO_DATABASE = MONGO_CLIENT.getDatabase("ysf");

  @Test
  public void test1() {
    Iterable<Document> documents = MONGO_DATABASE.getCollection("sku")
        .find(
            Filters.and(
                Filters.eq("seller_id", 394695430),
                Filters.eq("sku_id", 3652317451677L),
                Filters.lte("dt", 20171030),
                Filters.gte("dt", 20171023)
            ))
        .projection(fields(include("dt", "sku_id", "alipay_winner_num",
            "alipay_auction_num", "add_cart_user_num", "alipay_trade_amt",
            "alipay_trade_num", "succ_refund_trade_amt"), excludeId()))
        .sort(orderBy(descending("dt")));

    log.info("documents    --------> \n {}", Lists.newArrayList(documents));
  }

  @Test
  public void test2() {
    AggregateIterable<Document> cursor = MONGO_DATABASE.getCollection("sku")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20171001),
                Filters.lte("dt", 20171031),
                Filters.eq("seller_id", 394695430),
                Filters.eq("sku_id", 3652317451677L)
            )),
            Aggregates.group(new BasicDBObject("sku_id", "$sku_id"),
                new BsonField("alipay_winner_num", new BasicDBObject("$sum", "$alipay_winner_num")),
                new BsonField("alipay_auction_num", new BasicDBObject("$sum", "$alipay_auction_num")),
                new BsonField("add_cart_user_num", new BasicDBObject("$sum", "$add_cart_user_num")),
                new BsonField("alipay_trade_amt", new BasicDBObject("$sum", "$alipay_trade_amt")),
                new BsonField("alipay_trade_num", new BasicDBObject("$sum", "$alipay_trade_num")),
                new BsonField("succ_refund_trade_amt", new BasicDBObject("$sum", "$succ_refund_trade_amt")))
        ));

    Map<Long, Object> map = StreamSupport
        .stream(cursor.spliterator(), false)
        .collect(Collectors.toMap(
            doc -> doc.get("_id", Document.class).getLong("sku_id"),
            doc -> doc)
        );
    log.info("map    --------> \n {}", map);
  }

  @Test
  public void test3() {
    List<Map<String, Object>> list = Lists.newArrayList();

    IntStream.range(1, 60).forEach(i -> {
      Integer dt = calcDt(i);

      long app = MONGO_DATABASE.getCollection("app_keyword_new")
          .count(Filters.and(
              Filters.eq("dt", dt),
              Filters.eq("seller_id", 2382603663L)
          ));

      long pc = MONGO_DATABASE.getCollection("pc_keyword")
          .count(Filters.and(
              Filters.eq("dt", dt),
              Filters.eq("seller_id", 2382603663L)
          ));

      Map<String, Object> map = Maps.newHashMap();
      map.put("dt", dt);
      map.put("app", app);
      map.put("pc", pc);
      list.add(map);
    });
    System.out.println(JsonStream.serialize(list));
  }

  @Test
  public void test4() {
    List<Map<String, Object>> list = Lists.newArrayList();

    IntStream.range(1, 100).forEach(i -> {
      Integer dt = calcDt(i);

      Iterable<Document> documents = MONGO_DATABASE.getCollection("shop")
          .find(
              Filters.and(
                  Filters.eq("dt", dt),
                  Filters.eq("seller_id", 2146742267L)
              ))
          .projection(fields(include("dt", "app_total_keywords_new", "pc_total_keywords"), excludeId()))
          .sort(orderBy(descending("dt"))).limit(5000);

      List<Map> temp = Lists.newArrayList(documents);

      if (temp.size() > 0) {
        Map m = temp.get(0);

        Map<String, Object> map = Maps.newHashMap();
        map.put("dt", dt);
        map.put("app", m.get("app_total_keywords_new") == null ? 0 : m.get("app_total_keywords_new"));
        map.put("pc", m.get("pc_total_keywords") == null ? 0 : m.get("pc_total_keywords"));
        list.add(map);
      }
    });

    System.out.println(JsonStream.serialize(list));
  }

  @Test
  public void test5() {
    AggregateIterable<Document> cursor = MONGO_DATABASE.getCollection("shop_h")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20171108),
                Filters.lte("dt", 20171109),
                Filters.eq("seller_id", 2146742267)
            )),
            Aggregates.group(new BasicDBObject("thehour", "$thehour"))
        ));


    Map<Long, Object> map = StreamSupport
        .stream(cursor.spliterator(), false)
        .collect(Collectors.toMap(
            doc -> {
              Long hour = doc.get("_id", Document.class).getLong("thehour");
              if (hour == null) {
                hour = 0L;
              }
              return hour;
            },
            doc -> doc)
        );
    log.info("map    --------> \n {}", map);
  }


  private static int calcDt(int t) {
    return Integer.parseInt(LocalDate.now()
        .minusDays(t)
        .format(DateTimeFormatter.BASIC_ISO_DATE));
  }

  @Test
  public void test6() {
    Map<String, String> map = Maps.newHashMap();
    map.put("query", "$query");
    map.put("dt", "$dt");

    AggregateIterable<Document> cursor = MONGO_DATABASE.getCollection("app_keyword_new")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.eq("dt", 20171121),
                Filters.eq("seller_id", 394695430))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("landing_uv", new BasicDBObject("$sum", "$landing_uv")),
                new BsonField("landing_pv", new BasicDBObject("$sum", "$landing_pv")),
                new BsonField("direct_alipay_auction_num", new BasicDBObject("$sum", "$direct_alipay_auction_num")),
                new BsonField("direct_alipay_winner_num", new BasicDBObject("$sum", "$direct_alipay_winner_num")),
                new BsonField("direct_alipay_trade_amt", new BasicDBObject("$sum", "$direct_alipay_trade_amt"))),
            Aggregates.sort(new BasicDBObject("direct_alipay_auction_num", -1)),
            Aggregates.limit(5000)
        ));

    List<KeyWordData> keyWordData = StreamSupport
        .stream(cursor.spliterator(), false)
        .map(document -> {
          KeyWordData temp = new KeyWordData();
          temp.setDt(document.get("_id", Document.class).getInteger("dt"));
          temp.setKeyWord(document.get("_id", Document.class).getString("query"));
          temp.setLandingUv(document.getLong("landing_uv"));
          temp.setLandingPv(document.getLong("landing_pv"));
          temp.setAuctionNum(Long.valueOf(document.get("direct_alipay_auction_num").toString()));
          temp.setWinnerNum(Long.valueOf(document.get("direct_alipay_winner_num").toString()));
          temp.setTradeAmt(Double.valueOf(document.get("direct_alipay_trade_amt").toString()));
          return temp;
        }).collect(Collectors.toList());

    System.out.println(keyWordData);
  }

  @Test
  public void test7() {
    Iterable<Document> documents = MONGO_DATABASE.getCollection("item_source")
        .find(Filters.and(
            Filters.eq("seller_id", 394695430),
            Filters.eq("item_id", 557867640775L),
//            Filters.lte("dt", 20171204),
            Filters.eq("dt", 20171210),
            Filters.gte("dws_item_app_src_platform_new_d.ipv", 0)
//            Filters.gte("dwb_item_source_last_effect_new_d.ipv", 0)
            )
        );
    List<Map> temp = Lists.newArrayList(documents);

    log.info("documents    --------> \n {}", 1);
  }
}
