package com.hh.core.file.txt.read.util;

import com.hh.core.file.txt.read.util.YwztTxtUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hh on 2019/6/27.
 * ywzt测试
 */
public class YwztTxtUtilTest {

    @Test
    public void testReadTrap() throws UnsupportedEncodingException {
        String path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\Imanage外部告警\\告警核对\\华为\\trap报文表\\";
        path += "CM_MIB_TRAP_INFO_YWZT_20200109.sql";
        File file = new File(path);
        List<String> list = YwztTxtUtil.readTrap(file);
        List<String> newList = new ArrayList<>();
        for (String name : list) {
            name = "%" + name.replace(":", "%");
            name = URLDecoder.decode(name, "UTF-8");
            newList.add(name);
        }

        Set<String> set = new HashSet<>();
        for (String name : newList) {
            String var1 = StringUtils.substringAfter(name, "云服务=");
            String type = StringUtils.substringBefore(var1, ",");
            set.add(type);
        }

        set.forEach(System.out::println);
    }

}
