package com.hh.core.interfacer.resttemplate.jsonparam.service;

/**
 * Created by hh on 2019/5/16.
 * json参数接口service
 */
public interface JsonParamService {

    /**
     * 调用接口，单个参数
     */
    Integer singleParamInvoke();

    /**
     * 调用接口，参数为单个对象
     */
    Integer singleObjectInvoke();

    /**
     * 调用接口，参数为对象集合
     */
    Integer listObjectInvoke();
}
