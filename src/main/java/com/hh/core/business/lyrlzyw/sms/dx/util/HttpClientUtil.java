package com.hh.core.business.lyrlzyw.sms.dx.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: Http客户端工具类
 * @copyright: Copyright (c) 2011 FFCS All Rights Reserved
 * @company: 北京福富软件有限公司福州分公司
 * @author 吴鼎良 2011-12-26
 * @version 1.00.00
 * @history:
 * 
 */
public class HttpClientUtil {
	//编码
	private static String CHARSET = "UTF-8";
	//连接超时时间
	private static int TIME_OUT = 60000;

	public static String get(String url, String param, String content) {
		Validate.notEmpty(url);
		Validate.notEmpty(param);
		Validate.notEmpty(content);

		String result = "";
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// set proxy
		//if(Constants.IS_PROXY) client.getHostConfiguration().setProxy(Constants.PROXY_HOST, Constants.PROXY_PORT);
		// set charset
		client.getParams().setParameter("http.protocol.content-charset", CHARSET);
		// set http client timeout
		client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);
		// Create a method instance.
		GetMethod getMethod = new GetMethod(url);
		// Provide custom retry handler is necessary
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
			new DefaultHttpMethodRetryHandler(3, false));
		getMethod.addRequestHeader("Content-type", "text/html; charset=" + CHARSET);
		// set get method timeout
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);
		// set query string
		NameValuePair requestData = new NameValuePair(param, content);
		getMethod.setQueryString(new NameValuePair[] { requestData });

		try {
			// Execute the method.
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new RuntimeException("Bad response status, " + getMethod.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = getMethod.getResponseBody();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			result = new String(responseBody);

		} catch (HttpException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// Release the connection.
			getMethod.releaseConnection();
		}

		return result;
	}
	
	/**
	 * @description: Post方式提交
	 * @author: 吴鼎良 Dec 26, 2011
	 * @param url
	 * @param param
	 * @param content
	 * @return String
	 */
	public static String post(String url, String param, String content){
		Validate.notEmpty(url);
		Validate.notEmpty(param);
		Validate.notEmpty(content);
		
		String result = "";
		HttpClient client = new HttpClient();
		// set proxy
     //		if(Constants.IS_PROXY){
//			HttpUtil.setHttpProxy(Constants.PROXY_HOST, String.valueOf(Constants.PROXY_PORT));
//			client.getHostConfiguration().setProxy(Constants.PROXY_HOST, Constants.PROXY_PORT);
//		}
		// set http client charset
		client.getParams().setParameter("http.protocol.content-charset", CHARSET);
		// set http client timeout
		client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);  

		PostMethod postMethod = new PostMethod(url);
		//在协议出现异常或网络问题的情况下重发三次
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		
		postMethod.addRequestHeader("Content-type" , "application/x-www-form-urlencoded; charset=" + CHARSET);
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);
		NameValuePair requestData = new NameValuePair(param, content);
		NameValuePair[] params = new NameValuePair[]{ requestData };
		postMethod.setRequestBody(params);
		
	    
		//postMethod.setQueryString(new NameValuePair[] { requestData });
		InputStream in = null;
		 BufferedReader buffer = null;
		
		try {
			// Execute the method			
			int statusCode = client.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new RuntimeException("Bad response status, " + postMethod.getStatusLine());
			}
			
			// Read the response body.
			/*byte[] responseBody = getMethod.getResponseBody();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			result = new String(responseBody);*/
			/*byte[] responseBody = postMethod.getResponseBody();
			
			result = new String(responseBody);*/
			
            StringBuffer temp = new StringBuffer();
            in = postMethod.getResponseBodyAsStream();
            buffer = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            for(String tempstr = ""; (tempstr = buffer.readLine()) != null;)
                temp = temp.append(tempstr);
            
            result = temp.toString().trim();

		} catch (HttpException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(buffer != null) buffer.close();
				if(in != null) in.close();
				if(postMethod != null) postMethod.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

	/**
	 *
	 * @param url
	 * @param postParams
	 * @return
	 */
	public static String post(String url, Map<String,String> postParams){
		Validate.notEmpty(url);

		String result = "";
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.content-charset", CHARSET);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);

		PostMethod postMethod = new PostMethod(url);
		//在协议出现异常或网络问题的情况下重发三次
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		postMethod.addRequestHeader("Content-type" , "application/x-www-form-urlencoded; charset=" + CHARSET);
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> stringStringEntry : postParams.entrySet()) {
			NameValuePair requestData = new NameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue());
			params.add(requestData);
		}

		postMethod.setRequestBody(params.toArray(new NameValuePair[0]));


		//postMethod.setQueryString(new NameValuePair[] { requestData });
		InputStream in = null;
		BufferedReader buffer = null;

		try {
			// Execute the method
			int statusCode = client.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new RuntimeException("Bad response status, " + postMethod.getStatusLine());
			}

			// Read the response body.
			/*byte[] responseBody = getMethod.getResponseBody();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			result = new String(responseBody);*/
			/*byte[] responseBody = postMethod.getResponseBody();

			result = new String(responseBody);*/

			StringBuffer temp = new StringBuffer();
			in = postMethod.getResponseBodyAsStream();
			buffer = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			for(String tempstr = ""; (tempstr = buffer.readLine()) != null;)
				temp = temp.append(tempstr);

			result = temp.toString().trim();

		} catch (HttpException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(buffer != null) buffer.close();
				if(in != null) in.close();
				if(postMethod != null) postMethod.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return result;
	}

	public static String post(String url,Map<String,String> headers, Map<String,String> postParams){
		Validate.notEmpty(url);

		String result = "";
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.content-charset", CHARSET);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);

		PostMethod postMethod = new PostMethod(url);
		//在协议出现异常或网络问题的情况下重发三次
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		postMethod.addRequestHeader("Content-type" , "application/x-www-form-urlencoded; charset=" + CHARSET);
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> stringStringEntry : postParams.entrySet()) {
			NameValuePair requestData = new NameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue());
			params.add(requestData);
		}

		postMethod.setRequestBody(params.toArray(new NameValuePair[0]));


		//postMethod.setQueryString(new NameValuePair[] { requestData });
		InputStream in = null;
		BufferedReader buffer = null;

		try {
			// Execute the method
			int statusCode = client.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new RuntimeException("Bad response status, " + postMethod.getStatusLine());
			}

			// Read the response body.
			/*byte[] responseBody = getMethod.getResponseBody();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			result = new String(responseBody);*/
			/*byte[] responseBody = postMethod.getResponseBody();

			result = new String(responseBody);*/

			StringBuffer temp = new StringBuffer();
			in = postMethod.getResponseBodyAsStream();
			buffer = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			for(String tempstr = ""; (tempstr = buffer.readLine()) != null;)
				temp = temp.append(tempstr);

			result = temp.toString().trim();

		} catch (HttpException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(buffer != null) buffer.close();
				if(in != null) in.close();
				if(postMethod != null) postMethod.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return result;
	}


	
}
