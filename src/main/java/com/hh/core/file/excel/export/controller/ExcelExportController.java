package com.hh.core.file.excel.export.controller;

import com.hh.core.file.excel.export.util.ExcelExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping(value = "/excel")
public class ExcelExportController {

    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        String header = "attachment; filename = aaa.xlsx";
        response.setHeader("Content-Disposition", header);
        response.setContentType("APPLICATION/OCTET-STREAM");
        OutputStream out = response.getOutputStream();
        ExcelExportUtil.exportExcel(out);
    }
}
