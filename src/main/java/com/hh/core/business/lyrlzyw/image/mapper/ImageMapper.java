package com.hh.core.business.lyrlzyw.image.mapper;

import com.hh.core.business.lyrlzyw.image.domain.Cb09Attachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    /**
     * 查询招聘会图片集合
     * @return 招聘会图片集合
     */
    List<Cb09Attachment> findImageContentList();

}
