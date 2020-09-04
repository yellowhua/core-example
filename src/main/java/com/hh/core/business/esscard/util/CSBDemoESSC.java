package com.hh.core.business.esscard.util;

import com.alibaba.csb.sdk.ContentBody;
import com.alibaba.csb.sdk.HttpCaller;
import com.alibaba.csb.sdk.HttpCallerException;
import com.alibaba.csb.sdk.HttpParameters;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟第三方接入渠道调用CSB上发布的API服务
 * @author siniu
 *
 */
public class CSBDemoESSC {
	
	private static final Logger logger = LoggerFactory.getLogger(CSBDemoESSC.class);
	private String csburl="https://ssc.mohrss.gov.cn";
	private String accessKey="bbf57149ebbe4d7dba8a85db6542c048";
	private String securityKey="yBlvU2M4MZGB64yYuuc9tSyP/7I=";

//	private String csburl="https://test-ssc.mohrss.gov.cn/CSB/ecard";
//	private String accessKey="";
//	private String securityKey="";
	//这里填入的accessKey、secretKey并不是阿里云账号（或子账号）的accessKey、secretKey，而是CSB订阅者（即：第三方接入渠道）凭证的accessKey、secretKey
	
	private String api_version="1.0.0";
	private String api_method="post";
	public static int SUCCESS = 200;
	
	public void v1_sign_info(){
		HttpParameters.Builder builder = HttpParameters.newBuilder();
		builder.requestURL(csburl) // 设置请求的URL
				.api("sign_info") // 设置服务名
				.version(api_version) // 设置版本号
				.method(api_method) // 设置调用方式, get/post
				.accessKey(accessKey)
				.secretKey(securityKey); // 设置accessKey 和 设置secretKey
		
		// 设置请求参数（json格式)
		String jsonData="{\"channelNo\":\"1400000001\",\"aab301\":\"\",\"aac002\":\"140311195509213025\",\"aac003\":\"荆爱花\"}";
		ContentBody cb = new ContentBody(jsonData);
		builder.contentBody(cb);
		
		//进行调用 返回结果（json格式)
		String result = null;
		try {
			result = HttpCaller.invoke(builder.build());
			logger.info("================sign_info==================");
			logger.info(result);
			/**返回的result内容如下：
			{"msgCode":700,"msg":"失败","result":null}
			*/
			JSONObject retJson=JSON.parseObject(result);//返回的json对象
			if(retJson.getIntValue("msgCode")==CSBDemoESSC.SUCCESS){//msgCode=200说明返回成功的
				JSONArray resultJson=retJson.getJSONArray("result");
				logger.info(resultJson.toJSONString());
			}
		} catch (HttpCallerException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		CSBDemoESSC _inst=new CSBDemoESSC();
		_inst.v1_sign_info();
	}
	
}
