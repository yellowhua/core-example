package com.hh.core.business.lyrlzyw.ca.vo;

/**
 * 单位信息查询参数
 * @author ChenDang
 * @date 2019/7/9 0009
 */
public class UnitInfoVO {

    private String unitNo;
    private String password;
    private String securityCode;
    private String platform;

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String toAttributeString() {
        return "?unitNo="+this.unitNo+"&password="+this.password+"&securityCode="+this.securityCode+"&platform="+this.platform;
    }
}
