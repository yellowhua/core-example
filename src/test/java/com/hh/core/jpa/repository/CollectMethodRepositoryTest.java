package com.hh.core.jpa.repository;

import com.hh.core.file.excel.util.CollectMethodExcelUtil;
import com.hh.core.jpa.domain.CollectMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2019/12/11.
 * 采集方式repository测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectMethodRepositoryTest {

    @Autowired
    private CollectMethodRepository collectMethodRepository;

    @Test
    public void testSaveAll() {
        collectMethodRepository.deleteAll();

        List<CollectMethod> all = new ArrayList<>();
        String path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\平台监控大屏展示\\版本二\\采集口径\\";
        path += "主机采集口径.xls";
        all.addAll(CollectMethodExcelUtil.readHostExcel(new File(path)));

        path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\平台监控大屏展示\\版本二\\采集口径\\";
        path += "数据库采集口径整理.xlsx";
        all.addAll(CollectMethodExcelUtil.readDatabaseExcel(new File(path)));

        path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\平台监控大屏展示\\版本二\\采集口径\\";
        path += "网络设备采集口径.xlsx";
        all.addAll(CollectMethodExcelUtil.readNetworkExcel(new File(path)));

        collectMethodRepository.saveAll(all);
    }

}
