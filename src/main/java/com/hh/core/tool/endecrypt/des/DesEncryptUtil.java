package com.hh.core.tool.endecrypt.des;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * <pre>
 * 描述：des加密算法
 * </pre>
 *
 * @类名： com.hh.core.tool.endecrypt.des.DesUtil
 * @作者： huanghua
 * @创建日期: 2021/6/23 11:38
 */
@Slf4j
public class DesEncryptUtil {

    /**
     * 生成秘钥
     *
     * @return 秘钥
     */
    @SneakyThrows
    public static String generateSecretKey() {
        // 生成秘钥
        SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
        // 将秘钥转化为字符串
        String secretKeyStr = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        log.info("秘钥：{}", secretKeyStr);
        return secretKeyStr;
    }

    /**
     * 加密
     *
     * @param secretKeyStr 秘钥
     * @param content 原文数据
     */
    @SneakyThrows
    public static String encrypt(String secretKeyStr, String content){
        // 秘钥转换
        byte[] secretKeyByte = Base64.getDecoder().decode(secretKeyStr);
        SecretKey secretKey = new SecretKeySpec(secretKeyByte, 0, secretKeyByte.length, "DES");

        // 加密，算法类型/工作方式/填充方式
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // 指定为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptResult = cipher.doFinal(content.getBytes());
        // 将byte结果转化为字符串
        String encryptResultStr = Base64.getEncoder().encodeToString(encryptResult);
        log.info("加密结果：{}", encryptResultStr);
        return encryptResultStr;
    }

    /**
     * 解密
     *
     * @param secretKeyStr 秘钥
     * @param encryptResultStr 加密内容
     */
    @SneakyThrows
    public static String decrypt(String secretKeyStr, String encryptResultStr){
        // 秘钥转换
        byte[] secretKeyByte = Base64.getDecoder().decode(secretKeyStr);
        SecretKey secretKey = new SecretKeySpec(secretKeyByte, 0, secretKeyByte.length, "DES");

        // 加密，算法类型/工作方式/填充方式
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // 解密，指定为解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 根据加密内容解密
        byte[] encryptResult = Base64.getDecoder().decode(encryptResultStr);
        byte[] decryptResult = cipher.doFinal(encryptResult);
        String decryptResultStr = new String(decryptResult);
        log.info("解密结果：{} ", decryptResultStr);
        return decryptResultStr;
    }

    public static void main(String[] args) {
        String content = "今天天气真好";
        String secretKeyStr = generateSecretKey();
        String encryptResultStr = encrypt(secretKeyStr, content);
        decrypt(secretKeyStr, encryptResultStr);
    }

}
