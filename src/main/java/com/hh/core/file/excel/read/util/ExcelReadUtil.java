package com.hh.core.file.excel.read.util;

import com.hh.core.file.excel.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2019/6/17.
 * excel解析读取工具类
 */
@Slf4j
public class ExcelReadUtil {

    public static List<List<String>> readExcel(File file) {
        List<List<String>> list = new ArrayList<>();
        try {
            // 检查文件合法性
            checkFile(file);

            Workbook workbook = getWorkbook(file);

            // 获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();

            for (int i = 0; i < sheetNum; i++) {
                if (i != 0) {break;}
                Sheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {continue;}

                // 获取行数
                int rowNum = sheet.getLastRowNum();
                for(int j = 0; j < rowNum + 1; j++){
                    if (j == 0) {continue;}
                    Row row = sheet.getRow(j);
                    if (null == row) {continue;}
                    List<String> cellList = new ArrayList<>();

                    // 获取列数
                    int cellNum = row.getLastCellNum();
                    for (int k = 0; k < cellNum; k++) {
                        Cell cell = row.getCell(k);
                        if (null == cell) {continue;}
                        cellList.add(cell.getStringCellValue());
                    }
                    list.add(cellList);
                }
            }
        } catch (IOException e) {
            log.error("read excel error:{}", e.getMessage());
        }
        return list;
    }

    private static void checkFile(File file) throws IOException {
        if (null == file) {
            throw new FileNotFoundException("file doesn't exist");
        }

        String fileName = file.getName();
        if (!fileName.endsWith(Constants.EXCEL_XLS_FORMAT)
                && !fileName.endsWith(Constants.EXCEL_XLSX_FORMAT)) {
            throw new IOException(fileName + "isn't excel");
        }
    }

    /**
     * 得到解析类
     * @param file 文件
     * @return 解析类
     */
    private static Workbook getWorkbook(File file) throws IOException {
        Workbook workbook;

        // 获取文件名
        String fileName = file.getName();

        // 获取文件输入流
        InputStream in = new FileInputStream(file);

        // 判断文件格式
        if (fileName.endsWith(Constants.EXCEL_XLS_FORMAT)) {
            workbook = new HSSFWorkbook(in);
        } else {
            workbook = new XSSFWorkbook(in);
        }
        return workbook;
    }
}