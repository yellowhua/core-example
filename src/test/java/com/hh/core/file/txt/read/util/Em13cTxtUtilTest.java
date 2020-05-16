package com.hh.core.file.txt.read.util;

import com.hh.core.file.txt.read.business.domain.Em13cTrap;
import com.hh.core.file.txt.read.business.util.Em13cTxtUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by hh on 2019/6/27.
 * em13c测试
 */
public class Em13cTxtUtilTest {

    @Test
    public void testReadTrap() {
        String path = "C:\\Users\\Administrator\\Desktop\\work\\福建\\福建Imanage-已完成\\Imanage外部告警\\告警核对\\华为\\trap报文表\\";
        path += "EM13C告警核对.txt";
        File file = new File(path);
        List<Em13cTrap> em13cTraps = Em13cTxtUtil.readTrap(file);

        System.out.println("=========== size ===========");
        System.out.println(em13cTraps.size());
        System.out.println("============================");

        System.out.println("=========== data ===========");
        System.out.println(em13cTraps);
        System.out.println("============================");
    }
}
