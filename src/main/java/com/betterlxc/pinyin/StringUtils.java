package com.betterlxc.pinyin;

import java.util.regex.Pattern;

/**
 * @author liuxincheng
 * @date 2019/9/26
 */
public class StringUtils {

    private static final Pattern PATTERN_1 = Pattern.compile("[0-9]*");

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumberic(String str) {
        return PATTERN_1.matcher(str).matches();
    }

    /**
     * 判断一个字符串是否为字母
     *
     * @param fastData
     * @return
     */
    public static boolean isAbc(String fastData) {
        char c = fastData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否为汉字
     *
     * @param str
     * @return
     */
    public static boolean vd(String str) {

        char[] chars = str.toCharArray();
        boolean isUtf8 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;

                if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isUtf8 = true;
                    break;
                }
            }
        }
        return isUtf8;
    }
}