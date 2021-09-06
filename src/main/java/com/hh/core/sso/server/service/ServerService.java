package com.hh.core.sso.server.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by hh on 2020/6/3.
 * 单点登录服务端
 */
public interface ServerService {

    String login(HttpServletRequest request, String returnUrl);

    Map<String, Object> checkToken(HttpServletRequest request, String token);
}
