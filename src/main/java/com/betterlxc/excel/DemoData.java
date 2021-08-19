package com.betterlxc.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author liuxincheng
 * @date 2021/8/19
 */
@Data
public class DemoData {
    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 2)
    private String projectId;

}
