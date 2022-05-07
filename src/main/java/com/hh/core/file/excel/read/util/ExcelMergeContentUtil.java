package com.hh.core.file.excel.read.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * <pre>
 * 描述：将多个excel的内容合并到新的excel的一个sheet中
 * </pre>
 *
 * @类名： com.hh.core.file.excel.read.util.ExcelMergeContentUtil
 * @作者： huanghua
 * @创建日期: 2022/5/7 16:25
 */
@Slf4j
public class ExcelMergeContentUtil {

    /**
     * 将多个excel的内容合并到新的excel的一个sheet中
     *
     * @param folderPath 目录路径
     * @param targetPath 新的excel路径
     */
    public static void mergeExcel(String folderPath, String targetPath) {
        // 创建新的excel工作簿
        XSSFWorkbook newExcelWorkBook = new XSSFWorkbook();
        XSSFSheet newExcelSheet = newExcelWorkBook.createSheet("sheet名称");
        int totalNum = 0;

        File folder = new File(folderPath);
        for (File file : folder.listFiles()) {
            try (InputStream in = new FileInputStream(file.getPath())) {
                // 创建工作簿
                XSSFWorkbook tmpWorkBook = new XSSFWorkbook(in);
                XSSFSheet tmpSheet = tmpWorkBook.getSheetAt(0);
                // 复制sheet内容
                totalNum = copyExcelSheet(newExcelWorkBook, tmpSheet, newExcelSheet, totalNum);
                // 关闭tmpWorkBook工作簿
                tmpWorkBook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 使用输出流写出
        try (FileOutputStream fos = new FileOutputStream(targetPath)) {
            newExcelWorkBook.write(fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                newExcelWorkBook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("excel文件合并成功，合并后文件路径：" + targetPath);
    }

    /**
     * #复制sheet到新的excel文件中
     * @param workbook excel工作簿
     * @param tmpSheet 来源sheet
     * @param newExcelSheet 新生成的sheet
     */
    public static int copyExcelSheet(XSSFWorkbook workbook, XSSFSheet tmpSheet, XSSFSheet newExcelSheet, int totalNum) {
        // 复制每行内容
        for (int i = 0; i < tmpSheet.getLastRowNum() + 1; i++) {
            XSSFRow tmpRow = tmpSheet.getRow(i);
            if (null == tmpRow) {continue;}
            // 创建新行
            XSSFRow newExcelRow = newExcelSheet.createRow(totalNum);
            // 复制行
            copyExcelRow(workbook, tmpRow, newExcelRow);
            // 总行数加1
            totalNum++;
        }
        return totalNum;
    }

    /**
     * #复制excel中的行到新的sheet中
     * @param workbook 目标工作簿
     * @param tmpRow 来源excel行
     * @param newExcelRow 目标excel行
     */
    public static void copyExcelRow(XSSFWorkbook workbook, XSSFRow tmpRow, XSSFRow newExcelRow) {
        // 设置行高
        newExcelRow.setHeight(tmpRow.getHeight());
        // 获取所有列
        for (int i = 0; i < tmpRow.getLastCellNum(); i++) {
            XSSFCell tmpCell = tmpRow.getCell(i);
            if (null == tmpCell) {continue;}
            // 创建单元格
            XSSFCell newExcelCell = newExcelRow.createCell(tmpCell.getColumnIndex());
            // 复制单元格
            copyExcelCell(workbook, tmpCell, newExcelCell);
        }
    }

    /**
     * #复制单元格
     * @param workbook 目标工作簿
     * @param tmpCell 来源excel单元格
     * @param newExcelCell 目标excel单元格
     */
    public static void copyExcelCell(XSSFWorkbook workbook, XSSFCell tmpCell, XSSFCell newExcelCell) {
        XSSFCellStyle newExcelStyle = workbook.createCellStyle();
        // 复制单元格样式
        newExcelStyle.cloneStyleFrom(tmpCell.getCellStyle());
        // 单元格样式
        newExcelCell.setCellStyle(newExcelStyle);
        if (tmpCell.getCellComment() != null) {
            newExcelCell.setCellComment(tmpCell.getCellComment());
        }
        // 不同数据类型处理
        CellType tmpCellType = tmpCell.getCellType();
        newExcelCell.setCellType(tmpCellType);
        if (tmpCellType == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(tmpCell)) {
                newExcelCell.setCellValue(tmpCell.getDateCellValue());
            } else {
                newExcelCell.setCellValue(tmpCell.getNumericCellValue());
            }
        } else if (tmpCellType == CellType.STRING) {
            newExcelCell.setCellValue(tmpCell.getRichStringCellValue());
        } else if (tmpCellType == CellType.BLANK) {
        } else if (tmpCellType == CellType.BOOLEAN) {
            newExcelCell.setCellValue(tmpCell.getBooleanCellValue());
        } else if (tmpCellType == CellType.ERROR) {
            newExcelCell.setCellErrorValue(tmpCell.getErrorCellValue());
        } else if (tmpCellType == CellType.FORMULA) {
            newExcelCell.setCellFormula(tmpCell.getCellFormula());
        } else {
        }
    }

    public static void main(String[] args) {
        mergeExcel("d:\\xyhh\\Desktop\\2022.5.7海峡旅游大厦塔楼人员信息", "d:\\xyhh\\Desktop\\test\\merge.xlsx");
    }

}
