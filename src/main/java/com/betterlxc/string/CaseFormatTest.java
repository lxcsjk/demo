package com.betterlxc.string;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class CaseFormatTest {

  @Test
  public void lowerUnderscoreTest() {
    String underscoreStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "dateCreated");
    log.info("驼峰  转  小写下划线   -------->   {}", underscoreStr);
  }

  @Test
  public void lowerHyphenTest() {
    String lowerHyphenStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "dateCreated");
    log.info("驼峰  转  连字号   -------->   {}", lowerHyphenStr);
  }

  @Test
  public void upperUnderscoreTest() {
    String upperUnderscoreStr = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "dateCreated");
    log.info("驼峰  转  大写下划线   -------->   {}", upperUnderscoreStr);
  }

  @Test
  public void test() {
    List<String> list = Lists.newArrayList("stat_shop_wireless_log_d_all"
        , "stat_shop_weblog_d"
        , "stat_sku_trade_d"
        , "stat_shop_platform_traffic_d"
        , "stat_shop_pc_search_effect"
        , "dws_item_app_src_platform_new_d"
        , "stat_shop_comment_180d"
        , "stat_shop_app_search_effect"
        , "stat_item_app_query_effect_allbelong_d"
        , "stat_shop_app_query_effect_allbelong_d"
        , "stat_rpt_shop_src_last_effect_new_d"
        , "stat_dws_shop_wireless_platform_src_new_d"
        , "stat_auction_comment_180d"
        , "stat_auction_trade_d"
        , "dws_auction_discount_d"
        , "dws_auction_pc_search_src_effect_new_d"
        , "dws_auctionset_asso_d"
        , "dws_auction_asso_d"
        , "dwb_shop_platform_view_h"
        , "dwb_item_search_effect_new_d"
        , "dwb_shop_trade_d"
        , "dwb_shop_platform_view_d"
        , "dwb_item_app_query_effect_allbelong_d"
        , "dwb_shop_platform_trade_repeat_traffic_d"
        , "dwb_shop_platform_trade_h"
        , "dwb_shop_platform_trade_d"
        , "dwb_shop_delivery_stat_d"
        , "dwb_shop_comment_d"
        , "dwb_item_source_last_effect_new_d"
        , "dwb_shop_collect_d"
        , "dwb_auction_trade_repeat_traffic_d"
        , "dwb_auction_platform_traffic_d"
        , "dwb_auction_trade_repeat_trade_d"
        , "dwb_auction_trade_d"
        , "dwb_auction_platform_trade_d"
        , "dwb_auction_app_search_src_effect_new_d"
        , "dwb_auc_pc_page_traff_d"
        , "dwb_auction_collect_d"
        , "dwb_auction_comment_d");

    List<String> list1 = Lists.newArrayList("stat_rpt_shop_src_last_effect_new_d"
        , "stat_shop_platform_traffic_d"
        , "dwb_shop_delivery_stat_d"
        , "dws_auction_pc_search_src_effect_new_d"
        , "dwb_auction_app_search_src_effect_new_d"
        , "dwb_item_source_last_effect_new_d"
        , "dwb_shop_platform_trade_repeat_traffic_d"
        , "dwb_shop_platform_trade_d"
        , "stat_auction_comment_180d"
        , "dwb_auction_comment_d"
        , "dwb_shop_platform_view_d"
        , "stat_auction_trade_d"
        , "dwb_auction_trade_d"
        , "dwb_item_app_query_effect_allbelong_d"
        , "dwb_item_app_query_effect_allbelong_d"
        , "dwb_shop_trade_d"
        , "stat_item_app_query_effect_allbelong_d"
        , "dws_auction_discount_d"
        , "stat_shop_app_query_effect_allbelong_d"
        , "dwb_auction_platform_trade_d"
        , "dwb_auc_pc_page_traff_d"
        , "dwb_auction_platform_traffic_d"
        , "dwb_auction_collect_d"
        , "dwb_shop_platform_view_h"
        , "dws_auction_asso_d"
        , "stat_shop_weblog_d"
        , "dwb_shop_collect_d"
        , "dwb_shop_comment_d"
        , "stat_shop_comment_180d");

    list.removeAll(list1);

    System.out.println(list);
  }
}
