package com.betterlxc.ip.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class IpUtil2 {
  private List<IpDto> ipList;

  public IpUtil2() {
    IpData2 ipData2 = new IpData2();
    ipList = new ArrayList<>();
    List<String> s = ipData2.getIpList();
    if (s == null || s.isEmpty()) {
      throw new IllegalArgumentException("初始化IpList失败");
    }
    for (String value : s) {
      String[] subArr = value.split(",");
      ipList.add(new IpDto(Integer.valueOf(subArr[0]), Integer.valueOf(subArr[1]), Long.valueOf(subArr[2]), Long.valueOf(subArr[3])));
    }
    Collections.sort(ipList);
  }

  private Long calculateIp(String ip) {
    String[] arr = ip.split("\\.");
    return Long.valueOf(arr[0]) * 256 * 256 * 256 + Long.valueOf(arr[1]) * 256 * 256 + Long.valueOf(arr[2]) * 256 + Long.valueOf(arr[3]);
  }

  public IpDto search(String ipStr) {
    long ipNum = calculateIp(ipStr);
    IpDto src = new IpDto(0, 0, ipNum, 0);
    int idx = Collections.binarySearch(ipList, src);
    if (idx >= 0) {
      IpDto dto = ipList.get(idx);
      if (dto != null) {
        return dto;
      }
    }
    return null;
  }
}
