package com.hh.core.basic.date;

import org.junit.Test;

/**
 * Created by hh on 2020/3/4.
 * 时间工具类测试
 */
public class MyDateUtilTest {

    @Test
    public void testGetWeek() {
        String date = "2020-01-05";
        System.out.println(MyDateUtil.getWeek(date));
    }

}
