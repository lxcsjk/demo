package com.betterlxc.mongo;


import lombok.Data;

/**
 * @author liuweixi
 * @date 2017/11/13
 */
@Data
public class Source {


  protected Long dt;

  protected Long sellerId;

  protected String srcId;

  protected String name;

  protected Long uv;

  protected Long pv;

  protected Long orderQuantity;

  protected Long orderBuyerCount;

  protected Long payBuyerCount;

  public void add(Source obj) {
    this.uv = this.uv + obj.getUv();
    this.pv = this.pv + obj.getPv();
    this.orderQuantity = this.orderQuantity + obj.getOrderQuantity();
    this.orderBuyerCount = this.orderBuyerCount + obj.getOrderBuyerCount();
    this.payBuyerCount = this.payBuyerCount + obj.getPayBuyerCount();
  }

  public void nullToZero() {
    uv = uv == null ? 0 : uv;
    pv = pv == null ? 0 : pv;
    orderQuantity = orderQuantity == null ? 0 : orderQuantity;
    orderBuyerCount = orderBuyerCount == null ? 0 : orderBuyerCount;
    payBuyerCount = payBuyerCount == null ? 0 : payBuyerCount;
  }
}
