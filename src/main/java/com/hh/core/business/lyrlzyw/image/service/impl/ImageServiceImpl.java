package com.hh.core.business.lyrlzyw.image.service.impl;

import com.hh.core.business.lyrlzyw.image.domain.Cb09Attachment;
import com.hh.core.business.lyrlzyw.image.mapper.ImageMapper;
import com.hh.core.business.lyrlzyw.image.service.ImageService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<String> findImageContentList() {
        List<String> images = new ArrayList<>();
        List<Cb09Attachment> list = imageMapper.findImageContentList();
        byte[] image;
        for (Cb09Attachment cb09Attachment : list) {
            image = cb09Attachment.getAttachmentcontent();
            images.add(Base64.encodeBase64String(image));
        }
        return images;
    }

}
