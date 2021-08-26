package com.betterlxc.excel;

import lombok.AllArgsConstructor;

/**
 * @author liuxincheng
 * @date 2021/7/12
 */
@AllArgsConstructor
public enum ProfessionTypeEnum {

    /**
     * 运营
     */
    OPERATION(1, "运营"),

    /**
     * 开发
     */
    DEVELOPMENT(2, "开发"),

    /**
     * 设计
     */
    DESIGN(3, "设计"),

    /**
     * 产品
     */
    PRODUCT(4, "产品"),

    /**
     * 无明确意向
     */
    NONE(5, "无明确意向"),

    /**
     * 数据分析
     */
    DATA_ANALYSIS(6, "数据分析"),
    ;

    public int code;

    public String desc;

    public static ProfessionTypeEnum valueOf(int value) {
        for (ProfessionTypeEnum item : ProfessionTypeEnum.values()) {
            if (item.code == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal value for ProfessionTypeEnum: " + value);
    }
}
