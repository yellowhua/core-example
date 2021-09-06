package com.hh.core.business.esscard.util;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Utils.class);

    public Md5Utils() {
    }

    private static byte[] md5(String s) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception var3) {
            LOGGER.error("MD5 Error...", var3);
            return null;
        }
    }

    private static final String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        } else {
            StringBuffer buf = new StringBuffer(hash.length * 2);

            for(int i = 0; i < hash.length; ++i) {
                if ((hash[i] & 255) < 16) {
                    buf.append("0");
                }

                buf.append(Long.toString((long)(hash[i] & 255), 16));
            }

            return buf.toString();
        }
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception var2) {
            LOGGER.error("not supported charset...{}", var2);
            return s;
        }
    }
}

