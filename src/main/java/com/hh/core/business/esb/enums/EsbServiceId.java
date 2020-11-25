package com.hh.core.business.esb.enums;


/**
 * Created with IntelliJ IDEA
 * Created By zheng kaifa
 * Data: 2019/5/20
 * Time: 14:41
 */
public enum EsbServiceId {

    /**
     * 三明民生h5
     */
    GRSYBXJF("ylz.ps.fj.si.j.queryPayment2020_1", "失业保险个人缴费信息查询_1"),

    /**
     * 三明人力资源网
     */
    HQDXYZM("ylz.ps.sm.sms.getSmsCode", "获取短信验证码"),
    YZDXYZM("ylz.ps.sm.sms.checkSmsCode", "验证短信验证码"),
    FSPTDX("ylz.ps.sm.sms.sentSmsInfoService", "发送普通短信"),

    /**
     * 福建12333网上仲裁
     */
    CXAJLB("fjjb.ylz.lb.fjldzc.getCaseListInfo", "查询案件列表接口"),
    ZCAJSB("fjjb.ylz.lb.fjldzc.applyArbitration", "仲裁案件申报接口"),
    XGAJXX("fjjb.ylz.lb.fjldzc.submitArbitration", "修改案件信息接口"),
    SJTJ("fjjb.ylz.lb.fjldzc.submitArbitration", "数据提交接口"),
    CXAJXXXX("ylz.lb.fjldzc.getCaseDetailInfo", "查询案件详细信息接口"),
    HQBM("fjjb.ylz.lb.fjldzc.getAA10Info", "获取编码信息"),


    /**
     * 吉林电子社保卡
     */
    SEND_SMS("ylz.jlrs.xxzx.ywsmssend","发送短信");

    /**
     * 接口id
     */
    private final String sid;

    /**
     * 接口描述
     */
    private final String decs;

    EsbServiceId(String sid, String desc){
        this.sid =  sid;
        this.decs = desc;
    }

    public String getSid() {
        return sid;
    }

    public String getDecs() {
        return decs;
    }

}
