package com.hh.core.sso.server.service.impl;

import com.hh.core.sso.server.service.ServerService;
import com.hh.core.sso.server.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hh on 2020/6/3.
 * 单点登录服务端
 */
@Service
public class ServerServiceImpl implements ServerService {

    @Override
    public String login(HttpServletRequest request, String returnUrl) {
        // 模拟服务端登录成功
        HttpSession session = request.getSession();
        session.setAttribute("isLogin", true);

        // 生成token保存到session中
        String token = "company" + UUID.randomUUID().toString();
        session.setAttribute("token", token);

        return token;
    }

    @Override
    public Map<String, Object> checkToken(HttpServletRequest request, String token) {
        // 验证token
        HttpSession session = request.getSession();
        String sessionToken = String.valueOf(session.getAttribute("token"));

        // 返回用户信息
        Map<String, Object> resultMap = new HashMap<>();
        if (!StringUtils.equals(token, sessionToken)) {
            resultMap.put("code", Constants.CODE_SUCCESS);
            resultMap.put("msg", "token信息验证成功");
            resultMap.put("data", findUserinfo(token));
        } else {
            resultMap.put("code", Constants.CODE_SUCCESS);
            resultMap.put("msg", "token信息验证失败");
            resultMap.put("data", null);
        }
        return resultMap;
    }

    private Map<String, Object> findUserinfo(String token) {
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.startsWith(token, "person")) {
            data.put("accountType", "person");
            data.put("personName", "gr02sso");
            data.put("cardNo", "110101201803070933");
            data.put("phone", "13865234585");
        } else {
            data.put("accountType", "company");
            data.put("companyName", "aaaaaaaa");
            data.put("unifiedCode", "88888888");
        }
        return data;
    }

}
