package com.hh.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hh on 2018/5/29.
 * core-file
 */
@Controller
@RequestMapping(value = "/file")
@Api(description = "编写一些与文件相关的例子")
public class FileController {

    @ApiOperation(value = "进入文件页面")
    @RequestMapping(value = "/list")
    public String list() {
        return "/file/list";
    }

    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload() {
        System.out.println(1);
        return "aaa";
    }

}
