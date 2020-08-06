package com.hh.core.business.knowledge.util;

import com.hh.core.business.knowledge.domain.KnowledgeBase;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by hh on 2019/6/17.
 * excel导出工具类
 */
public class KnowledgeExcelExportUtil {

    public static void exportExcel(OutputStream out, List<KnowledgeBase> knowledgeBases) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("知识库");

        // 标题
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("问题");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("答案");

        // 创建3行
        for (int i = 0; i < knowledgeBases.size(); i++) {
            row = sheet.createRow(i + 1);
            cell0 = row.createCell(0);
            cell0.setCellValue(knowledgeBases.get(i).getKnowledgeTitle());
            cell1 = row.createCell(1);
            cell1.setCellValue(knowledgeBases.get(i).getKnowledgeContent());
        }

        workbook.write(out);
    }


}