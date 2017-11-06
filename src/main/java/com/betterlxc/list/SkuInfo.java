package com.betterlxc.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuInfo {

  /**
   * sku id
   */
  private Long skuId;

  /**
   * 商家编码
   */
  private String outerId;

  /**
   * 别名
   */
  private String alias;

  /**
   * 实际库存
   */
  private Integer actualNum;

  /**
   * 库存淘宝
   */
  private Integer quantity;


  /**
   * 颜色
   */
  private String color;

  /**
   * 图片
   */
  private String img;

  /**
   * 商品级别 计算所需要的原始数据
   */
  private Map<String, StatisticsData> data;

  public SkuInfo(Sku sku, Map<String, String> imgMap) {
    this.setSkuId(sku.getSkuId());
    this.setOuterId(sku.getOuterId());
    this.setQuantity(sku.getQuantity());

    String[] ss = sku.getPropertiesName().split(":");
    int a = 0;
    int b = 0;
    for (int i = 0; i < ss.length; i++) {
      if (ss[i].contains("颜色")) {
        a = i + 1;
      }
      if ("尺码".equals(ss[i])) {
        b = i + 1;
      }
    }

    if (a > 0) {
      String color = ss[a];
      int z = color.indexOf(";");
      this.setColor(color.substring(0, z));
    }

    if (b > 0) {
      String alias = ss[b];
      this.setAlias(alias);
    }

    String[] imgIds = sku.getProperties().split(";");
    String imgId = imgIds[0];

    this.setImg(imgMap.get(imgId));
  }
}
