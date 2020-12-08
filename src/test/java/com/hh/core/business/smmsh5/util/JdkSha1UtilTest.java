package com.hh.core.business.smmsh5.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JdkSha1Util测试类
 *
 * @author huanghua
 * @date 2020/11/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JdkSha1UtilTest {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXxZ/542BjAKiRlGH6" +
            "NwyotEoIu1nVh+d6yhlPv7CbYtBDTxjkLf672s1TqHEnQwvlGJYLrlJcV/RxO1le+qbWI7Ipc89jgEXELhSJgP1p" +
            "2e8ogVo8q7SF+zdyJHSHYlJ72gtKXuDXRrsnBEJFUnxAIrn5LMhQlIOX+4wzcTzotQIDAQAB";

    @Test
    public void testSignEncrypt() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appid", "esanming");
        jsonObject.put("appkey", "f9b44609ad57ef7c20c674d62aa5ecab");
        jsonObject.put("aac002", "350403199104130013");
        jsonObject.put("aac003", "刘嘉辰");
        jsonObject.put("authLevel", "4");
        String data = RsaUtils.encryptedDataOnJava(jsonObject.toJSONString(), publicKey);
        String sign = JdkSha1Util.getSign(jsonObject.toJSONString());
        String encryptSign = RsaUtils.encryptedDataOnJava(sign, publicKey);
        log.info("https://ldjy.smrsms.com/smmsh5/#/search-module/paycost?sign=" + encryptSign + "&data=" + data);
        log.info("https://ldjy.smrsms.com/smmsh5/#/search-module/archives?sign=" + encryptSign + "&data=" + data);
        log.info("https://ldjy.smrsms.com/smmsh5/#/search-module/jobs?sign=" + encryptSign + "&data=" + data);
    }

    @Test
    public void testSignEncrypt2() {
        String json =
                "{"
                        + "\"appid\": \"esanming\","
                        + "\"appkey\": \"f9b44609ad57ef7c20c674d62aa5ecab\","
                        + "\"aac002\": \"352227199302140513\","
                        + "\"aac003\": \"黄剑东\","
                        + "\"authLevel\": \"4\""
                        + "}";
        String data = RsaUtils.encryptedDataOnJava(json, publicKey);
        String sign = JdkSha1Util.getSign(json);
        String encryptSign = RsaUtils.encryptedDataOnJava(sign, publicKey);
        log.info("https://ldjy.smrsms.com/smmsh5/#/search-module/paycost?sign=" + encryptSign + "&data=" + data);
        log.info("https://ldjy.smrsms.com/smmsh5/#/search-module/archives?sign=" + encryptSign + "&data=" + data);
        log.info("https://ldjy.smrsms.com/smmsh5/#/search-module/jobs?sign=" + encryptSign + "&data=" + data);
    }

}
