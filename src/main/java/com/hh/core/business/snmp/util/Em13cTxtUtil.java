package com.hh.core.business.snmp.util;

import com.hh.core.business.snmp.domain.Em13cTrap;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hh on 2019/12/19.
 * txt工具类
 */
public class Em13cTxtUtil {

    public static List<Em13cTrap> readTrap(File file){
        List<Em13cTrap> em13cTraps = new ArrayList<>();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            //  考虑到编码格式
            inputStreamReader = new InputStreamReader(new FileInputStream(file), "gbk");
            bufferedReader = new BufferedReader(inputStreamReader);
            String value;
            while((value = bufferedReader.readLine()) != null){
                Em13cTrap em13cTrap = new Em13cTrap();
                String var1 = StringUtils.substringAfter(value, "1.3.6.1.4.1.111.15.3.1.1.24=");
                String ipOid = StringUtils.substringBefore(var1, ",");
                em13cTrap.setIpOid(ipOid);

                String var2 = StringUtils.substringAfter(value, "1.3.6.1.4.1.111.15.3.1.1.21=");
                String dbInstanceOid = StringUtils.substringBefore(var2, ",");
                em13cTrap.setDbinstanceOid(dbInstanceOid);

                em13cTraps.add(em13cTrap);
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
        return em13cTraps;
    }

}
