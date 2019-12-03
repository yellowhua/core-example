package com.hh.core.file.image.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Created by hh on 2019/7/1.
 * 图片流解析
 */
public class ImagaParseUtil {

    private static final Logger logger = LogManager.getLogger(ImagaParseUtil.class);

    public static String readFileContent(File file) {
        String content = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            fileInputStream.read(filecontent);
            content =  new String(filecontent, "gbk");
        } catch (IOException e) {
            logger.info("parse error");
        }
        return content;
    }

    public static void imageToBufferedInputStream(File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(
                    new FileInputStream(file));
            int str;
            StringBuilder sb = new StringBuilder();
            while ((str = bufferedInputStream.read()) != -1) {
                sb.append(str);
            }
            logger.info("imageToBufferedInputStream:{}", sb);
        } catch (IOException e) {
            logger.info("parse error");
        }
    }

}
