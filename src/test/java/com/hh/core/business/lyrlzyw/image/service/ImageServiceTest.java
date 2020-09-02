package com.hh.core.business.lyrlzyw.image.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void testFindImageContentList() {
        List<String> images = imageService.findImageContentList();
        System.out.println();
    }

}
