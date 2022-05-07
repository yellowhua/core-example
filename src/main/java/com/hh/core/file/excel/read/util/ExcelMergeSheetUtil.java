package com.hh.core.file.excel.read.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <pre>
 * 描述：将多个excel的sheet合并到新的excel的多个sheet中
 * </pre>
 *
 * @类名： com.hh.core.file.excel.read.util.ExcelMergeContentUtil
 * @作者： huanghua
 * @创建日期: 2022/5/7 16:25
 */
public abstract class ExcelMergeSheetUtil {

    /**
     * 将多个excel的sheet合并到新的excel的多个sheet中
     *
     * @param folderPath 目录路径
     * @param targetPath 新的excel路径
     */
    public static void mergeExcel(String folderPath, String targetPath) {
        // 创建新的excel工作簿
        XSSFWorkbook newExcelWorkBook = new XSSFWorkbook();
        // 遍历需要合并的excel文件
        File folder = new File(folderPath);
        for (File file : folder.listFiles()) {
            try (InputStream in = new FileInputStream(file.getPath())) {
                // 创建工作簿
                XSSFWorkbook tmpWorkBook = new XSSFWorkbook(in);
                // 获取工作簿中的Sheet个数
                int len = tmpWorkBook.getNumberOfSheets();
                if (len <= 1) {
                    XSSFSheet tmpSheet = tmpWorkBook.getSheetAt(0);
                    XSSFSheet newExcelSheet = newExcelWorkBook.createSheet(tmpSheet.getSheetName());
                    // 复制sheet内容
                    copyExcelSheet(newExcelWorkBook, tmpSheet, newExcelSheet);
                } else {
                    for (int i = 0; i < len; i++) {
                        XSSFSheet tmpSheet = tmpWorkBook.getSheetAt(i);
                        XSSFSheet newExcelSheet = newExcelWorkBook.createSheet(tmpSheet.getSheetName());
                        // 复制sheet内容
                        copyExcelSheet(newExcelWorkBook, tmpSheet, newExcelSheet);
                    }
                }
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
    public static void copyExcelSheet(XSSFWorkbook workbook, XSSFSheet tmpSheet, XSSFSheet newExcelSheet) {
        // 合并单元格
        mergeSheetAllRegion(tmpSheet, newExcelSheet);
        // 设置单元格列宽度
        // 获取最后一个单元格位置
        int len = tmpSheet.getRow(tmpSheet.getFirstRowNum()).getLastCellNum();
        for (int i = 0; i < len; i++) {
            newExcelSheet.setColumnWidth(i, tmpSheet.getColumnWidth(i));
        }
        // 复制每行内容
        Iterator<Row> it = tmpSheet.iterator();
        while (it.hasNext()) {
            XSSFRow tmpRow = (XSSFRow) it.next();
            // 创建新行
            XSSFRow newExcelRow = newExcelSheet.createRow(tmpRow.getRowNum());
            // 复制行
            copyExcelRow(workbook, tmpRow, newExcelRow);
        }
    }

    /**
     * #合并单元格
     * @param tmpSheet 来源sheet
     * @param newExcelSheet 目标sheet
     */
    private static void mergeSheetAllRegion(XSSFSheet tmpSheet, XSSFSheet newExcelSheet) {
        int num = tmpSheet.getNumMergedRegions();
        CellRangeAddress cellRange = null;
        for (int i = 0; i < num; i++) {
            cellRange = tmpSheet.getMergedRegion(i);
            newExcelSheet.addMergedRegion(cellRange);
        }
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
        Iterator<Cell> it = tmpRow.cellIterator();
        while (it.hasNext()) {
            XSSFCell tmpCell = (XSSFCell) it.next();
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
        mergeExcel("d:\\xyhh\\Desktop\\test", "d:\\xyhh\\Desktop\\test\\merge.xlsx");
    }

}
