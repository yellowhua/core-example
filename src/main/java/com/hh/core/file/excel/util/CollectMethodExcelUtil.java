package com.hh.core.file.excel.util;

import com.hh.core.jpa.domain.CollectMethod;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hh on 2019/6/17.
 * 采集方式读取excel工具类
 */
public class CollectMethodExcelUtil {

    /**
     * 读取主机类文件
     * @param file 文件对象
     * @return 采集方式对象
     */
    public static List<CollectMethod> readHostExcel(File file) {
        List<CollectMethod> collectMethods = new ArrayList<>();
        try {
            // 检查文件合法性
            checkFile(file);

            Workbook workbook = getWorkbook(file);

            // 获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetNum; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {continue;}

                // 获取行数
                int rowNum = sheet.getLastRowNum();
                for(int j = 0; j < rowNum + 1; j++){
                    // 跳过第一行
                    if (j == 0) {continue;}
                    Row row = sheet.getRow(j);
                    if (null == row) {continue;}

                    // 获取列数
                    String cellValue4 = "";
                    String collectMethodValue = "";
                    CollectMethod collectMethod = new CollectMethod();
                    int cellNum = row.getLastCellNum();
                    for (int k = 0; k < cellNum; k++) {
                        Cell cell = row.getCell(k);

                        if (i < 4) {
                            // 获取KPI_ID
                            if (k == 3) {
                                if (null == cell || StringUtils.isEmpty(cell.getStringCellValue())) {break;}
                                collectMethod.setKpiId(cell.getStringCellValue());
                            }

                            // 获取采集命令
                            if (k == 4) {
                                cellValue4 = cell.getStringCellValue();
                            }

                            // 获取采集方式
                            if (k == 6) {
                                if (!"".equals(cellValue4)) {
                                    collectMethodValue += "系统文件：\n" + cellValue4;
                                }
                                collectMethodValue += "\n计算方法：\n" + cell.getStringCellValue();
                                collectMethod.setCollectMethod(collectMethodValue);
                            }
                        } else {
                            // 获取KPI_ID
                            if (k == 1) {
                                if (null == cell || StringUtils.isEmpty(cell.getStringCellValue())) {break;}
                                collectMethod.setKpiId(cell.getStringCellValue());
                            }

                            // 获取采集方式
                            if (k == 4) {
                                collectMethodValue = "计算方法：\n" + cell.getStringCellValue();
                                collectMethod.setCollectMethod(collectMethodValue);
                            }
                        }
                    }
                    if (StringUtils.isNotEmpty(collectMethod.getKpiId())) {
                        collectMethods.add(collectMethod);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectMethods;
    }

    /**
     * 读取数据库类文件
     * @param file 文件对象
     * @return 采集方式对象
     */
    public static List<CollectMethod> readDatabaseExcel(File file) {
        List<CollectMethod> collectMethods = new ArrayList<>();
        try {
            // 检查文件合法性
            checkFile(file);

            Workbook workbook = getWorkbook(file);

            // 获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetNum; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {continue;}

                // 获取行数
                int rowNum = sheet.getLastRowNum();
                for(int j = 0; j < rowNum + 1; j++){
                    // 跳过第一行
                    if (j == 0) {continue;}
                    Row row = sheet.getRow(j);
                    if (null == row) {continue;}

                    // 获取列数
                    CollectMethod collectMethod = new CollectMethod();
                    int cellNum = row.getLastCellNum();
                    for (int k = 0; k < cellNum; k++) {
                        Cell cell = row.getCell(k);

                        // 获取KPI_ID
                        if (k == 2) {
                            if (null == cell || StringUtils.isEmpty(cell.getStringCellValue())) {break;}
                            collectMethod.setKpiId(cell.getStringCellValue());
                        }

                        // 获取采集方式
                        if (k == 3) {
                            String collectMethodValue = "计算方法：\n" + cell.getStringCellValue();
                            collectMethod.setCollectMethod(collectMethodValue);
                        }
                    }
                    if (StringUtils.isNotEmpty(collectMethod.getKpiId())) {
                        collectMethods.add(collectMethod);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectMethods;
    }

    /**
     * 读取网络设备类文件
     * @param file 文件对象
     * @return 采集方式对象
     */
    public static List<CollectMethod> readNetworkExcel(File file) {
        List<CollectMethod> collectMethods = new ArrayList<>();
        try {
            // 检查文件合法性
            checkFile(file);

            Workbook workbook = getWorkbook(file);

            // 获取sheet数量
            int sheetNum = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetNum; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {continue;}

                // 获取行数
                int rowNum = sheet.getLastRowNum();
                for(int j = 0; j < rowNum + 1; j++){
                    // 跳过第一行
                    if (j == 0) {continue;}
                    Row row = sheet.getRow(j);
                    if (null == row) {continue;}

                    // 获取列数
                    CollectMethod collectMethod = new CollectMethod();
                    int cellNum = row.getLastCellNum();
                    for (int k = 0; k < cellNum; k++) {
                        Cell cell = row.getCell(k);

                        // 获取KPI_ID
                        if (k == 2) {
                            if (null == cell || StringUtils.isEmpty(cell.getStringCellValue())) {break;}
                            collectMethod.setKpiId(cell.getStringCellValue());
                        }

                        // 获取采集方式
                        if (k == 4) {
                            String collectMethodValue = "计算方法：\n" + cell.getStringCellValue();
                            collectMethod.setCollectMethod(collectMethodValue);
                        }
                    }
                    if (StringUtils.isNotEmpty(collectMethod.getKpiId())) {
                        collectMethods.add(collectMethod);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectMethods;
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
