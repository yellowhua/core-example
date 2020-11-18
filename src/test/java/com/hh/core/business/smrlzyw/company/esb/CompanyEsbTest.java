package com.hh.core.business.smrlzyw.company.esb;

import com.hh.core.business.esb.CallEsbService;
import com.hh.core.business.esb.entity.EsbParams;
import com.hh.core.business.esb.entity.EsbResult;
import com.hh.core.business.esb.enums.EsbServiceId;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CompanyEsbTest {

    @Autowired
    private CallEsbService esbService;

    @Test
    public void testSendSmsCode() {
        // 封装参数
        EsbParams eparams = new EsbParams();
        eparams.add("aae005", "18965082080");
        eparams.add("aaa121", "013");
        EsbResult result = esbService.doAction(EsbServiceId.HQDXYZM, eparams);
        log.info("{}", result);
    }

    @Test
    public void testYzSmsCode() {
        // 封装参数
        EsbParams eparams = new EsbParams();
        eparams.add("aae005", "18965082080");
        eparams.add("aaa121", "013");
        eparams.add("yse080", "441618");
        EsbResult result = esbService.doAction(EsbServiceId.YZDXYZM, eparams);
        log.info("{}", result);
    }

    @Test
    public void testSendSms() {
        // 封装参数
        EsbParams eparams = new EsbParams();
        eparams.add("aae005", "1896508208");
        eparams.add("aaa121", "013");
        eparams.add("content", "哈哈哈");
        EsbResult result = esbService.doAction(EsbServiceId.FSPTDX, eparams);
        List<Map<String, String>> list = result.getContent();
        String aae314 = list.get(0).get("aae314");
        if (!aae314.equals("1")) {
            log.error(list.get(0).get("aae317"));
        }
    }

}
