package com.betterlxc.mongo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
public class DictRegion {

    private String province;

    private String pCode;

    private String city;

    private Long cid;

    private String cCode;

    public static void main(String[] args) {

        List list = Lists.newArrayList(1, 2, 3, 4);

        list.forEach(i -> {
            System.out.println(i);
            return;
        });
        System.out.println(111);
    }
}
