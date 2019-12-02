package com.betterlxc.ip.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class IpUtil {
    private List<IpDto> ipList;
    private Map<Integer, IpProvince> provinceMap;

    public IpUtil() {
        IpData ipData = new IpData();
        ipList = new ArrayList<>();
        List<String> s = ipData.getIpList();
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("初始化IpList失败");
        }
        for (String value : s) {
            String[] subArr = value.split(",");
            ipList.add(new IpDto(Integer.valueOf(subArr[0]), Long.valueOf(subArr[1]), Long.valueOf(subArr[2])));
        }
        Collections.sort(ipList);
        provinceMap = new HashMap<>();
        String[] provinces = ipData.getProvince();
        if (provinces == null || provinces.length == 0) {
            throw new IllegalArgumentException("初始化provinceMap失败");
        }
        for (String p : provinces) {
            String[] arr = p.split(",");
            IpProvince ipProvince = new IpProvince(Integer.valueOf(arr[0]), Long.valueOf(arr[1]), arr[2]);
            provinceMap.put(ipProvince.getSpid(), ipProvince);
        }
    }

    public static void main(String[] args) {
        String[] arr = "1.0.1.0".split("\\.");
        System.out.println(Long.valueOf(arr[0]) * 256 * 256 * 256 + Long.valueOf(arr[1]) * 256 * 256 + Long.valueOf(arr[2]) * 256 + Long.valueOf(arr[3]));
    }

    private Long calculateIp(String ip) {
        String[] arr = ip.split("\\.");
        return Long.valueOf(arr[0]) * 256 * 256 * 256 + Long.valueOf(arr[1]) * 256 * 256 + Long.valueOf(arr[2]) * 256 + Long.valueOf(arr[3]);
    }

    private int search(String ipStr) {
        long ipNum = calculateIp(ipStr);
        IpDto src = new IpDto(0, ipNum, 0);
        int idx = Collections.binarySearch(ipList, src);
        if (idx >= 0) {
            IpDto dto = ipList.get(idx);
            if (dto != null) {
                return dto.getSpid();
            }
        }
        return 0;
    }

    public IpProvince searchIpProvince(String ipStr) {
        return provinceMap.get(search(ipStr));
    }

    public long searchProvinceId(String ipStr) {
        return searchIpProvince(ipStr).getPid();
    }

    public String searchProvince(String ipStr) {
        return searchIpProvince(ipStr).getProvince();
    }

    class IpDto implements Comparable<IpDto> {
        private int spid;
        private long start;
        private long offset;

        public IpDto(int spid, long start, long offset) {
            this.spid = spid;
            this.start = start;
            this.offset = offset;
        }

        public int getSpid() {
            return spid;
        }

        public void setSpid(int spid) {
            this.spid = spid;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getOffset() {
            return offset;
        }

        public void setOffset(long offset) {
            this.offset = offset;
        }

        @Override
        public int compareTo(IpDto o) {
            if (start + offset < o.getStart()) {
                return -1;
            } else if (start > o.getStart() + o.getOffset()) {
                return 1;
            }
            return 0;
        }
    }

    class IpProvince {
        private int spid;
        private long pid;
        private String province;

        public IpProvince(int spid, long pid, String province) {
            this.spid = spid;
            this.pid = pid;
            this.province = province;
        }

        public int getSpid() {
            return spid;
        }

        public void setSpid(int spid) {
            this.spid = spid;
        }

        public long getPid() {
            return pid;
        }

        public void setPid(long pid) {
            this.pid = pid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
