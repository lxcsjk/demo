package com.betterlxc.map;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.drew.imaging.ImageMetadataReader.readMetadata;

/**
 * Created by LXC on 2017/4/24.
 */
public class MapsTest {

    private static void print(Metadata metadata) {
        System.out.println("-------------------------------------");
        //遍历图片中的元数据
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.println("ERROR: " + error);
                }
            }
        }
    }

    private static void deliberateAccessories(Map<Integer, String> mapSportsPersonality) {
        mapSportsPersonality.forEach((key, value) -> System.out.println("Key:" + key + ", Value:" + value));
    }

    public static void main(String[] args) {
        Map map = Maps.newHashMap();
        map.put(1, 1111);

        Map map1 = Maps.newHashMap();
        map1.put(1, 5555);
        map1.put(2, 2222);

        Map map2 = Maps.newHashMap();
        map2.put(3, 2222);

        Map map3 = Maps.newHashMap();

        map3.putAll(map);
        map3.putAll(map1);
        map3.putAll(map2);
        Map map4 = Maps.newHashMap();
        map3.putAll(map4);
        System.out.println(map3);

    }

    @Test
    public void test() throws ImageProcessingException, IOException {
        File file = new File("1.jpg");
        Metadata metadata = readMetadata(file);
        print(metadata);
    }

    @Test
    public void test1() {
        Map<Integer, String> mapVehicleNoAndOwner = new LinkedHashMap<>(2, 0.75f, true);

        mapVehicleNoAndOwner.put(1000, "Federer");
        mapVehicleNoAndOwner.put(2000, "Bradman");
        mapVehicleNoAndOwner.put(3000, "Jordan");
        mapVehicleNoAndOwner.put(4000, "Woods");
        mapVehicleNoAndOwner.put(5000, "Ali");

        Set<Integer> integers = mapVehicleNoAndOwner.keySet();
        Set<Integer> set = mapVehicleNoAndOwner.keySet();
        System.out.println();
//        System.out.println("1. Iterating default LinkedHashMap: ");
//        deliberateAccessories(mapVehicleNoAndOwner);
//        int key = 1000;
//        System.out.printf("2. Accessting value at key: %d is %s\n", key, mapVehicleNoAndOwner.get(key));
//
//        key = 3000;
//        System.out.printf("3. Accessting value at key: %d is %s\n", key, mapVehicleNoAndOwner.get(key));
//
//        System.out.println("4. Iterating LinkedHashMap, least accessed to most accessed keys: ");
//        deliberateAccessories(mapVehicleNoAndOwner);
    }


}
