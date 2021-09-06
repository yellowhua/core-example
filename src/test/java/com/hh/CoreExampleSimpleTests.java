package com.hh;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by hh on 2020/6/6.
 * 简单测试，无需启动服务器
 */
@Slf4j
public class CoreExampleSimpleTests {

    @Test
    public void test() {
        String accessId = "d537d9c7-51e6-4900-b9c7-f8026983a6d7";
        long time = System.currentTimeMillis();
        System.out.println(time);
        String secret = accessId + time;
        System.out.println(secret);

        System.out.println(str2HexStr(secret));
    }

    /**
     * 字符串转换成十六进制字符串
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    @Test
    public void testSign() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "操作成功");
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("c", "c");
        map2.put("d", "d");

        list.add(map);
        list.add(map2);
        data.put("data", list);
        data.put("totalCount", 100);
        result.put("data", data);
        log.info(JSON.toJSONString(result));
    }

    @Test
    public void testSubstring() {
        String str = "10000070132710000070132720200424000000000000386 13041730   13041730   03041730              13041730   4131033105130700002259143FFFFFFF00000000010000000000010020200424155553004000000成功交易                                00.0000000                   0.0000000                   0.0000000                   03041730                    100012532001022F00000001000001016C8001692401010000061203041730082090012204040021034FB542CAF1669687E32D38EEE7AECE4FF65243614DCB5596F801268BFDA815BBC61ED92995D0ADAAF8F5EBD72785D7969583FFB415A4E562B826E32B2C1D5F30FFBEDC7C315932B998C8563C05C8A838634E42953139F423E5E2C34ED8B47F72343736303530393630303139313937383105130700002259143F03041730030417300000138803181D7C0EB8D0BE3F289714F63FEC6930E9782A6B968301933C3222E78EC581215EA29CB0003C20012020042408817382430098051481600000000000000000000000000000000015E98DA692CC80046480474FA662F4B66473A32C59A14A0B5FE1B2D65D79D1A8E21847061D88E2BCAFCBDFA7B25849F7E3D824567E2391F9E6D47287379E6D85F55EA29B84158A9B7DC5515468E98F5EAFF68EB7A4BE92345CC8D331E1DBCBFE5109B070BD127A133D1B6A5CDA3A78AA306DF09BD30D9144D8B81564B2B03482ED171E7574D60002000832303030303030300003000830363037303830390004000A322E315F3137303932330005002B3033303431373330303030313431333130313436313837303230323030343234313535353533363736313500060004343133310007000100000800033135360009000400000100000A0006303030313135000C00054646464646000D0006303030333033001000045EA29B890011002031441346C99AD448071A719C32BC124D8B31A3CDD7DF59223EBC310CB994079B20020000200300142020042415555320050004230320070001020090012413101461870201100041730203700062303路20380006吕梁市2039000820000115";
        log.info(str.substring(12, 24));
        log.info(str.substring(109, 129));
    }

    @Test
    public void testParseFile() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\work\\ylz\\sx\\吕梁公交数据比对\\tmp\\busdatagram16\\SUCCESS_DATA\\141100\\20200615\\111200\\CL200615111107030417300000000001A.txt");
        int i = 1;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String s = null;
            //使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                if (i <= 2) {
                    i++;
                } else {
                    log.info("交易流水号：" + s.substring(12, 24));
                    i++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
