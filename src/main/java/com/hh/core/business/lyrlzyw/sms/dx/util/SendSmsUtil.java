package com.hh.core.business.lyrlzyw.sms.dx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class SendSmsUtil {

    private static Logger logger = LogManager.getLogger(SendSmsUtil.class);

    // 测试地址
    private final String URL = "http://www.y12345678.com:5555/templateSms";
    private final String USER_ID = "1152";
    // 发送短信方分配分配
    private  String KEY = "lyrs1key";
    // 发送短信方分配分配
    private final String EXTRA = "lyrs1key";


    public String getToken() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("userId", USER_ID);
        String timestamp = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        params.put("timestamp", timestamp);
        params.put("key", KEY);
        String digest = "userId=" + USER_ID + "&key=" + KEY + "&timestamp=" + timestamp + "&extra=" + EXTRA;
        String md5Encode = Md5Tool.EncoderMd5(digest);
        params.put("digest", md5Encode);
        String resp = HttpClientUtil.post(URL + "/getToken", params);
        JSONObject jsonObject = JSON.parseObject(resp);
        return jsonObject.getString("token");
    }

    public String sendTemplateSms(String telephone, String content) throws Exception {
        String token = getToken();
        logger.info("token:{}", token);
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("userId", USER_ID);
        String timestamp = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        params.put("timestamp", timestamp);
        params.put("key", KEY);
        JSONArray objects = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seq", 1);
        List<String> tels = new ArrayList<>();
        tels.add(telephone);
        jsonObject.put("tels", tels);
        jsonObject.put("value1", content);
        jsonObject.put("value2", "测试2");
        jsonObject.put("value3", RandomUtil.getRandomPwd(6));
        jsonObject.put("tmpSms", "100335");
        objects.add(jsonObject);
        String datas = objects.toJSONString();
        params.put("datas", datas) ;
        String digest = "userId=" + USER_ID + "&token=" + token + "&key=" + KEY  + "&datas=" + datas + "&timestamp=" + timestamp + "&extra=" + EXTRA;
        digest = Md5Tool.EncoderMd5(digest);
        params.put("digest", digest);
        logger.info("params:{}", params.toString());
        return HttpClientUtil.post(URL + "/sendTmpSms", params);
    }
}
