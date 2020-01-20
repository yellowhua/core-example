package com.hh.core.file.txt.util;

import java.io.*;

/**
 * Created by hh on 2019/12/19.
 * txt工具类
 */
public class TxtUtil {

    public static void readTxt(File file){
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            //  考虑到编码格式
            inputStreamReader = new InputStreamReader(new FileInputStream(file), "gbk");
            bufferedReader = new BufferedReader(inputStreamReader);
            String value;
            while((value = bufferedReader.readLine()) != null){
                System.out.println(value);
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
    }

}
