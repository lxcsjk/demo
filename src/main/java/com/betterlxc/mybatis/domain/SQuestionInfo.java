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
public class SQuestionInfo {
    /**
     * 主键
     */
    private Integer iD;

    /**
     * 题库id
     */
    private Integer questionID;

    /**
     * 问题描述
     */
    private String content;

    /**
     * 问题类型 0 选择题 1 填空题
     */
    private Byte type;

    /**
     * 问题图片url
     */
    private String picture;

    /**
     * 问题选项
     */
    private String selection;

    /**
     * 问题答案 如果是选择题 就是id 如果是填空就是内容
     */
    private String answer;

    /**
     * 是否必选题
     */
    private Byte required;

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