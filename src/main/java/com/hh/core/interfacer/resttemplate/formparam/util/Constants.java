package com.hh.core.interfacer.resttemplate.formparam.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by hh on 2019/5/16.
 * 常量类
 */
public class Constants {

    /**
     * json请求头
     */
    public static HttpHeaders formHeader() {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        headers.setContentType(type);
        return headers;
    }

    /**
     * 单个参数的响应地址一
     */
    public static final String SINGLE_PARAM_RES_ONE_URL
            = "http://localhost:8080/interfacer/form-param/single-param/res/one";

    /**
     * 单个参数的响应地址二
     */
    public static final String SINGLE_PARAM_RES_TWO_URL
            = "http://localhost:8080/interfacer/form-param/single-param/res/two";

    /**
     * 单个对象的响应地址
     */
    public static final String SINGLE_OBJECT_RES_URL
            = "http://localhost:8080/interfacer/form-param/single-object/res";

    /**
     * 对象集合的响应地址
     */
    public static final String LIST_OBJECT_RES_URL
            = "http://localhost:8080/interfacer/form-param/list-object/res";

    /**
     * 响应返回参数
     */
    public static final Integer CODE_SUCCESS = 200;
    public static final String MSG_SUCCESS = "接口调用成功";

}
