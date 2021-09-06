package com.hh.core.tool.captcha.controller;

import com.hh.core.tool.captcha.util.Constants;
import com.hh.core.tool.captcha.util.GenerateCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/tool/captcha")
public class CaptchaController {

    private static final Logger logger = LogManager.getLogger(CaptchaController.class);

    @GetMapping(value = "/index")
    public String index() {
        return "/tool/captcha/index";
    }

    @GetMapping
    @ResponseBody
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        GenerateCaptcha generateCaptcha = new GenerateCaptcha();
        try {
            generateCaptcha.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/ajax")
    @ResponseBody
    public String checkCaptchaAjax(String captcha, HttpServletRequest request) {
        String msg;
        try {
            HttpSession session = request.getSession();
            String sessionCaptcha = String.valueOf(session.getAttribute(Constants.RANDOMCODEKEY));
            if (StringUtils.equalsIgnoreCase(sessionCaptcha, captcha)) {
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
