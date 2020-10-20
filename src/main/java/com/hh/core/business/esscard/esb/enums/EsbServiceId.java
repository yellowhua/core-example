package com.hh.core.business.esscard.esb.enums;

/**
 * Created with IntelliJ IDEA
 * Created By zheng kaifa
 * Data: 2019/5/20
 * Time: 14:41
 */
public enum EsbServiceId {
	
	/**
	 * 人事人才
	 * */
	RSRC_QUERYPROTITLE("ylz.ps.fj.si.lb.queryProTitle","职称查询"),
	
	
	/**
	 * 就业
	 * */
	JY_QUERYARCHIVES("ylz.rlzyw.sm.queryCc10","档案查询"),

    QUERY_GW("ylz.jlrs.xxzx.ywsmssend","搜索岗位列表信息"),

    /**
     * 社保卡
     */
    SC_GetCardInfo_SSCARD("SC_GetCardInfo_SSCARD","获取社保卡信息"),
    SC_GetBank_SSCARD("SC_GetBank_SSCARD","查询银行信息"),
    SC_GetCardPlace_SSCARD("SC_GetCardPlace_SSCARD","查询领卡网点"),
    SC_IfDifferentPlace_SSCARD("SC_IfDifferentPlace_SSCARD","是否有异地卡校验"),
    SC_GetCardMap_SSCARD("SC_GetCardMap_SSCARD","补换卡条件校验"),
    SC_ReplaceCard_SSCARD("SC_ReplaceCard_SSCARD","补换卡申请"),
    SC_GetPersonInfo_SSCARD("SC_GetPersonInfo_SSCARD","获取人员信息"),
    SC_CardLose_SSCARD("SC_CardLose_SSCARD","社保卡挂失"),
    SC_CardStart_External("SC_CardStart_External_h5","社保卡解挂"),
    SC_CardStop_SSCARD("SC_CardStop_SSCARD","社保卡停用"),
    SC_CardStart2_External("SC_CardStart2_External","社保卡启用"),
	
	/**
   * 公共服务
   * */
	GGFW_GETPUBLICKEY("com.ylz.ps.cm.getpublicKey", "获取公共key"),
	GGFW_PSERSON_LOGIN("ylz.ps.cm.login.personLogin", "个人登录"),
	  /**
     * 综合服务
     */
        /**
         * 个人基本信息
         */
    ZHFW_GRJBXX_GRJBXXCX("ylz.ps.cm.base.queryPersonInfo", "查询个人基本信息"),
    ZHFW_GRJBXX_GRTXXXCX("ylz.ps.cm.base.queryPersonalInfo", "查询个人通讯信息（通讯地址、邮政编码）"),
    ZHFW_GRJBXX_GRTXXXXG("ylz.ps.cm.base.personalChangeInfo", "修改个人通讯信息（通讯地址、邮政编码）"),
    ZHFW_GRJBXX_XGSJHM("ylz.ps.cm.base.changeTele", "修改绑手机号码"),
        /**
         *  短信验证
         */
    ZHFW_DXYZ_HQDXYZM("ylz.ps.cm.sms.getSmsCode", "获取短信验证码"),
    ZHFW_DXYZ_DXYZMSFYX("ylz.ps.cm.sms.checkSmsCode", "校验短信验证码是否有效"),

    /*
     *  劳动就业
     */
    LDJY_GRXX_GRJSYDJXX("ylz.ps.fj.si.queryInsu", "个人就失业登记信息"),
    LDJY_GRXX_JYFCZCXSQK("ylz.ps.fj.lb.querySustain", "就业扶持政策享受情况"),

    /**
     * 社会保险
     */
    SHBX_CBGLFW_GRCBXXCX("ylz.ps.fj.si.queryInsu", "查询个人参保信息，可按险种查询，也可以查询参保的所有险种信息。"),

    SHBX_CZZGJBYLBX_GRYLZHCX("ylz.ps.fj.si.i.queryAccount", "查询企业职工基本养老保险的个人账户信息"),

    SHBX_CZZGJBYLBX_YLJFFQKCX("ylz.ps.fj.si.i.queryRealPay", "查询企业职工养老保险的养老金发放记录"),

    SHBX_CZZGJBYLBX_TXZGXX("ylz.ps.fj.si.i.queryBenefit", "查询人员退休信息"),

    SHBX_CZZGJBYLBX_GRCBXXCX("ylz.ps.fj.si.i.queryInsu110", "查询个人参保信息，可按险种查询，也可以查询参保的所有险种信息"),

    SHBX_CZZGJBYLBX_JFHBXXCX("ylz.ps.fj.si.i.queryFund110", "查询个人险种缴费信息，可按险种查询缴费信息，也可查询所有险种的缴费信息"),

    SHBX_CZZGJBYLBX_RYJBXXCX("ylz.ps.fj.si.i.queryBaseInfo110", "查询人员基本信息"),

    SHBX_CZZGJBYLBX_JFLCCX("ylz.ps.fj.si.i.queryPayHistory110", "查询缴费历程");







    private String sid;
    private String decs;//描述
    private EsbServiceId(String sid, String desc){
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
