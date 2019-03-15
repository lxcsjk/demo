package com.betterlxc.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ItemTime extends DictRegion {

    private Long dt;

    private Long sellerId;

    private Long appPv = 0L;

    private Long appUv = 0L;

    private Double appGmvTradeAmt = 0.0D;

    private Double appAlipayTradeAmt = 0.0D;

    private Long appGmvAuctionNum = 0L;

    private Long pcPv = 0L;

    private Long pcUv = 0L;

    private Double pcGmvTradeAmt = 0.0D;

    private Double pcAlipayTradeAmt = 0.0D;

    private Long pcGmvAuctionNum = 0L;

    private Double otherGmvTradeAmt = 0.0D;

    private Double otherAlipayTradeAmt = 0.0D;

    private Long otherGmvAuctionNum = 0L;

    private Long hour;

    public void a() {

    }
}
