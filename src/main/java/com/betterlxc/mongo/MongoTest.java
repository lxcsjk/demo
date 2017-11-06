package com.betterlxc.mongo;

import com.google.common.collect.Lists;
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

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
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
}
