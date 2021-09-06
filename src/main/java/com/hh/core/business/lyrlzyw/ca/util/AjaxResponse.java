package com.hh.core.business.lyrlzyw.ca.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有属性success、message、data，该对象只是一个约定的返回到前台的json对象，按该对象设置可基本满足需求，
 * 若不满足需求可自行返回json对象
 * <p>
 * success 表示操作是否成功
 * </p>
 * <p>
 * message 提示的信息
 * </p>
 * <p>
 * data 要返回的数据
 * </p>
 * 
 * @author liuzhipeng
 * @since 1.1
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class AjaxResponse implements Serializable {
	/**
	 * 该请求是否成功
	 */
	private Boolean success;
	/**
	 * 请求提示
	 */
	private String message;

	private List data;
	
	private Map<String,Object> mapData;

	/**
	 * 用户登录验证标识
	 */
	private Boolean authentic = Boolean.TRUE;

	/**
	 * 创建一个新的{@link AjaxResponse}，success为true,message为操作成功
	 */
	public AjaxResponse() {
		this(Boolean.TRUE, "操作成功");
	}

	/**
	 * 创建一个新的{@link AjaxResponse}
	 * 
	 * @param success
	 */
	public AjaxResponse(Boolean success) {
		this(success, null);
	}

	/**
	 * 创建一个新的{@link AjaxResponse}，success为true
	 * 
	 * @param message
	 */
	public AjaxResponse(String message) {
		this(Boolean.TRUE, "操作成功");
	}

	/**
	 * 创建一个新的{@link AjaxResponse}
	 * 
	 * @param success
	 * @param message
	 */
	public AjaxResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
		if (this.message == null) {
			if (Boolean.FALSE.equals(success)) {
				this.message = "操作失败";
			}
			if (Boolean.TRUE.equals(success)) {
				this.message = "操作成功";
			}
		}
		this.data = new ArrayList();
	}

	/**
	 * 创建一个新的{@link AjaxResponse}，success为false
	 * 
	 * @return
	 */
	public static AjaxResponse fail() {
		return fail(null);
	}

	/**
	 * 创建一个新的{@link AjaxResponse}，success为false
	 * 
	 * @param message 消息
	 * @return
	 */
	public static AjaxResponse fail(String message) {
		return new AjaxResponse(Boolean.FALSE, message);
	}

	/**
	 * 创建一个新的{@link AjaxResponse}，success为true
	 * 
	 * @return
	 */
	public static AjaxResponse success() {
		return success(null);
	}

	/**
	 * 创建一个新的{@link AjaxResponse}，success为true
	 * 
	 * @param message 消息
	 * @return
	 */
	public static AjaxResponse success(String message) {
		return new AjaxResponse(Boolean.TRUE, message);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List getData() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public void setData(List data) {
		// 防止返回一个空map时分页出错，清空map
		if (data != null && data.size() == 1) {
			Object obj = data.get(0);
			if (obj instanceof HashMap) {
				HashMap<String, String> map = (HashMap<String, String>) data.get(0);
				if (map.size() == 0) {
					data.clear();
				}
			}
		}
		this.data = data;
	}

	public Boolean getAuthentic() {
		return authentic;
	}

	public void setAuthentic(Boolean authentic) {
		this.authentic = authentic;
	}

	public Map<String, Object> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}
	
	
}
