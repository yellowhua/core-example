package com.hh.core.file.excel.util;

import org.junit.Test;

import java.io.File;

/**
 * Created by hh on 2019/6/27.
 * excel工具类测试
 */
public class ExcelUtilTest {

    @Test
    public void testReadExcel() {
        String path = "C:\\Users\\Administrator\\Desktop\\xc.xlsx";
        ExcelUtil.readExcel(new File(path));
    }
}
