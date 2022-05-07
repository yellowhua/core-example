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

            for (int i = 0; i < sheetNum; i++) {
                if (i == 0) {
                    System.out.println("## 工程项目数据");
                    parseLimitProject(workbook, i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void parseApplyLoanInfo(Workbook workbook, int i) {
        Sheet sheet = workbook.getSheetAt(i);
        if (null == sheet) {return;}

        // 获取行数
        int rowNum = sheet.getLastRowNum();
        for(int j = 0; j < rowNum + 1; j++){
            boolean flag = true;
            String sql = "update xy_apply.apply_loan_info set statement_amount = $statementAmount where id = $id;";
            if (j == 0) {continue;}
            Row row = sheet.getRow(j);
            if (null == row) {continue;}

            // 获取列数
            int cellNum = row.getLastCellNum();
            for (int k = 0; k < cellNum; k++) {
                if (k == 6) {
                    Cell cell = row.getCell(k);
                    cell.setCellType(CellType.STRING);
                    if (StringUtils.isEmpty(cell.getStringCellValue())) {
                        flag = false;
                    }
                    sql = sql.replace("$statementAmount", cell.getStringCellValue());
                }
                if (k == 0) {
                    Cell cell = row.getCell(k);
                    cell.setCellType(CellType.STRING);
                    sql = sql.replace("$id", cell.getStringCellValue());
                }
            }
            if (flag) {
                System.out.println(sql);
            }
        }
    }

    private static void parseLimitProject(Workbook workbook, int i) {
        Sheet sheet = workbook.getSheetAt(i);
        if (null == sheet) {return;}

        // 获取行数
        int rowNum = sheet.getLastRowNum();
        for(int j = 0; j < rowNum + 1; j++){
            String sql = "update xy_limit.limit_project set owner_name = $ownerName where limit_no = $limitNo and project_id = $projectId;";
            if (j == 0) {continue;}
            Row row = sheet.getRow(j);
            if (null == row) {continue;}

            // 获取列数
            int cellNum = row.getLastCellNum();
            for (int k = 0; k < cellNum; k++) {
                if (k == 5) {
                    Cell cell = row.getCell(k);
                    sql = sql.replace("$ownerName", "'" + cell.getStringCellValue() + "'");
                }
                if (k == 3) {
                    Cell cell = row.getCell(k);
                    sql = sql.replace("$limitNo", "'" + cell.getStringCellValue() + "'");
                }
                if (k == 4) {
                    Cell cell = row.getCell(k);
                    sql = sql.replace("$projectId", cell.getStringCellValue());
                }
            }
            String sql2 = sql.replace("xy_limit.limit_project", "xy_apply.apply_limit_project");
            System.out.println(sql);
            System.out.println(sql2);
        }
    }

    private static void parseLimitInfo(Workbook workbook, int i) {
        Sheet sheet = workbook.getSheetAt(i);
        if (null == sheet) {return;}

        // 获取行数
        int rowNum = sheet.getLastRowNum();
        for(int j = 0; j < rowNum + 1; j++){
            String sql = "update xy_limit.limit_info set share_with = $shareWith, approve_date = $approveDate where limit_no = $limitNo;";
            if (j == 0) {continue;}
            Row row = sheet.getRow(j);
            if (null == row) {continue;}

            // 获取列数
            int cellNum = row.getLastCellNum();
            for (int k = 0; k < cellNum; k++) {
                if (k == 28) {
                    Cell cell = row.getCell(k);
                    sql = sql.replace("$shareWith", "'" + cell.getStringCellValue() + "'");
                }
                if (k == 29) {
                    Cell cell = row.getCell(k);
                    sql = sql.replace("$approveDate", "'" + DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd") + "'");
                }
                if (k == 1) {
                    Cell cell = row.getCell(k);
                    sql = sql.replace("$limitNo", "'" + cell.getStringCellValue() + "'");
                }
            }
            String sql2 = sql.replace("xy_limit.limit_info", "xy_apply.apply_limit_info");
            String sql3 = sql.replace("xy_limit.limit_info", "xy_apply.apply_limit_info_his");
            System.out.println(sql);
            System.out.println(sql2);
            System.out.println(sql3);
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
        File file = new File("d:\\xyhh\\Desktop\\work\\xy\\保理\\台账新增字段历史数据补充\\业主名称字段历史数据补充.xlsx");
        readExcel(file);
    }
}