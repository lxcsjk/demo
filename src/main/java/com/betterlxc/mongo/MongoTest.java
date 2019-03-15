package com.betterlxc.mongo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
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
import java.util.Collection;
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
      new MongoClientURI("mongodb://10.1.32.182:27017");

  private static final MongoClient MONGO_CLIENT = new MongoClient(MONGO_CLIENT_URI);

  private static final MongoDatabase MONGO_DATABASE = MONGO_CLIENT.getDatabase("shop_msg_liaomengge");

  private static int calcDt(int t) {

    return Integer.parseInt(LocalDate.now()
        .minusDays(t)
        .format(DateTimeFormatter.BASIC_ISO_DATE));
  }

  @Test
  public void test1() {
//    DeleteResult deleteResult = MONGO_DATABASE.getCollection("group_base_msg_table")
//        .deleteMany(
//            Filters.and(
//                Filters.gt("sendTime", "2018-10-25 13:54:27"),
//                Filters.lt("sendTime", "2018-10-26 17:34:27"), Filters.eq("msgGroupType", 3)
//            ));

    long count0 = MONGO_DATABASE.getCollection("p2p_msg_table")
        .count(
            Filters.and(Filters.eq("msgGroupType", 1),
                Filters.gt("createdTime", "2018-11-14 00:00:00"),
                Filters.lt("createdTime", "2018-11-15 00:00:00")
            ));
    long count1 = MONGO_DATABASE.getCollection("p2p_msg_table")
        .count(
            Filters.and(Filters.eq("msgGroupType", 1),
                Filters.gte("createdTime", "2018-11-15 00:00:00")
            ));
    System.out.println();
  }

  @Test
  public void testt() {
    CsvReader reader = CsvUtil.getReader();
    CsvData data = reader.read(FileUtil.file("/Users/lxc/Downloads/mobile.csv"));
    List<CsvRow> rows = data.getRows();



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

    for (int i = 0; i < 3; i++) {
      Iterable<Document> documents = MONGO_DATABASE.getCollection("shop")
          .find(Filters.and(
              Filters.gte("dt", 20171023)
          ))
          .projection(fields(include("dt", "seller_id", "app_total_keywords_new", "pc_total_keywords"), excludeId()))
          .sort(orderBy(descending("dt"))).skip(10 * i).limit(10);

      List<Map> temp = Lists.newArrayList(documents);

      temp.forEach(m -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("dt", m.get("dt"));
        map.put("seller_id", m.get("seller_id"));
        map.put("app", m.get("app_total_keywords_new") == null ? 0 : m.get("app_total_keywords_new"));
        map.put("pc", m.get("pc_total_keywords") == null ? 0 : m.get("pc_total_keywords"));
        list.add(map);
      });
    }

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
            Filters.lte("dt", 20171204),
            Filters.eq("dt", 20171210),
            Filters.gte("dws_item_app_src_platform_new_d.ipv", 0)
//            Filters.gte("dwb_item_source_last_effect_new_d.ipv", 0)
            )
        );
    List<Map> temp = Lists.newArrayList(documents);

    log.info("documents    --------> \n {}", 1);
  }

  @Test
  public void test8() {
    Map<String, String> map = Maps.newHashMap();
    map.put("dt", "$dt");
    map.put("src_id", "$src_id");

    AggregateIterable<Document> cursor = MONGO_DATABASE.getCollection("item_source")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20171212),
                Filters.lte("dt", 20171218),
                Filters.eq("seller_id", 394695430))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("pc_uv", new BasicDBObject("$sum", "$dwb_item_source_last_effect_new_d.iuv")),
                new BsonField("pc_pv", new BasicDBObject("$sum", "$dwb_item_source_last_effect_new_d.ipv")),
                new BsonField("app_uv", new BasicDBObject("$sum", "$dws_item_app_src_platform_new_d.iuv")),
                new BsonField("app_pv", new BasicDBObject("$sum", "$dws_item_app_src_platform_new_d.ipv"))),
            Aggregates.limit(1000)));

    List<ShopSource> shopSources = StreamSupport
        .stream(cursor.spliterator(), false)
        .map(document -> {
          ShopSource shopSource = new ShopSource();
          shopSource.setDt(Long.valueOf(document.get("_id", Document.class).get("dt").toString()));
          shopSource.setSrcId(document.get("_id", Document.class).getString("src_id"));

          if (document.get("pc_uv") != null &&
              Long.valueOf(document.get("pc_uv").toString()) > 0) {
            shopSource.setPv(Long.valueOf(document.get("pc_pv").toString()));
            shopSource.setUv(Long.valueOf(document.get("pc_uv").toString()));
          } else {
            shopSource.setPv(Long.valueOf(document.get("app_pv").toString()));
            shopSource.setUv(Long.valueOf(document.get("app_uv").toString()));
          }
          return shopSource;
        }).collect(Collectors.toList());

    System.out.println(shopSources);
  }

  @Test
  public void test9() {
    Iterable<Document> documents = MONGO_DATABASE.getCollection("item_weblog")
        .find(
            Filters.and(
                Filters.lte("dt", 20180408),
                Filters.gte("dt", 20180407),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 565529661031L),
                Filters.exists("stat_auction_region_app_weblog_d")
            ))
        .projection(fields(include("dt", "seller_id", "auction_id", "pid", "cid",
            "stat_auction_region_app_weblog_d"), excludeId()));

    List<Document> list = Lists.newArrayList(documents);

    Double appData = list.stream().mapToDouble(document -> {
      Double a = 0.0D;

      Map app = (Map) document.get("stat_auction_order_plus_d");
      if (app != null && app.get("alipay_trade_amt") != null) {
        a = Double.valueOf(app.get("alipay_trade_amt").toString());
      }
      return a;
    }).sum();
    System.out.println(appData);
  }

  @Test
  public void test10() {
    Map<String, String> map = Maps.newHashMap();
    map.put("dt", "$dt");
    map.put("seller_id", "$seller_id");
    map.put("auction_id", "$auction_id");
    map.put("pid", "$pid");
    map.put("cid", "$cid");

    AggregateIterable<Document> appData = MONGO_DATABASE.getCollection("item_weblog")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20180408),
                Filters.lte("dt", 20180408),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 564335965653L),
                Filters.eq("visit_platform", 1))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("app_pv", new BasicDBObject("$sum", "$stat_auction_region_app_weblog_d.pv")),
                new BsonField("app_uv", new BasicDBObject("$sum", "$stat_auction_region_app_weblog_d.uv")),
                new BsonField("app_gmv_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_trade_amt")),
                new BsonField("app_alipay_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.alipay_trade_amt")),
                new BsonField("app_gmv_auction_num", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_auction_num")))));

    log.info("app成交金额数据 ： {} ", StreamSupport.stream(appData.spliterator(), false)
        .mapToDouble(r -> Double.valueOf(r.get("app_alipay_trade_amt").toString())).sum());

    Map<String, ItemRegion> data = Maps.newHashMap();

    StreamSupport.stream(appData.spliterator(), false)
        .forEach(document -> handler(data, document, 1));

    AggregateIterable<Document> pcData = MONGO_DATABASE.getCollection("item_weblog")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20180408),
                Filters.lte("dt", 20180408),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 564335965653L),
                Filters.eq("visit_platform", 2))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("pc_pv", new BasicDBObject("$sum", "$stat_auction_region_pc_weblog_d.pv")),
                new BsonField("pc_uv", new BasicDBObject("$sum", "$stat_auction_region_pc_weblog_d.uv")),
                new BsonField("pc_gmv_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_trade_amt")),
                new BsonField("pc_alipay_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.alipay_trade_amt")),
                new BsonField("pc_gmv_auction_num", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_auction_num")))));

    StreamSupport.stream(pcData.spliterator(), false)
        .forEach(document -> handler(data, document, 2));

    log.info("pc成交金额数据 ： {} ", StreamSupport.stream(pcData.spliterator(), false)
        .mapToDouble(r -> Double.valueOf(r.get("pc_alipay_trade_amt").toString())).sum());


    AggregateIterable<Document> otherData = MONGO_DATABASE.getCollection("item_weblog")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20180408),
                Filters.lte("dt", 20180408),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 564335965653L),
                Filters.eq("visit_platform", null))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("other_gmv_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_trade_amt")),
                new BsonField("other_alipay_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.alipay_trade_amt")),
                new BsonField("other_gmv_auction_num", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_auction_num")))));

    log.info("other成交金额数据 ： {} ", StreamSupport.stream(otherData.spliterator(), false)
        .mapToDouble(r -> Double.valueOf(r.get("other_alipay_trade_amt").toString())).sum());


    StreamSupport.stream(otherData.spliterator(), false)
        .forEach(document -> handler(data, document, 0));

    Collection<ItemRegion> regions = data.values();

    log.info("成交金额数据 ： {} ", regions.stream().mapToDouble(r -> r.getAppAlipayTradeAmt() +
        r.getPcAlipayTradeAmt() + r.getOtherAlipayTradeAmt()).sum());
  }


  private ItemRegion handler(Map<String, ItemRegion> map, Document document, Integer platform) {
    Document d = document.get("_id", Document.class);

    Long dt = Long.valueOf(d.get("dt").toString());
    Long sellerId = Long.valueOf(d.get("seller_id").toString());

    Long pid;
    if (d.get("pid") == null) {
      pid = 0L;
    } else {
      pid = Long.valueOf(d.get("pid").toString());
    }

    Long cid;
    if (d.get("cid") == null) {
      cid = 0L;
    } else {
      cid = Long.valueOf(d.get("cid").toString());
    }

    String key = dt + "" + sellerId + cid + platform;
    ItemRegion itemRegion = map.getOrDefault(key, new ItemRegion());

    itemRegion.setDt(dt);
    itemRegion.setSellerId(sellerId);


    if (document.get("pc_pv") != null) {
      itemRegion.setPcPv(Long.valueOf(document.get("pc_pv").toString()));
    }
    if (document.get("app_pv") != null) {
      itemRegion.setAppPv(Long.valueOf(document.get("app_pv").toString()));
    }

    if (document.get("app_gmv_trade_amt") != null) {
      itemRegion.setAppGmvTradeAmt(Double.valueOf(document.get("app_gmv_trade_amt").toString()));
    }
    if (document.get("app_alipay_trade_amt") != null) {
      itemRegion.setAppAlipayTradeAmt(Double.valueOf(document.get("app_alipay_trade_amt").toString()));
    }
    if (document.get("app_gmv_auction_num") != null) {
      itemRegion.setAppGmvAuctionNum(Long.valueOf(document.get("app_gmv_auction_num").toString()));
    }

    if (document.get("pc_gmv_trade_amt") != null) {
      itemRegion.setPcGmvTradeAmt(Double.valueOf(document.get("pc_gmv_trade_amt").toString()));
    }
    if (document.get("pc_alipay_trade_amt") != null) {
      itemRegion.setPcAlipayTradeAmt(Double.valueOf(document.get("pc_alipay_trade_amt").toString()));
    }
    if (document.get("pc_gmv_auction_num") != null) {
      itemRegion.setPcGmvAuctionNum(Long.valueOf(document.get("pc_gmv_auction_num").toString()));
    }

    if (document.get("other_gmv_trade_amt") != null) {
      itemRegion.setOtherGmvTradeAmt(Double.valueOf(document.get("other_gmv_trade_amt").toString()));
    }
    if (document.get("other_alipay_trade_amt") != null) {
      itemRegion.setOtherAlipayTradeAmt(Double.valueOf(document.get("other_alipay_trade_amt").toString()));
    }
    if (document.get("other_gmv_auction_num") != null) {
      itemRegion.setOtherGmvAuctionNum(Long.valueOf(document.get("other_gmv_auction_num").toString()));
    }

    map.put(key, itemRegion);
    return itemRegion;
  }

  private ItemTime handler0(Map<String, ItemTime> map, Document document, Integer platform) {
    Document d = document.get("_id", Document.class);

    Long dt = Long.valueOf(d.get("dt").toString());
    Long sellerId = Long.valueOf(d.get("seller_id").toString());

    Long hour;
    if (d.get("hour") == null) {
      hour = 0L;
    } else {
      hour = Long.valueOf(d.get("hour").toString());
    }

    String key = "" + hour + platform;
    ItemTime itemTime = map.getOrDefault(key, new ItemTime());

    itemTime.setDt(dt);
    itemTime.setSellerId(sellerId);
    itemTime.setHour(hour);

    if (document.get("pc_pv") != null) {
      itemTime.setPcPv(Long.valueOf(document.get("pc_pv").toString()));
    }
    if (document.get("app_pv") != null) {
      itemTime.setAppPv(Long.valueOf(document.get("app_pv").toString()));
    }

    if (document.get("app_gmv_trade_amt") != null) {
      itemTime.setAppGmvTradeAmt(Double.valueOf(document.get("app_gmv_trade_amt").toString()));
    }
    if (document.get("app_alipay_trade_amt") != null) {
      itemTime.setAppAlipayTradeAmt(Double.valueOf(document.get("app_alipay_trade_amt").toString()));
    }
    if (document.get("app_gmv_auction_num") != null) {
      itemTime.setAppGmvAuctionNum(Long.valueOf(document.get("app_gmv_auction_num").toString()));
    }

    if (document.get("pc_gmv_trade_amt") != null) {
      itemTime.setPcGmvTradeAmt(Double.valueOf(document.get("pc_gmv_trade_amt").toString()));
    }
    if (document.get("pc_alipay_trade_amt") != null) {
      itemTime.setPcAlipayTradeAmt(Double.valueOf(document.get("pc_alipay_trade_amt").toString()));
    }
    if (document.get("pc_gmv_auction_num") != null) {
      itemTime.setPcGmvAuctionNum(Long.valueOf(document.get("pc_gmv_auction_num").toString()));
    }

    if (document.get("other_gmv_trade_amt") != null) {
      itemTime.setOtherGmvTradeAmt(Double.valueOf(document.get("other_gmv_trade_amt").toString()));
    }
    if (document.get("other_alipay_trade_amt") != null) {
      itemTime.setOtherAlipayTradeAmt(Double.valueOf(document.get("other_alipay_trade_amt").toString()));
    }
    if (document.get("other_gmv_auction_num") != null) {
      itemTime.setOtherGmvAuctionNum(Long.valueOf(document.get("other_gmv_auction_num").toString()));
    }
    map.put(key, itemTime);
    return itemTime;
  }

  @Test
  public void test11() {
    Map<String, String> map = Maps.newHashMap();
    map.put("dt", "$dt");
    map.put("seller_id", "$seller_id");
    map.put("auction_id", "$auction_id");
    map.put("hour", "$hour");

    AggregateIterable<Document> appData = MONGO_DATABASE.getCollection("item_weblog")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20180408),
                Filters.lte("dt", 20180408),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 564335965653L),
                Filters.eq("visit_platform", 1))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("app_pv", new BasicDBObject("$sum", "$stat_auction_time_app_weblog_d.pv")),
                new BsonField("app_uv", new BasicDBObject("$sum", "$stat_auction_time_app_weblog_d.uv")),
                new BsonField("app_gmv_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_trade_amt")),
                new BsonField("app_alipay_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.alipay_trade_amt")),
                new BsonField("app_gmv_auction_num", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_auction_num")))));

    log.info("app成交金额数据 ： {} ", StreamSupport.stream(appData.spliterator(), false)
        .mapToDouble(r -> Double.valueOf(r.get("app_alipay_trade_amt").toString())).sum());

    Map<String, ItemTime> data = Maps.newHashMap();

    StreamSupport.stream(appData.spliterator(), false)
        .forEach(document -> handler0(data, document, 1));

    AggregateIterable<Document> pcData = MONGO_DATABASE.getCollection("item_weblog")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20180408),
                Filters.lte("dt", 20180408),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 564335965653L),
                Filters.eq("visit_platform", 2))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("pc_pv", new BasicDBObject("$sum", "$stat_auction_time_pc_weblog_d.pv")),
                new BsonField("pc_uv", new BasicDBObject("$sum", "$stat_auction_time_pc_weblog_d.uv")),
                new BsonField("pc_gmv_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_trade_amt")),
                new BsonField("pc_alipay_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.alipay_trade_amt")),
                new BsonField("pc_gmv_auction_num", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_auction_num")))));

    StreamSupport.stream(pcData.spliterator(), false)
        .forEach(document -> handler0(data, document, 2));

    log.info("pc成交金额数据 ： {} ", StreamSupport.stream(pcData.spliterator(), false)
        .mapToDouble(r -> Double.valueOf(r.get("pc_alipay_trade_amt").toString())).sum());


    AggregateIterable<Document> otherData = MONGO_DATABASE.getCollection("item_weblog")
        .aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.gte("dt", 20180408),
                Filters.lte("dt", 20180408),
                Filters.eq("seller_id", 394695430),
                Filters.eq("auction_id", 564335965653L),
                Filters.eq("visit_platform", null))),
            Aggregates.group(new BasicDBObject(map),
                new BsonField("other_gmv_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_trade_amt")),
                new BsonField("other_alipay_trade_amt", new BasicDBObject("$sum", "$stat_auction_order_plus_d.alipay_trade_amt")),
                new BsonField("other_gmv_auction_num", new BasicDBObject("$sum", "$stat_auction_order_plus_d.gmv_auction_num")))));

    log.info("other成交金额数据 ： {} ", StreamSupport.stream(otherData.spliterator(), false)
        .mapToDouble(r -> Double.valueOf(r.get("other_alipay_trade_amt").toString())).sum());


    StreamSupport.stream(otherData.spliterator(), false)
        .forEach(document -> handler0(data, document, 0));

    Collection<ItemTime> regions = data.values();

    log.info("成交金额数据 ： {} ", regions.stream().mapToDouble(r -> r.getAppAlipayTradeAmt() +
        r.getPcAlipayTradeAmt() + r.getOtherAlipayTradeAmt()).sum());
  }


  @Test
  public void test12() {
    Iterable<Document> documents = MONGO_DATABASE.getCollection("stat_keyword")
        .find(
            Filters.and(
                Filters.eq("seller_id", 394695430),
                Filters.lte("dt", 20180522),
                Filters.gte("dt", 20180422),
                Filters.exists("stat_pc_keyword_data_d"),
                Filters.exists("stat_app_keyword_data_d")
            ))
        .limit(10000);

//    include=keyword,keywordValue,productNum,clickNum,keywordValue,searchNum

    System.out.println(Lists.newArrayList(documents).size());
  }
}
