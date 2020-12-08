package com.hh.core.business.smmsh5.esb;

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

/**
 * @author huanghua
 * @date 2020/11/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Smmsh5EsbTest {

    @Autowired
    private CallEsbService esbService;

    @Test
    public void testQueryGrsybxjf() {
        EsbParams eparams = new EsbParams();
        eparams.add("aac002", "350403199104130013");
        eparams.add("aae041", "201801");
        eparams.add("aae042", "202012");
        eparams.add("cpage", "1");
        eparams.add("rows", "10");
        EsbResult result = esbService.doAction(EsbServiceId.GRSYBXJF, eparams);
        log.info("{}", result);

        // 解析数据
        checkEsbResult(result);
        List<Map<String, String>> list = result.getContent();
        for (Map<String, String> map : list) {
            log.info(map.toString());
        }

    }

    /**
     * 校验esb返回结果
     * @param result esb返回结果
     */
    private void checkEsbResult(EsbResult result) {
        if (!result.isResultcode()) {
            throw new RuntimeException(result.getResultstring());
        }
    }

}
