package com.hh.core.interfacer.resttemplate.formparam.service;

import java.io.FileNotFoundException;

/**
 * Created by hh on 2019/5/16.
 * form参数接口service
 */
public interface FormParamService {

    /**
     * 调用接口，单个参数（跟json参数的一样）
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

    /**
     * 文件上传
     */
    Integer uploadFile() throws Exception;
}
