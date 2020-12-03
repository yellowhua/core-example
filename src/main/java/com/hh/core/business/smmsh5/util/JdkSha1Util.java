package com.hh.core.business.smmsh5.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha1 摘要算法
 *
 * @author wuyuchuan
 * @e-mail 975710950@qq.com
 * @date 2020/10/28 21:57
 * @package com.ylzinfo.security.utils
 * @description
 */
@Slf4j
public class JdkSha1Util {
  public static String getSign(String plainText) {
    String cipherStr = "";
    MessageDigest messageDigest = null;
    try {
      messageDigest = MessageDigest.getInstance("SHA");
      byte[] cipherBytes = messageDigest.digest(plainText.getBytes());
      cipherStr = Hex.encodeHexString(cipherBytes);
    } catch (NoSuchAlgorithmException e) {
      log.error(e.getMessage(), e);
    }
    return cipherStr;
  }
}
