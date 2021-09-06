package com.hh.core.business.esb.entity;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * Description:ESBAction解析返回结果
 * </p>
 * <p>
 * Date:2017年6月5日
 * </p>
 * <p>
 * Company:易联众信息技术股份有限公司
 * </p>
 * 
 * @author liuzhipeng
 * @email liuzhipeng@ylzinfo.com
 * @version 1.0
 */
public class EsbResult {
	private boolean resultcode; // 返回编码
	private String resultstring; // 返回信息
	private List<Map<String, String>> content;// 结果集
	private String total;// 总记录数
	private String pdfb64; // 返回pdfb64

	public EsbResult(){}

	public EsbResult(boolean resultcode, String resultstring){
		this.resultcode = resultcode;
		this.resultstring = resultstring;
	}
	public static EsbResult getEmptyResult(String fieldName){
		return new EsbResult(false, fieldName + "不能为空！");
	};
	public static EsbResult getFailResult(String msg){
		return new EsbResult(false, msg);
	};
	public static EsbResult getSuccessResult(){
		return new EsbResult(true, "操作成功！");
	};
	public String getPdfb64() {
		return pdfb64;
	}

	public void setPdfb64(String pdfb64) {
		this.pdfb64 = pdfb64;
	}

	public boolean isResultcode() {
		return resultcode;
	}

	public void setResultcode(boolean resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultstring() {
		return resultstring;
	}

	public void setResultstring(String resultstring) {
		this.resultstring = resultstring;
	}

	public List<Map<String, String>> getContent() {
		return content;
	}

	public void setContent(List<Map<String, String>> content) {
		this.content = content;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
