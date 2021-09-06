package com.hh.core.layui.eletree.controller;

import com.hh.core.layui.eletree.service.EletreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * eletree插件
 *
 * @author huanghua
 * @date 2020/12/15
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/layui/eletree")
public class EletreeController {

    private final EletreeService eletreeService;

    @GetMapping
    public String index() {
        return "/layui/eletree/index";
    }

    @PostMapping(value = "/zw-data")
    @ResponseBody
    public List<Map<String, Object>> zwData(){
        return eletreeService.loadZwData();
    }

}
