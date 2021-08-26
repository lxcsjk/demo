package com.betterlxc.excel.course;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.Test;

import java.io.File;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class CourseExcelTest {

    private static final File EXCEL_PATH = new File("/Users/lxc/Downloads/1.xlsx");

    @Test
    public void test() {
        ExcelReader excelReader = EasyExcel.read(EXCEL_PATH).build();

        ReadSheet readSheet1 =
            EasyExcel.readSheet(0).head(CourseDTO.class).registerReadListener(new CourseDataListener()).build();
//        ReadSheet readSheet2 =
//            EasyExcel.readSheet(1).head(DemoData.class).registerReadListener(new DemoDataListener()).build();

        excelReader.read(readSheet1);
        excelReader.finish();
        System.out.println();
    }
}
