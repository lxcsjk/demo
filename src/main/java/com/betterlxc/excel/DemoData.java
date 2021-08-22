package com.betterlxc.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author liuxincheng
 * @date 2021/8/19
 */
@Data
public class DemoData {
    @ExcelProperty(index = 0)
    private String dramaIdStr;

    @ExcelProperty(index = 2)
    private String projectIdStr;
    @ExcelIgnore
    private int status = 0;
    @ExcelIgnore
    private String creator = "import";
    @ExcelIgnore
    private String updater = "import";
    @ExcelIgnore
    private Integer dramaId;
    @ExcelIgnore
    private Integer projectId;

}
