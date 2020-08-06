package com.hh.core.business.lyrlzyw.sms.dx.controller;

import com.hh.core.business.lyrlzyw.sms.dx.util.Constants;
import com.hh.core.business.lyrlzyw.sms.dx.util.RandomUtil;
import com.hh.core.business.lyrlzyw.sms.dx.util.SendSmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "sms/dx")
public class SmsDXController {

    private static final Logger logger = LogManager.getLogger(SmsDXController.class);

    @GetMapping(value = "/index")
    public String index() {
        return "business/sms/dx";
    }

    @GetMapping
    @ResponseBody
    public Map<String, Object> getSmskey(String iphone, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            String smskey = RandomUtil.getRandomPwd(6);
            String content = "短信验证码为:" + smskey;
            SendSmsUtil sendSmsUtil = new SendSmsUtil();
            /*String result = sendSmsUtil.sendTemplateSms(iphone, content);
            logger.info("result:{}", result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String code = jsonObject.optString("code", "unknown");*/
            String code = "0";
            if (Constants.CODE_SUCCESS.equals(code)) {
                logger.info("短信验证码发送成功");
                // 存入短信验证码
                request.getSession().setAttribute(Constants.SMSKEY, smskey);
                // 存入短信验证码生成时间
                request.getSession().setAttribute(Constants.SMS_BUILDTIME, new Date());
                map.put("code", Constants.CODE_SUCCESS);
                map.put("msg", "短信验证码发送成功");
            } else if (Constants.CODE_PARAMETER_ERROR.equals(code)) {
                logger.error("短信验证码发送失败，参数错误");
                map.put("code", Constants.CODE_PARAMETER_ERROR);
                map.put("msg", "短信验证码发送失败，参数错误");
            } else if (Constants.CODE_IP_NOT_AUTH.equals(code)) {
                logger.error("短信验证码发送失败，IP不在白名单中");
                map.put("code", Constants.CODE_IP_NOT_AUTH);
                map.put("msg", "短信验证码发送失败，IP不在白名单中");
            } else if (Constants.CODE_SYSTEM_ERROR.equals(code)) {
                logger.error("短信验证码发送失败，系统异常");
                map.put("code", Constants.CODE_IP_NOT_AUTH);
                map.put("msg", "短信验证码发送失败，系统异常");
            } else {
                logger.error("短信验证码发送失败，未知异常");
                map.put("code", "-1");
                map.put("msg", "短信验证码发送失败，未知异常");
            }
        } catch (Exception e) {
            logger.info("短信验证码发送失败, {}", e.getMessage());
            map.put("code", "-1");
            map.put("msg", "短信验证码发送失败，系统异常");
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping(value = "/check")
    @ResponseBody
    public String checkSmskey(String smskey, HttpServletRequest request) {
        String msg;
        try {
            HttpSession session = request.getSession();

            // 验证码是否过期
            Date smsBuildtime = (Date) session.getAttribute(Constants.SMS_BUILDTIME);
            long costTime = new Date().getTime() - smsBuildtime.getTime();
            if (costTime > Constants.INVALID_TIME * 1000) {
                msg = "验证码已过期";
                return msg;
            }

            // 验证码是否正确
            String sessionSmskey = String.valueOf(session.getAttribute(Constants.SMSKEY));
            if (StringUtils.equalsIgnoreCase(sessionSmskey, smskey)) {
                msg = "验证码正确";
            } else {
                msg = "验证码错误";
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            msg = "系统错误";
        }
        return msg;
    }
}
