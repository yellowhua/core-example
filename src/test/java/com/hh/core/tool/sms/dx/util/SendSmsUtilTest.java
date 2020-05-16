package com.hh.core.tool.sms.dx.util;

import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class SendSmsUtilTest {

    private static Logger logger = LogManager.getLogger(SendSmsUtilTest.class);

    @Test
    public void testSendSms() {
        try{
            String content = "测试1";
            SendSmsUtil sendSmsUtil = new SendSmsUtil();
            String result= sendSmsUtil.sendTemplateSms("18965082080", content);
            logger.info("result:{}", result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String code = jsonObject.optString("code", "unknown");
            if (Constants.CODE_SUCCESS.equals(code)) {
                logger.info("短信验证码发送成功");
            } else if (Constants.CODE_PARAMETER_ERROR.equals(code)) {
                logger.error("短信验证码发送失败，参数错误");
            } else if (Constants.CODE_SYSTEM_ERROR.equals(code)) {
                logger.error("短信验证码发送失败，系统异常");
            } else {
                logger.error("短信验证码发送失败，未知错误");
            }
        }catch (Exception e) {
            logger.info("短信验证码发送失败, {}", e.getMessage());
            e.printStackTrace();
        }
    }

}
