package com.hh.core.tool.endecrypt.druid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by hh on 2020/5/22.
 * druid加解密测试
 */
public class DruidEnDecryptTest {

    public static final Logger logger = LogManager.getLogger(DruidEnDecrypt.class);

    @Test
    public void testEncrypt() throws Exception {
        String password = "SNNJJJJ1fF";
        logger.info("encrypt password:\n{}", DruidEnDecrypt.encrypt(password));
    }

    @Test
    public void testDecrypt() throws Exception {
        String password = "KqJNg2b9dxSQ9+KZ7yppku/F60Nhx5bp+mXhLUXb0wjysm87/KmNzvupxjtIKSjZ0j3tVFiZLr+TUJfvHs/rTQ==";
        logger.info("decrypt password:\n{}", DruidEnDecrypt.decrypt(password));
    }

}
