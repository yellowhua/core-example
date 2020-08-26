package com.hh.core.business.esscard;

import org.junit.Test;

import java.io.File;

/**
 * 读取吕梁公交乘车数据测试
 *
 * @author huanghua
 * @date 2020/8/6
 */
public class ReadBusDataUtilTest {

    @Test
    public void testReadExcel() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\20200806-对账结果.xlsx");
        ReadBusDataUtil.readExcel(file);
    }

}
