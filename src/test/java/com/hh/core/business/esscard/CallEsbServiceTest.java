package com.hh.core.business.esscard;

import com.hh.core.business.esscard.esb.CallEsbService;
import com.hh.core.business.esscard.esb.entity.EsbParams;
import com.hh.core.business.esscard.esb.entity.EsbResult;
import com.hh.core.business.esscard.esb.enums.EsbServiceId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallEsbServiceTest {

    @Autowired
    private CallEsbService esbService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void esbTest() {
        EsbParams eparams = new EsbParams();
        eparams.add("accessId", "yw_ycs");
        eparams.add("accessSecret", getPwd("yw_ycs"));

        eparams.add("bizId", "");
        eparams.add("telephones", "18965082080");
        eparams.add("title", "测试短信");
        eparams.add("content", "消息重发");
        EsbResult result = esbService.doAction(EsbServiceId.QUERY_GW, eparams);
        System.out.println(result);
    }

    @SuppressWarnings("unused")
    public static String getPwd(String accessId) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64 = new BASE64Encoder();
            String valicode =accessId
                    + new SimpleDateFormat("yyyyMMdd").format(new Date());
            String str = base64.encode(md5.digest(valicode.getBytes("utf-8")));
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
