package com.hh.core.file.excel.read.repository;

import com.hh.core.file.txt.read.business.repository.Em13cTrapRepository;
import com.hh.core.file.txt.read.business.util.Em13cTxtUtil;
import com.hh.core.file.txt.read.business.domain.Em13cTrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

/**
 * Created by hh on 2019/12/11.
 * em13cTrap报文repository测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Em13cTrapRepositoryTest {

    @Autowired
    private Em13cTrapRepository em13cTrapRepository;

    @Test
    public void testSaveAll() {
        em13cTrapRepository.deleteAll();

        String path = "C:\\Users\\Administrator\\Desktop\\";
        path += "EM13C告警核对.txt";
        File file = new File(path);
        List<Em13cTrap> em13cTraps = Em13cTxtUtil.readTrap(file);

        em13cTrapRepository.saveAll(em13cTraps);
    }

}
