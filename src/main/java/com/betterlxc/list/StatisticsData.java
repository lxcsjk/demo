package com.betterlxc.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsData {

  /**
   * 商品id
   */
  private Long numIid;

  /**
   * 成交数
   */
  private Long alipayTradeNum;

  /**
   * --成功完成支付宝支付的金额(元)
   */
  private Double alipayTradeAmt;

  /**
   * --成功拍下并完成支付宝付款的人数
   */
  private Long alipayWinnerNum;

  /**
   * --成功拍下并完成支付宝付款的商品件数
   */
  private Long alipayAuctionNum;

  /**
   * --添加购物车的用户数
   */
  private Long addCartUserNum;

  /**
   * --退款成功的金额
   */
  private Double succRefundTradeAmt;

  /**
   * 收藏商品的总次数
   */
  private Long auctionCollectNum;

  /**
   * 访客数 商品级别才有
   */
  private Long uv;
}
