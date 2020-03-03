package com.betterlxc.mybatis.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuxincheng
 * @date 2019/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SQuestion {
    /**
     * 主键
     */
    private Integer iD;

    /**
     * 题库标题
     */
    private String title;

    /**
     * C端文案
     */
    private String content;

    /**
     * 需答几道选择题
     */
    private Integer choiceNum;

    /**
     * 需答几道填空题
     */
    private Integer blankNum;

    /**
     * 是否开启必选题
     */
    private Byte required;

    /**
     * 0 指定必选题 1 每题池必现一题
     */
    private Byte requiredType;

    /**
     * 创建人ID
     */
    private Integer userID;

    /**
     * 创建人名字
     */
    private String userName;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}