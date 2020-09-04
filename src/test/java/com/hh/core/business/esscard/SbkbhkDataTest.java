package com.hh.core.business.esscard;

import com.hh.core.business.esscard.entity.EsscardBhk;
import com.hh.core.business.esscard.entity.KgBhk;
import com.hh.core.business.esscard.repository.EsscardBhkRepository;
import com.hh.core.business.esscard.repository.KgBhkRepository;
import com.hh.core.file.excel.read.util.ExcelReadUtil;
import com.hh.core.file.txt.read.util.TxtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SbkbhkDataTest {

    @Autowired
    private KgBhkRepository kgBhkRepository;

    @Autowired
    private EsscardBhkRepository esscardBhkRepository;

    @Test
    public void testInsertKgBhk() {
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

    @Test
    public void testInsertEsscardBhk() {
        long start = System.currentTimeMillis();
        File file = new File("C:\\Users\\45198\\Desktop\\社保卡补换卡充值记录.csv");
        List<String> list = TxtUtil.readTxt(file);
        List<EsscardBhk> esscardBhkList = new ArrayList<>();
        for (String row : list) {
            row = row.replaceAll("\"", "");
            String[] rowArray = row.split(",");
            EsscardBhk esscardBhk = new EsscardBhk();
            esscardBhk.setRechargeId(rowArray[0]);
            esscardBhk.setOutTradeNo(rowArray[1]);
            esscardBhk.setMobile(rowArray[2]);
            esscardBhk.setMoney(rowArray[3]);
            esscardBhk.setStatus(rowArray[4]);
            esscardBhk.setSzRtnCode(rowArray[5]);
            esscardBhk.setTimeStamp(rowArray[6]);
            esscardBhk.setAccountId(rowArray[7]);
            esscardBhk.setVolumeId(rowArray[8]);
            if (rowArray.length == 10) {
                esscardBhk.setCreateTime(rowArray[9]);
            }
            esscardBhkList.add(esscardBhk);
        }
        esscardBhkRepository.saveAll(esscardBhkList);
        log.info("cost time:{}", System.currentTimeMillis() - start);
    }

}
