package com.hh.core.business.lyrlzyw.image.service;

import java.util.List;

public interface ImageService {

    /**
     * 查询招聘会图片集合
     * @return 招聘会图片集合
     */
    List<String> findImageContentList();

}
