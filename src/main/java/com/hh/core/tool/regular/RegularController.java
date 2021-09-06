package com.hh.core.tool.regular;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hh on 2019/5/20.
 * 手机号码正则表达式
 */
@Controller
@RequestMapping(value = "/tool/regular")
public class RegularController {

    @GetMapping
    public String index() {
        return "/tool/regular/index";
    }

}
