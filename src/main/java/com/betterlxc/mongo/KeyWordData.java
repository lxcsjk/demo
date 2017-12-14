package com.betterlxc.mongo;

import lombok.Data;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
public class KeyWordData {

  private Integer dt;

  private String keyWord;

  private Long landingUv;

  private Long landingPv;

  private Long auctionNum;

  private Long winnerNum;

  private Double tradeAmt;
}
