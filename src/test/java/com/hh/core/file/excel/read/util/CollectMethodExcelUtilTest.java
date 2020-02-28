package com.hh.core.file.excel.read.util;

import com.hh.core.file.excel.read.util.CollectMethodExcelUtil;
import com.hh.core.jpa.domain.CollectMethod;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by hh on 2019/6/27.
 * 采集方式excel工具类测试
 */
public class CollectMethodExcelUtilTest {

    @Test
    public void testReadHostExcel() {
        String path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\平台监控大屏展示\\版本二\\采集口径\\";
        path += "主机采集口径.xls";
        List<CollectMethod> collectMethods = CollectMethodExcelUtil.readHostExcel(new File(path));

        System.out.println("=========== size ===========");
        System.out.println(collectMethods.size());
        System.out.println("============================");

        System.out.println("=========== data ===========");
        System.out.println(collectMethods);
        System.out.println("============================");
    }

    @Test
    public void testReadDatabaseExcel() {
        String path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\平台监控大屏展示\\版本二\\采集口径\\";
        path += "数据库采集口径整理.xlsx";
        List<CollectMethod> collectMethods = CollectMethodExcelUtil.readDatabaseExcel(new File(path));

        System.out.println("=========== size ===========");
        System.out.println(collectMethods.size());
        System.out.println("============================");

        System.out.println("=========== data ===========");
        System.out.println(collectMethods);
        System.out.println("============================");
    }

    @Test
    public void testReadNetworkExcel() {
        String path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\平台监控大屏展示\\版本二\\采集口径\\";
        path += "网络设备采集口径.xlsx";
        List<CollectMethod> collectMethods = CollectMethodExcelUtil.readNetworkExcel(new File(path));

        System.out.println("=========== size ===========");
        System.out.println(collectMethods.size());
        System.out.println("============================");

        System.out.println("=========== data ===========");
        System.out.println(collectMethods);
        System.out.println("============================");
    }
}
