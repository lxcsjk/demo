package com.betterlxc.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

import java.util.List;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
public class Sku {

    public static final TypeReference<List<Sku>> TYPE = new TypeReference<List<Sku>>() {
    };

    private String created;

    private String modified;

    private String outerId;

    private Double price;

    private String properties;

    private String propertiesName;

    private Integer quantity;

    private Long skuId;

    public Sku(@JsonProperty("created") String created,
               @JsonProperty("modified") String modified,
               @JsonProperty("outer_id") String outerId,
               @JsonProperty("price") Double price,
               @JsonProperty("properties") String properties,
               @JsonProperty("properties_name") String propertiesName,
               @JsonProperty("quantity") Integer quantity,
               @JsonProperty("sku_id") Long skuId) {
        this.created = created;
        this.modified = modified;
        this.outerId = outerId;
        this.price = price;
        this.properties = properties;
        this.propertiesName = propertiesName;
        this.quantity = quantity;
        this.skuId = skuId;
    }
}
