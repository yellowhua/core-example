package com.hh.core.interfacer.resttemplate.jsonparam.service.impl;

import com.hh.core.interfacer.resttemplate.jsonparam.service.JsonParamService;
import com.hh.core.interfacer.resttemplate.jsonparam.util.Constants;
import com.hh.core.interfacer.resttemplate.jsonparam.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hh on 2019/5/16.
 * json参数接口service实现类
 */
@Service
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public Integer singleParamInvoke() {
        String param = "测试";
        Map oneResult = restTemplateUtil.get(Constants.SINGLE_PARAM_RES_ONE_URL + "/" + param);
        Map twoResult = restTemplateUtil.get(Constants.SINGLE_PARAM_RES_TWO_URL + "?param=" + param);
        return (Integer) oneResult.get("code") + (Integer) twoResult.get("code");
    }

    @Override
    public Integer singleObjectInvoke() {
        Map<String, Object> reqParam = structureParam();
        Map resResult = restTemplateUtil.postJson(Constants.SINGLE_OBJECT_RES_URL, reqParam);
        return (Integer) resResult.get("code");
    }

    @Override
    public Integer listObjectInvoke() {
        List<Map<String, Object>> reqParam = structureParams();
        Map resResult = restTemplateUtil.postJson(Constants.LIST_OBJECT_RES_URL, reqParam);
        return (Integer) resResult.get("code");
    }

    /**
     * 准备集合参数
     * @return 接口参数
     */
    private List<Map<String, Object>> structureParams() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", "阿");
        map1.put("b", "哦");
        map1.put("c", "额");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("x", "嗯");
        map2.put("y", "哈");
        map2.put("z", "好");
        list.add(map1);
        list.add(map2);
        return list;
    }

    /**
     * 准备对象参数
     * @return 接口参数
     */
    private Map<String, Object> structureParam() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "阿");
        map.put("b", "哦");
        map.put("c", "额");
        return map;
    }

}
