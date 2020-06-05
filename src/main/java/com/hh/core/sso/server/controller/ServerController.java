package com.hh.core.sso.server.controller;

import com.hh.core.sso.server.service.ServerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hh on 2020/6/2.
 * 单点登录服务端
 */
@Controller
@RequestMapping(value = "/sso-server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(@RequestParam(required = false) String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);
        return "sso/server/login";
    }

    @ApiOperation(value = "以ajax的方式登录，然后ajax回调函数再跳转到returnUrl")
    @RequestMapping(value = "/login-ajax", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginAjax(HttpServletRequest request, @RequestParam(required = false) String returnUrl, HttpServletResponse response) throws IOException {
        String token = serverService.login(request, returnUrl);
        Map<String, Object> map = new HashMap<>();
        map.put("returnUrl", returnUrl);
        map.put("token", token);
        return map;
    }

    @ApiOperation(value = "以提交form表单的方式登录，然后通过重定向回调returnUrl")
    @RequestMapping(value = "/login-form", method = RequestMethod.POST)
    public void loginForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String returnUrl) throws IOException {
        String token = serverService.login(request, returnUrl);
        response.sendRedirect(returnUrl + "?token=" + token);
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkToken(HttpServletRequest request, @RequestParam String token) {
        return serverService.checkToken(request, token);
    }


}
