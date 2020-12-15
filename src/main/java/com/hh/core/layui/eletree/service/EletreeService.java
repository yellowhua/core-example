package com.hh.core.layui.eletree.service;

import java.util.List;
import java.util.Map;

/**
 * eletree插件
 *
 * @author huanghua
 * @date 2020/12/15
 */
public interface EletreeService {

    /**
     * 加载职位树形数据
     *
     * @return 职位树形数据
     */
    List<Map<String, Object>> loadZwData();

}
