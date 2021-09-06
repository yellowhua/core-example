package com.hh.core.business.lyrlzyw.ca.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hh.core.business.lyrlzyw.ca.util.*;
import com.hh.core.business.lyrlzyw.ca.vo.KeyBindVO;
import com.hh.core.business.lyrlzyw.ca.vo.UnitInfoVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hh on 2020/5/19.
 */
@Controller
@RequestMapping(value = "/business/ca")
public class CAController {

    private static final Logger log = LogManager.getLogger(CAController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/login-page")
    public String loginPage(HttpServletRequest request, Model model) {
        fjca.FJCAApps ca = new fjca.FJCAApps();
        ca.setErrorBase(3000);
        ca.setServerURL("202.109.194.213:7000");//设置服务器IP端口
        // 产生随机数,正式的应用，随机数要在服务端产生传给客户端，登陆成功后清空。防止他人恶意登陆。
        String strRandom = ca.FJCA_GenRandom2();
        request.getSession().setAttribute("RANDOM", strRandom);
        model.addAttribute("strRandom", strRandom);
        return "business/ca/login";
    }

    /**
     * 公共服务CA登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginCa", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse loginCa(HttpServletRequest request, HttpServletResponse response) {
        AjaxResponse result = new AjaxResponse();
        KeyBindVO vo = BizFunction.checkCa(request);
        //检验成功,userId统一社会信用代码
        if (vo.getRetCode() == 0) {
            String resultMap = restTemplate.postForObject(Constants.CAConfigUrl.OPERATIONAL_SUPPORT_URL + UrlConfig.Support.KEY_BIND + vo.toAttributeString(), null, String.class);
            log.info("验证证书绑定EDT单位信息:" + Constants.CAConfigUrl.OPERATIONAL_SUPPORT_URL + UrlConfig.Support.KEY_BIND + vo.toAttributeString());
            log.info("返回验证结果:" + resultMap);
            JSONObject keyBindInfo = JSONObject.parseObject(resultMap);
            if (keyBindInfo != null) {
                //成功
                if ("1".equals(keyBindInfo.getString("resultCode"))) {
                    JSONObject resultBody = keyBindInfo.getJSONObject("resultBody");
                    JSONArray rows = resultBody.getJSONArray("rows");
                    if (rows.size() > 0) {
                        result = getUnitInfo(rows, request, "EDT", "福建e点通");
                        return result;
                    } else {
                        log.info("验证证书绑定EDT单位信息为空，获取JYT信息");
                        vo.setPlatform("JYT");
                        resultMap = restTemplate.postForObject(Constants.CAConfigUrl.OPERATIONAL_SUPPORT_URL + UrlConfig.Support.KEY_BIND + vo.toAttributeString(), null, String.class);
                        log.info("验证证书绑定JYT单位信息:" + Constants.CAConfigUrl.OPERATIONAL_SUPPORT_URL + UrlConfig.Support.KEY_BIND + vo.toAttributeString());
                        log.info("返回验证结果:" + resultMap);
                        keyBindInfo = JSONObject.parseObject(resultMap);
                        if (keyBindInfo != null) {
                            //成功
                            if ("1".equals(keyBindInfo.getString("resultCode"))) {
                                resultBody = keyBindInfo.getJSONObject("resultBody");
                                rows = resultBody.getJSONArray("rows");
                                if (rows.size() > 0) {
                                    result = getUnitInfo(rows, request, "JYT", "福建就业通");
                                    return result;
                                } else {
                                    result.setMessage("登录失败,查询的证书未绑定单位信息");
                                    result.setSuccess(false);
                                    return result;
                                }
                            }
                        }
                    }
                } else {
                    result.setMessage("登录失败,查询证书的绑定信息失败");
                    result.setSuccess(false);
                    return result;
                }
            }
            return result;
        } else {
            String msg = vo.getMsg() == null ? "CA校验失败" : vo.getMsg();
            result.setMessage(msg);
            result.setSuccess(false);
            return result;
        }
    }

    public AjaxResponse getUnitInfo(JSONArray rows, HttpServletRequest request, String platform, String platName) {
        AjaxResponse result = new AjaxResponse();
        JSONObject object = rows.getJSONObject(0);
        String userId = object.getString("userId");
        //获取单位信息查询
        if (!StringUtils.isEmpty(userId)) {
            UnitInfoVO unitVo = new UnitInfoVO();
            unitVo.setSecurityCode(MD5Util.MD5("625111833"));
            unitVo.setPlatform(platform);
            unitVo.setUnitNo(userId);
            String unitMap = restTemplate.postForObject(Constants.CAConfigUrl.OPERATIONAL_SUPPORT_URL + UrlConfig.Support.UNIT_INFO + unitVo.toAttributeString(), null, String.class);
            JSONObject unitInfoObj = JSONObject.parseObject(unitMap);
            if (unitInfoObj != null) {
                //请求单位信息成功
                if ("1".equals(unitInfoObj.getString("resultCode"))) {
                    JSONObject unitBody = unitInfoObj.getJSONObject("resultBody");
                    JSONObject unitInfo = unitBody.getJSONObject("unitInfo");

                    try {
                        saveOrUpdateCompanyInfo(object, unitInfo);
                        result.setSuccess(true);
                    } catch (Exception e) {
                        log.error("CA登录保存或更新单位信息失败：" + e.getMessage());
                        e.printStackTrace();
                        result.setMessage("登录失败,保存或更新单位信息失败");
                        result.setSuccess(false);
                    }
                } else {
                    result.setMessage("登录失败,查询单位信息失败");
                    result.setSuccess(false);
                    return result;
                }
            } else {
                result.setMessage("登录失败,查询单位信息失败");
                result.setSuccess(false);
                return result;
            }
        } else {
            result.setMessage("登录失败,查询证书绑定信息失败");
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    private void saveOrUpdateCompanyInfo(JSONObject object, JSONObject unitInfo) {
        // TODO: 2020/5/20 具体的保存或者更新公司信息的业务以及模拟登录
    }

}
