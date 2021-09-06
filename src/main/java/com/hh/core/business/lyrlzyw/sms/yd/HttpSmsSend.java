package com.hh.core.business.lyrlzyw.sms.yd;

import com.alibaba.fastjson.JSONObject;
import com.hh.core.business.lyrlzyw.ca.util.MD5Util;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


/**
 * 移动短信http调用
 *
 * @author huanghua
 * @date 2020/08/13
 */
@Component
public class HttpSmsSend {

	@Autowired
	private RestTemplate restTemplate;

	public void sendSms() {
		String ecName = "龙岩市社会保障卡服务中心";
		String apId = "rlzycs";
		String secretKey = "rlzycs350800";
		String mobiles = "18965082080";
		String content = "测试";
		String sign = "HyOXuWMDC";
		String addSerial = "";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		JSONObject requestBody = new JSONObject();
		requestBody.put("ecName", ecName);
		requestBody.put("apId", apId);
		requestBody.put("mobiles", mobiles);
		requestBody.put("content", content);
		requestBody.put("sign", sign);
		requestBody.put("addSerial", addSerial);
		requestBody.put("mac", MD5Util.MD5(ecName + apId + secretKey + mobiles + content + sign + addSerial));
		System.out.println(Base64.encodeBase64String(requestBody.toString().getBytes(StandardCharsets.UTF_8)));
		HttpEntity<String> entity = new HttpEntity<>(Base64.encodeBase64String(requestBody.toString().getBytes()), headers);
		JSONObject jsonObject = restTemplate.postForObject("http://112.35.1.155:1992/sms/norsubmit", entity, JSONObject.class);
		System.out.println(jsonObject);
	}
}
