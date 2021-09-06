package com.hh.core.interfacer.resttemplate.jsonparam.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


/**
 * Created by hh on 2019/5/16.
 * restTemplate的工具类
 * 备注：返回结果的数据类型不仅仅可以是Map，也可以是其他类型。比如是List，那么只要写List.class即可
 */
@Component
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求
     * @param url 请求地址
     * @return 接口返回结果
     */
    public Map get(String url) {
        return restTemplate.getForObject(url, Map.class);
    }

    /**
     * post请求
     * @param url 请求地址
     * @param paramMap 请求参数，单个对象
     * @return 接口返回结果
     */
    public Map postJson(String url, Map<String, Object> paramMap) {
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(paramMap, Constants.jsonHeader());
        return restTemplate.postForObject(url, httpEntity, Map.class);
    }

    /**
     * post请求
     * @param url 请求地址
     * @param paramList 请求参数，对象集合
     * @return 接口返回结果
     */
    public Map postJson(String url, List<Map<String, Object>> paramList) {
        HttpEntity<List<Map<String, Object>>> httpEntity = new HttpEntity<>(paramList, Constants.jsonHeader());
        return restTemplate.postForObject(url, httpEntity, Map.class);
    }

}
