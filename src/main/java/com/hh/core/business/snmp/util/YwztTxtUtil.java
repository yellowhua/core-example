package com.hh.core.business.snmp.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2019/12/19.
 * txt工具类
 */
public class YwztTxtUtil {

    public static List<String> readTrap(File file){
        List<String> list = new ArrayList<>();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            //  考虑到编码格式
            inputStreamReader = new InputStreamReader(new FileInputStream(file), "gbk");
            bufferedReader = new BufferedReader(inputStreamReader);
            String value;
            while((value = bufferedReader.readLine()) != null){
                if (!value.contains("1.3.6.1.4.1.2011.2.15.2.4.3.3.27=")) {continue;}
                String var1 = StringUtils.substringAfter(value, "1.3.6.1.4.1.2011.2.15.2.4.3.3.27=");
                String oid = StringUtils.substringBefore(var1, ",");
                list.add(oid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
