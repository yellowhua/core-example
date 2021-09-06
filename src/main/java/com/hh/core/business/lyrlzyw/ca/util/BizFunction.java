package com.hh.core.business.lyrlzyw.ca.util;

import com.hh.core.business.lyrlzyw.ca.vo.KeyBindVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理证书业务的方法
 * @author ChenDang
 * @date 2019/7/8 0008
 */
public class BizFunction {

    /**
     * 校验Ca
     *
     * @param request
     * @return
     */
    public static KeyBindVO checkCa(HttpServletRequest request) {
        KeyBindVO vo = new KeyBindVO();
        // 证书信息号
        String textCert = request.getParameter("textCertData");
        // 原文信息(随机数)
        String textOriginData = request.getParameter("textOriginData");
        // 签名信息
        String textSignData = request.getParameter("textSignData");
        fjca.FJCAApps ca = new fjca.FJCAApps();
        fjca.FJCAApps.setErrorBase(4000);
        //设置服务器IP端口
        fjca.FJCAApps.setServerURL("202.109.194.213:7001");
        //1.证书名称
        String keyName = "";
        //2.获取证书唯一码
        String strUniqueCode = "";
        //3.登录认证,统一社会信用代码
        String userId = "";
        byte[] bySubjectCN = ca.GetSubjectCN(textCert);
        try {
            keyName = new String(bySubjectCN, "GBK");
            strUniqueCode = ca.getExtensions(textCert.getBytes(), "1.2.156.112578.1");
        } catch (Exception e) {
            throw new RuntimeException("无法解析证书名称");
        }
        // 对企业进行身份认证（HTTP环境下的数字证书认证）
        // 取证书序列号
        String serialNumber = ca.getSerialFromCert(textCert);
        String strRet = ca.FJCA_VerifyQY(textOriginData, textSignData, textCert);
        int retCode = ca.getLastError();
        //retCode ==0 表示验证成功
        if (retCode == 0) {
            String[] retArr = strRet.split(",");
            userId = retArr[2].replace(" ", "");
            vo.setKeyName(keyName);
            //证书唯一码
            vo.setPlatform("EDT");
            vo.setSerialNumber(serialNumber);
            vo.setUserId(userId);
            vo.setUniqueCode(strUniqueCode);
        }else{
            vo.setMsg(CAErrorConstant.CaEerror(retCode));
        }
        vo.setRetCode(retCode);
        return vo;
    }
}
