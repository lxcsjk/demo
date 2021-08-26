package com.betterlxc.excel;

import lombok.AllArgsConstructor;

/**
 * @author liuxincheng
 * @date 2021/7/12
 */
@AllArgsConstructor
public enum CourseTypeEnum {

    /**
     * 项目小班
     */
    SMALL(1, "项目小班"),

    /**
     * 1v1
     */
    ALONE(2, "1v1"),

    /**
     * 咨询课
     */
    ADVISORY(3, "咨询课");

    public int code;

    public String desc;

    public static CourseTypeEnum valueOf(int value) {
        for (CourseTypeEnum item : CourseTypeEnum.values()) {
            if (item.code == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal value for CourseTypeEnum: " + value);
    }
}
