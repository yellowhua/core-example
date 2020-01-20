package com.hh.core.interfacer.resttemplate.jsonparam.controller;

import com.hh.core.interfacer.resttemplate.jsonparam.service.JsonParamService;
import com.hh.core.interfacer.resttemplate.jsonparam.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hh on 2019/5/16.
 * json参数接口controller
 */
@Controller
@RequestMapping(value = "interfacer/json-param")
@Api(description = "json参数接口")
public class JsonParamController {

    private static final Logger logger = LogManager.getLogger(JsonParamController.class);

    @Autowired
    private JsonParamService jsonParamService;

    @ApiOperation(value = "单个参数请求接口")
    @GetMapping(value = "single-param/req")
    @ResponseBody
    public Integer singleParamReq() {
        return jsonParamService.singleParamInvoke();
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
        return jsonParamService.singleObjectInvoke();
    }

    @ApiOperation(value = "单个对象接收接口")
    @PostMapping(value = "single-object/res")
    @ResponseBody
    public Map<String, Object> singleObjectRes(@RequestBody Map<String, Object> paramMap) {
        logger.info("paramMap:{}", paramMap);
        return returnMsg();
    }

    @ApiOperation(value = "对象集合请求接口")
    @GetMapping(value = "list-object/req")
    @ResponseBody
    public Integer listObjectReq() {
        return jsonParamService.listObjectInvoke();
    }

    @ApiOperation(value = "集合对象接收接口")
    @PostMapping(value = "list-object/res")
    @ResponseBody
    public Map<String, Object> listObjectRes(@RequestBody List<Map<String, Object>> params) {
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

    @GetMapping(value = "test")
    @ResponseBody
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "interface call succeeded");
        List<String> list = new ArrayList<>();
        list.add("10001");
        list.add("10002");
        list.add("10003");
        list.add("10004");
        map.put("data", list);
        return map;
    }

}
