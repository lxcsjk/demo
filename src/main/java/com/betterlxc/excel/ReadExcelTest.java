package com.betterlxc.excel;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class ReadExcelTest {

    private static final File EXCEL_PATH =
        new File(ClassLoader.getSystemResource("Book3.xlsx").getPath());


    @Test
    public void test1() {
        ImportParams params = new ImportParams();

        List<Map<String, Object>> list = ExcelImportUtil.importExcel(EXCEL_PATH, Map.class, params);
        System.out.println(list);
    }
}
