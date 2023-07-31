package com.hh.core.business.bl;

import com.hh.core.file.excel.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;

/**
 * Created by hh on 2019/6/17.
 * 读取报表新增字段数据
 */
@Slf4j
public class ReadReportUtil {

    public static String readExcel(File file) {
        String result = "";
        try {
            // 检查文件合法性
            checkFile(file);

            Workbook workbook = getWorkbook(file);

            // 获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();

            for (int s = 0; s < sheetNum; s++) {
                if (s == 0) {
                    System.out.println("## 授信数据");
                    parseLoanInfo(workbook, s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void parseLoanInfo(Workbook workbook, int s) {
        Sheet sheet = workbook.getSheetAt(s);
        if (null == sheet) {return;}

        // 获取行数
        int rowNum = sheet.getLastRowNum();
        for(int i = 0; i < rowNum + 1; i++){
            String sql = "update xy_apply.apply_limit_info set safeguard_measure = '$safeguardMeasure', risk_approve_measure = '$riskApproveMeasure', " +
                    "item_maintain = '$itemMaintain' where limit_no = '$limitNo';";
            if (i == 0) {continue;}
            Row row = sheet.getRow(i);
            if (null == row) {continue;}

            // 获取列数
            int cellNum = row.getLastCellNum();
            for (int j = 0; j < cellNum; j++) {
                if (j == 1) {
                    Cell cell = row.getCell(j);
                    if (null == cell) {continue;}
                    cell.setCellType(CellType.STRING);
                    String limitNo = cell.getStringCellValue();
                    if (StringUtils.isBlank(limitNo)) {
                        sql = "";
                        break;
                    }
                    sql = sql.replace("$limitNo", cell.getStringCellValue());
                }
                if (j == 4) {
                    Cell cell = row.getCell(j);
                    if (null == cell) {continue;}
                    cell.setCellType(CellType.STRING);
                    sql = sql.replace("$safeguardMeasure", cell.getStringCellValue());
                }
                if (j == 5) {
                    Cell cell = row.getCell(j);
                    if (null == cell) {continue;}
                    cell.setCellType(CellType.STRING);
                    sql = sql.replace("$riskApproveMeasure", cell.getStringCellValue());
                }
                if (j == 6) {
                    Cell cell = row.getCell(j);
                    if (null == cell) {continue;}
                    cell.setCellType(CellType.STRING);
                    sql = sql.replace("$itemMaintain", cell.getStringCellValue());
                }
            }
            System.out.println(sql);
            System.out.println();
        }
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

    public static void main(String[] args) {
        File file = new File("d:\\xyhh\\Desktop\\存量数据处理-授信-风控提供(1).xlsx");
        readExcel(file);
    }
}