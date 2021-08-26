package com.betterlxc.excel.course;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuxincheng
 * @date 2021/7/12
 */
@Data
public class CourseDTO implements Serializable {

    /**
     * 课程名
     */
    @ExcelProperty(index = 0)
    private String courseName;

    /**
     * 状态 0 社招 1 校招
     */
    @ExcelProperty(index = 1)
    private String sourceStr;

    @ExcelIgnore
    private Integer source;

    /**
     * 课程类型 0 项目小班 1 1v1
     */
    @ExcelIgnore
    private Integer type;

    @ExcelProperty(index = 2)
    private String typeStr;

    /**
     * 岗位 0 产品 1 运营 2 开发 3 设计
     */
    @ExcelIgnore
    private Integer profession;

    @ExcelProperty(index = 3)
    private String professionStr;

    /**
     * 班级人数
     */
    @ExcelProperty(index = 4)
    private Integer peopleLimit;

    /**
     * 课次
     */
    @ExcelProperty(index = 5)
    private Integer num;

    /**
     * 时长
     */
    @ExcelProperty(index = 6)
    private Integer duration;

    /**
     * 状态 0 开启 1 关闭
     */
    @ExcelIgnore
    private Integer available = 0;
}
