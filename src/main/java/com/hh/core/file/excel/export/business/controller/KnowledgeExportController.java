package com.hh.core.file.excel.export.business.controller;

import com.hh.core.file.excel.export.business.repository.KnowledgeBaseRepository;
import com.hh.core.file.excel.export.business.domain.KnowledgeBase;
import com.hh.core.file.excel.export.business.util.KnowledgeExcelExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/excel/knowledge")
public class KnowledgeExportController {

    @Autowired
    private KnowledgeBaseRepository knowledgeBaseRepository;

    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        String excelName = new String("知识库.xlsx".getBytes(), "ISO8859-1");
        String header = "attachment; filename = " + excelName;
        response.setHeader("Content-Disposition", header);
        response.setContentType("APPLICATION/OCTET-STREAM");
        OutputStream out = response.getOutputStream();

        List<KnowledgeBase> knowledgeBases = knowledgeBaseRepository.findAll();
        KnowledgeExcelExportUtil.exportExcel(out, knowledgeBases);
    }
}
