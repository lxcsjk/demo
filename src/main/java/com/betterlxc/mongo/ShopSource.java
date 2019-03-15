package com.betterlxc.mongo;


/**
 * @author LXC
 */
public class ShopSource {

  private Long dt;

  private Long sellerId;

  private String srcId;

  private String name;

  private Long uv;

  private Long pv;

  private Long orderQuantity;

  private Long orderBuyerCount;

  private Long payBuyerCount;


  public Long getDt() {
    return dt;
  }

  public void setDt(Long dt) {
    this.dt = dt;
  }

  public Long getSellerId() {
    return sellerId;
  }

  public void setSellerId(Long sellerId) {
    this.sellerId = sellerId;
  }

  public String getSrcId() {
    return srcId;
  }

  public void setSrcId(String srcId) {
    this.srcId = srcId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getUv() {
    return uv;
  }

  public void setUv(Long uv) {
    this.uv = uv;
  }

  public Long getPv() {
    return pv;
  }

  public void setPv(Long pv) {
    this.pv = pv;
  }

  public Long getOrderQuantity() {
    return orderQuantity;
  }

  public void setOrderQuantity(Long orderQuantity) {
    this.orderQuantity = orderQuantity;
  }

  public Long getOrderBuyerCount() {
    return orderBuyerCount;
  }

  public void setOrderBuyerCount(Long orderBuyerCount) {
    this.orderBuyerCount = orderBuyerCount;
  }

  public Long getPayBuyerCount() {
    return payBuyerCount;
  }

  public void setPayBuyerCount(Long payBuyerCount) {
    this.payBuyerCount = payBuyerCount;
  }
}
