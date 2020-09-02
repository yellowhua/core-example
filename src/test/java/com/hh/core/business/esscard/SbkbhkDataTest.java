package com.hh.core.business.esscard;

import com.hh.core.business.esscard.entity.KgBhk;
import com.hh.core.business.esscard.repository.KgBhkRepository;
import com.hh.core.file.txt.read.util.TxtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbkbhkDataTest {

    @Autowired
    private KgBhkRepository kgBhkRepository;

    @Test
    public void testInsert() {
        File file = new File("C:\\Users\\45198\\Desktop\\ghhf.txt");
        List<String> list = TxtUtil.readTxt(file);
        List<KgBhk> kgBhkList = new ArrayList<>();
        int id = 1;
        for (String row : list) {
            String[] rowArray = row.split("\t");
            KgBhk kgBhk = new KgBhk();
            kgBhk.setId(String.valueOf(id));
            kgBhk.setCreateTime(rowArray[0]);
            kgBhk.setMobile(rowArray[1]);
            kgBhk.setOutTradeNo(rowArray[2]);
            kgBhk.setResult(rowArray[3]);
            kgBhkList.add(kgBhk);
            id++;
        }
        kgBhkRepository.saveAll(kgBhkList);
    }

}
