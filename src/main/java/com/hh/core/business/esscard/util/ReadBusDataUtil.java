package com.hh.core.business.esscard.util;

import com.hh.core.file.excel.Constants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by hh on 2019/6/17.
 * 读取吕梁公交乘车数据
 */
public class ReadBusDataUtil {

    public static String readExcel(File file) {
        String result = "";
        try {
            // 检查文件合法性
            checkFile(file);

            Workbook workbook = getWorkbook(file);

            // 获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();

            for (int i = 0; i < sheetNum; i++) {
                if (i != 2) {continue;}
                Sheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {continue;}

                // 获取行数
                int rowNum = sheet.getLastRowNum();
                String sql = "select aac002, aae054 from esc_wallet_account where aae054 in(";
                for(int j = 0; j < rowNum + 1; j++){
                    if (j == 0) {continue;}
                    Row row = sheet.getRow(j);
                    if (null == row) {continue;}

                    // 获取列数
                    int cellNum = row.getLastCellNum();
                    for (int k = 0; k < cellNum; k++) {
                        if (k == 3) {
                            Cell cell = row.getCell(k);
                            sql += "'621" + cell.getStringCellValue() + "',";
                        }
                    }
                }
                sql += ")";
                System.out.println(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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