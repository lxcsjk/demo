package com.betterlxc.excel;

import lombok.Data;

/**
 * @author liuxincheng
 * @date 2020/3/9
 */
@Data
public class RelievedTagDTO {
    /**
     * tagid
     */
    private Integer id;

    /**
     * tag名称
     */
    private String name;

    /**
     * 状态 0 可用 1不可用
     */
    private Byte status;

    /**
     * 排序
     */
    private Integer sort;
}
