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
public class PropImg {

  public static final TypeReference<List<PropImg>> TYPE = new TypeReference<List<PropImg>>() {
  };

  private Long id;

  private Integer position;

  private String properties;

  private String url;

  public PropImg(@JsonProperty("id") Long id,
                 @JsonProperty("position") Integer position,
                 @JsonProperty("properties") String properties,
                 @JsonProperty("url") String url) {
    this.id = id;
    this.position = position;
    this.properties = properties;
    this.url = url;
  }
}
