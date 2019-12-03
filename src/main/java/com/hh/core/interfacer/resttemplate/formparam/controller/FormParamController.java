package com.hh.core.interfacer.resttemplate.formparam.controller;

import com.hh.core.interfacer.resttemplate.formparam.service.FormParamService;
import com.hh.core.interfacer.resttemplate.formparam.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hh on 2019/5/16.
 * form参数接口controller
 */
@Controller
@RequestMapping(value = "interfacer/form-param")
@Api(description = "form参数接口")
public class FormParamController {

    private static final Logger logger = LogManager.getLogger(FormParamController.class);

    @Autowired
    private FormParamService formParamService;

    @ApiOperation(value = "单个参数请求接口")
    @GetMapping(value = "single-param/req")
    @ResponseBody
    public Integer singleParamReq() {
        return formParamService.singleParamInvoke();
    }

    @ApiOperation(value = "单个参数接收接口一")
    @GetMapping(value = "single-param/res/one/{param}")
    @ResponseBody
    public Map<String, Object> singleParamResOne(@PathVariable String param) {
        logger.info(param);
        return returnMsg();
    }

    @ApiOperation(value = "单个参数接收接口二")
    @GetMapping(value = "single-param/res/two")
    @ResponseBody
    public Map<String, Object> singleParamResTwo(@RequestParam String param) {
        logger.info(param);
        return returnMsg();
    }

    @ApiOperation(value = "单个对象请求接口")
    @GetMapping(value = "single-object/req")
    @ResponseBody
    public Integer singleObjectReq() {
        return formParamService.singleObjectInvoke();
    }

    @ApiOperation(value = "单个对象接收接口")
    @PostMapping(value = "single-object/res")
    @ResponseBody
    public Map<String, Object> singleObjectRes(@RequestParam Map<String, Object> paramMap) {
        logger.info("paramMap:{}", paramMap);
        return returnMsg();
    }

    @ApiOperation(value = "对象集合请求接口")
    @GetMapping(value = "list-object/req")
    @ResponseBody
    public Integer listObjectReq() {
        return formParamService.listObjectInvoke();
    }

    @ApiOperation(value = "集合对象接收接口（未完成）")
    @PostMapping(value = "list-object/res")
    @ResponseBody
    public Map<String, Object> listObjectRes(List<Map<String, Object>> params) {
        logger.info(params.toString());
        return returnMsg();
    }

    /**
     * 准备返回参数
     * @return 返回参数
     */
    private Map<String, Object> returnMsg() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", Constants.CODE_SUCCESS);
        resultMap.put("msg", Constants.MSG_SUCCESS);
        return resultMap;
    }

}
