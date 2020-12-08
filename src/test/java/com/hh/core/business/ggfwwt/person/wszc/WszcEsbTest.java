package com.hh.core.business.ggfwwt.person.wszc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WszcEsbTest {

    @Autowired
    private CallEsbService esbService;

    @Test
    public void testGetCaseListInfo() {
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("rows", "10");
        paraMap.put("cpage", "1");
        paraMap.put("aac147", "350322195109020011");
        // 封装参数
        EsbParams eparams = new EsbParams();
        eparams.add("json", JSON.toJSONString(paraMap));
        EsbResult result = esbService.doAction(EsbServiceId.CXAJLB, eparams);
        log.info("{}", result);
    }

    @Test
    public void testApplyArbitration() {
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("abe111", "1");
        paraMap.put("abb705", "1");
        paraMap.put("abb854", "2");
        paraMap.put("abb880", "0");
        paraMap.put("abb722", "1");
        paraMap.put("abb013", "20200728");
        paraMap.put("abe110", "0");
        paraMap.put("abb712", "申请人陈述仲裁事实和理由");
        paraMap.put("abb878", "仲裁请求");

        List<Map<String, Object>> applicants = new ArrayList<>();
        Map<String, Object> appMap = new HashMap<>();
        appMap.put("aac003", "测试个人");
        appMap.put("aac058", "01");
        appMap.put("aac147", "350322195109020011");
        appMap.put("aac004", "1");
        appMap.put("aac006", "19960204");
        appMap.put("aac009", "10");
        appMap.put("aae005", "15859666666");
        appMap.put("aae006", "送达地址");

        appMap.put("sddz00", "福建省/厦门市/思明区");
        appMap.put("sddz02", "福建省code");
        appMap.put("sddz03", "厦门市code");
        appMap.put("sddz04", "思明区code");
        appMap.put("sddz01", "送达详细地址");
        appMap.put("hjdz00", "福建省/厦门市/思明区");
        appMap.put("hjdz02", "福建省code");
        appMap.put("hjdz03", "厦门市code");
        appMap.put("hjdz04", "思明区code");
        appMap.put("hjdz01", "户籍详细地址");
        appMap.put("czdz00", "福建省/厦门市/思明区");
        appMap.put("czdz02", "福建省code");
        appMap.put("czdz03", "厦门市code");
        appMap.put("czdz04", "思明区code");
        appMap.put("czdz01", "常住详细地址");
        appMap.put("sdfs00", "0");
        appMap.put("aac028", "0");
        applicants.add(appMap);
        paraMap.put("applicants", applicants);

        List<Map<String, Object>> respondents = new ArrayList<>();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("aab004", "单位名称e");
        resMap.put("aab003", "123456789");
        resMap.put("aab019", "10");
        resMap.put("aab020", "175");
        resMap.put("aab013", "法定代表人姓名");
        resMap.put("aab015", "法定代表人联系电话");
        resMap.put("aae006", "送达地址");
        resMap.put("sddz00", "福建省/厦门市/思明区");
        resMap.put("sddz02", "福建省code");
        resMap.put("sddz03", "厦门市code");
        resMap.put("sddz04", "思明区code");
        resMap.put("sddz01", "送达详细地址");
        resMap.put("hjdz00", "福建省/厦门市/思明区");
        resMap.put("hjdz02", "福建省code");
        resMap.put("hjdz03", "厦门市code");
        resMap.put("hjdz04", "思明区code");
        resMap.put("hjdz01", "户籍详细地址");
        resMap.put("czdz00", "福建省/厦门市/思明区");
        resMap.put("czdz02", "福建省code");
        resMap.put("czdz03", "厦门市code");
        resMap.put("czdz04", "思明区code");
        resMap.put("czdz01", "常住详细地址");
        resMap.put("sdfs00", "0");
        respondents.add(resMap);
        paraMap.put("respondents", respondents);

        EsbParams eparams = new EsbParams();
        eparams.add("json", JSON.toJSONString(paraMap));
        log.info("{}", JSON.toJSONString(paraMap));
        EsbResult result = esbService.doAction(EsbServiceId.ZCAJSB, eparams);
        log.info("{}", result);
    }

    @Test
    public void testUpdateArbitration() {
        EsbParams eparams = new EsbParams();
        eparams.add("abz309", "38380");
        eparams.add("abe111", "1");
        eparams.add("abb705", "1");
        eparams.add("abe110", "0");
        eparams.add("abb880", "0");
        eparams.add("abb722", "1");
        eparams.add("abb013", "20200729");
        eparams.add("abb712", "申请人陈述仲裁事实和理由 --- 修改");
        eparams.add("abb878", "仲裁请求");
        eparams.add("abz345", "1");
        EsbResult result = esbService.doAction(EsbServiceId.XGAJXX, eparams);
        log.info("{}", result);
    }

    @Test
    public void testSubmitArbitration() {
        EsbParams eparams = new EsbParams();
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("abz309", "37173");
        eparams.add("json", JSON.toJSONString(paraMap));
        EsbResult result = esbService.doAction(EsbServiceId.SJTJ, eparams);
        log.info("{}", result);
    }

    @Test
    public void testGetCaseDetailInfo() {
        EsbParams eparams = new EsbParams();
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("abz309", "37173");
        eparams.add("json", JSON.toJSONString(paraMap));
        eparams.add("key", "BA5D28CD684473919F7F42A867B859A3091078A9D048DDEDC3228A04B01582C6");
        eparams.add("termid", "127.0.0.1");
        EsbResult result = esbService.doAction(EsbServiceId.CXAJXXXX, eparams);
        log.info("{}", result);
    }

    /**
     * ABE111 争议类型
     * ABB705 申请人类型
     * ABB880 是否十人以上
     * ABB854 处理方式
     * ABE110 是否集体案件
     *
     * AAC058 证件类型
     * AAC004 性别
     * AAB019 单位类型
     */
    @Test
    public void testGetAA10Info() {
        EsbResult result = callCodeEsb("ABE111");
        EsbResult result1 = callCodeEsb("ABB705");
        EsbResult result2 = callCodeEsb("ABB880");
        EsbResult result3 = callCodeEsb("ABB854");
        EsbResult result4 = callCodeEsb("ABE110");
        EsbResult result5 = callCodeEsb("AAC058");
        EsbResult result6 = callCodeEsb("AAC004");
        EsbResult result7 = callCodeEsb("AAB019");
        log.info("\n\n\n\n\n{}\n{}\n{}\n{}\n{}\n{}\n{}\n{}\n\n\n\n\n",
                JSON.toJSONString(result),
                JSON.toJSONString(result1),
                JSON.toJSONString(result2),
                JSON.toJSONString(result3),
                JSON.toJSONString(result4),
                JSON.toJSONString(result5),
                JSON.toJSONString(result6),
                JSON.toJSONString(result7));
    }

    private EsbResult callCodeEsb(String code) {
        EsbParams eparams = new EsbParams();
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("aaa100", code);
        eparams.add("json", JSON.toJSONString(paraMap));
        log.info(JSON.toJSONString(paraMap));
        return esbService.doAction(EsbServiceId.HQBM, eparams);
    }

}
