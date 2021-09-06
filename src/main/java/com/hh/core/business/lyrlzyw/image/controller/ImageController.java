package com.hh.core.business.lyrlzyw.image.controller;

import com.hh.core.business.lyrlzyw.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping
    public String index() {
        return "/business/image/image";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public List<String> findImageContentList() {
        return imageService.findImageContentList();
    }

}
