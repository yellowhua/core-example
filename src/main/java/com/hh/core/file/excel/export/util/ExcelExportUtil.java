package com.hh.core.file.excel.export.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * Created by hh on 2019/6/17.
 * excel导出工具类
 */
public class ExcelExportUtil {

    public static void exportExcel(OutputStream out) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet名称");

        // 创建3行
        for (int i = 0; i < 3; i++) {
            XSSFRow row = sheet.createRow(i);
            // 创建5列
            for (int j = 0; j < 5; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(i + j);
            }
        }

        workbook.write(out);
    }


}