package com.hh.core.business.lyrlzyw.sms.dx.util;

public class RandomUtil {

    private final static String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8","9"};

    //获取N位数随机密码
    public static String getRandomPwd(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int a = (int)(Math.random() * 10);
            buffer.append(str[a]);
        }
        return buffer.toString();
    }

}
