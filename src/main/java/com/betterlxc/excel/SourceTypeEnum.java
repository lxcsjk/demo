package com.betterlxc.excel;

import lombok.AllArgsConstructor;

/**
 * @author liuxincheng
 * @date 2021/7/12
 */
@AllArgsConstructor
public enum SourceTypeEnum {

    /**
     * 社招
     */
    ALL(0, "全部"),

    /**
     * 社招
     */
    SOCIAL_RECRUITMENT(1, "社招"),

    /**
     * 校招
     */
    SCHOOL_RECRUITMENT(2, "校招");

    public int code;

    public String desc;

    public static SourceTypeEnum valueOf(int value) {
        for (SourceTypeEnum item : SourceTypeEnum.values()) {
            if (item.code == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal value for SourceEnum: " + value);
    }
}
