package com.betterlxc.string;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author LXC
 * @date 2017/5/10
 */
@Data
public class Place {

    private String key;

    private String name;

    private List<Map<String, String>> cities;

    private int num;

    public static void add(Place place) {
        place.setNum(place.getNum() + 1);
    }

    public static void main(String[] args) {
//        Place place = new Place();
//        place.setNum(1);
//
//        add(place);
//        System.out.println(place.getNum());


        String s = "答题取题模式_副本";

        int i = s.indexOf("副本") + 2;
        System.out.println(s.substring(i));


    }
}
