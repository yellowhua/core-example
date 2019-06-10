package com.hh.core.regular;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hh on 2019/5/20.
 * 手机号码正则表达式
 */
@Controller
@RequestMapping(value = "regular")
public class PhoneRegular {

    @GetMapping(value = "phone")
    public String phone() {
        return "/regular/phone";
    }

}
