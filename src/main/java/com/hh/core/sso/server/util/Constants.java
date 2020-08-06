package com.hh.core.sso.server.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * create by hh on 2020/06/02
 * 单点登录常量类
 */
public class Constants {

	public static final Integer CODE_SUCCESS = 1;

	/**
	 * json请求头
	 */
	public static HttpHeaders jsonHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
		return headers;
	}

}