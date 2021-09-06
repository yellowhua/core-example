package com.hh.core.business.blockchain.controller;

import com.hh.core.business.blockchain.entity.User;
import com.hh.core.business.blockchain.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author hh
 * @date 2021/5/28 0028
 */
@Controller
@RequestMapping(value = "/blockchain")
@RequiredArgsConstructor
public class BlockchainController {

    private final BlockchainService blockchainService;

    @GetMapping
    public String index() {
        return "/blockchain/index";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        List<User> list = blockchainService.query();
        map.put("data", list);
        return map;
    }

    @GetMapping(value = "/to-add")
    public String toAdd() {
        return "/blockchain/toAdd";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public String add(User user) {
        blockchainService.add(user);
        return "success";
    }

    @GetMapping(value = "/to-edit")
    public String toEdit(Model model, @RequestParam String id) {
        User user = blockchainService.queryById(id);
        model.addAttribute("user", user);
        return "/blockchain/toEdit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public String edit(User user) {
        blockchainService.edit(user);
        return "success";
    }

    @GetMapping(value = "/detail")
    public String detail(Model model, @RequestParam String id) {
        User user = blockchainService.queryById(id);
        model.addAttribute("user", user);
        return "/blockchain/detail";
    }

}
