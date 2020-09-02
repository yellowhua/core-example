package com.hh.core.business.lyrlzyw.ca.vo;

/**
 * 获取证书的绑定信息查询参数
 * @author ChenDang
 * @date 2019/7/9 0009
 */
public class KeyBindVO {

    private String serialNumber;
    private String uniqueCode;
    private String platform;
    private String userId;
    private String keyName;
    private int retCode;
    private String msg;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toAttributeString() {
        return  "?serialNumber="+this.getSerialNumber()+"&uniqueCode="+this.getUniqueCode()+"&platform="+this.getPlatform();
    }
}
