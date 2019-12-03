package com.hh.core.interfacer.resttemplate.formparam.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


/**
 * Created by hh on 2019/5/16.
 * restTemplate的工具类（重命名bean的名称为formRestTemplate是为了防止和jsonparam包下的bean冲突）
 */
@Component(value = "formRestTemplate")
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
    public Map postForm(String url, MultiValueMap<String, Object> paramMap) {
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, Constants.formHeader());
        return restTemplate.postForObject(url, httpEntity, Map.class);
    }

    /**
     * post请求
     * @param url 请求地址
     * @param paramList 请求参数，对象集合
     * @return 接口返回结果
     */
    public Map postForm(String url, List<MultiValueMap<String, Object>> paramList) {
        HttpEntity<List<MultiValueMap<String, Object>>> httpEntity = new HttpEntity<>(paramList, Constants.formHeader());
        return restTemplate.postForObject(url, httpEntity, Map.class);
    }

}
