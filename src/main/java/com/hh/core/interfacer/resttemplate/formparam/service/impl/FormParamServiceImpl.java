package com.hh.core.interfacer.resttemplate.formparam.service.impl;

import com.hh.core.interfacer.resttemplate.formparam.service.FormParamService;
import com.hh.core.interfacer.resttemplate.formparam.util.Constants;
import com.hh.core.interfacer.resttemplate.formparam.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by hh on 2019/5/16.
 * json参数接口service实现类
 */
@Service
public class FormParamServiceImpl implements FormParamService {

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
        MultiValueMap<String, Object> reqParam = structureParam();
        Map resResult = restTemplateUtil.postForm(Constants.SINGLE_OBJECT_RES_URL, reqParam);
        return (Integer) resResult.get("code");
    }

    @Override
    public Integer listObjectInvoke() {
        List<MultiValueMap<String, Object>> reqParam = structureParams();
        Map resResult = restTemplateUtil.postForm(Constants.LIST_OBJECT_RES_URL, reqParam);
        return (Integer) resResult.get("code");
    }

    /**
     * 准备集合参数
     * @return 接口参数
     */
    private List<MultiValueMap<String, Object>> structureParams() {
        List<MultiValueMap<String, Object>> list = new ArrayList<>();
        MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
        map1.add("a", "阿");
        map1.add("b", "哦");
        map1.add("c", "额");
        MultiValueMap<String, Object> map2 = new LinkedMultiValueMap<>();
        map2.add("x", "嗯");
        map2.add("y", "哈");
        map2.add("z", "好");
        list.add(map1);
        list.add(map2);
        return list;
    }

    /**
     * 准备对象参数
     * @return 接口参数
     */
    private MultiValueMap<String, Object> structureParam() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("a", "阿");
        map.add("b", "哦");
        map.add("c", "额");
        return map;
    }

}
