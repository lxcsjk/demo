package com.betterlxc.mybatis.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuxincheng
 * @date 2019/12/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SQuestionProjectRecord {
    /**
     * 主键
     */
    private Integer iD;

    /**
     * 题库id
     */
    private Integer questionId;

    /**
     * 题库标题
     */
    private String questionName;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目标题
     */
    private String projectName;

    /**
     * 创建人ID
     */
    private Integer userId;

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