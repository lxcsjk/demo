package com.betterlxc.pinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.text.Collator;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuxincheng
 * @date 2019/9/26
 */
public class PinyinSortUtils {

    private final static Comparator<Object> ENGLISH_COMPARE = Collator.getInstance(Locale.ENGLISH);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return NUMBER_PATTERN.matcher(str).matches();
    }

    /**
     * 判断一个字符串是否为字母
     *
     * @param fastData
     * @return
     */
    public static boolean isAbc(String fastData) {
        char c = fastData.charAt(0);
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }

    public static List<String> pinyinSort(List<String> names) {
        Map<String, String> data = names.stream().distinct().collect(Collectors.toMap(s -> s.replace("一", "1")
            .replace("二", "2")
            .replace("三", "3")
            .replace("四", "4")
            .replace("五", "5")
            .replace("六", "6")
            .replace("七", "7")
            .replace("八", "8")
            .replace("九", "9"), c -> c));

        List<String> abdList = Lists.newArrayList();
        List<String> numList = Lists.newArrayList();
        List<String> chineseList = Lists.newLinkedList();

        Map<String, String> chineseMap = Maps.newTreeMap();
        Set<String> list = data.keySet();

        list.forEach(s -> {
            char[] value = s.toCharArray();
            char c = value[0];
            String a = String.valueOf(c);

            if (isNumber(a)) {
                numList.add(s);
            } else if (isAbc(a)) {
                abdList.add(s);
            } else {
                chineseMap.put(py(s), s);
            }
        });

        abdList.sort(ENGLISH_COMPARE);
        numList.sort((o1, o2) -> {
            char[] value = o1.toCharArray();
            char c = value[0];

            char[] value2 = o2.toCharArray();
            char c2 = value2[0];

            Integer a = (int) c;
            Integer b = (int) c2;
            return a.compareTo(b);
        });
        chineseMap.forEach((k, v) -> chineseList.add(v));

        return Stream.of(abdList, numList, chineseList).flatMap(Collection::stream).map(data::get).collect(Collectors.toList());
    }

    private static String py(String str) {
        try {
            return PinyinHelper.getShortPinyin(str);
        } catch (PinyinException e) {
            return "";
        }
    }
}
