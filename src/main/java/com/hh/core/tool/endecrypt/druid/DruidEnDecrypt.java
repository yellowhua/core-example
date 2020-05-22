package com.hh.core.tool.endecrypt.druid;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * Created by hh on 2020/5/22.
 * druid加解密
 */
public class DruidEnDecrypt {

    /**
     * 加密
     * @param password 原始密码
     * @return 加密后的密码
     * @throws Exception
     */
    public static String encrypt(String password) throws Exception {
        return ConfigTools.encrypt(password);
    }

    /**
     * 解密
     * @param password 加密后的密码
     * @return 原始密码
     * @throws Exception
     */
    public static String decrypt(String password) throws Exception {
        return ConfigTools.decrypt(password);
    }

}
