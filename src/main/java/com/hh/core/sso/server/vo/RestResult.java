package com.hh.core.sso.server.vo;

import lombok.Data;

import java.util.Map;

/**
 * Created by hh on 2020/6/2.
 * 接口返回数据
 */
@Data
public class RestResult {

    private Integer code;

    private String msg;

    private Map<String, Object> data;

    private static final Integer CODE_SUCCESS = 0;

    private static final Integer CODE_FAIL = 1;

    public RestResult() {}

    public RestResult(Integer code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResult success(String msg, Map<String, Object> data) {
        return new RestResult(CODE_SUCCESS, msg, data);
    }

    public static RestResult fail(String msg, Map<String, Object> data) {
        return new RestResult(CODE_FAIL, msg, data);
    }

}
