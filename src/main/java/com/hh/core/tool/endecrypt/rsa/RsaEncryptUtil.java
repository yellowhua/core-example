package com.hh.core.tool.endecrypt.rsa;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 描述：rsa加密算法
 * </pre>
 *
 * @类名： com.hh.core.tool.endecrypt.rsa.RsaEncryptUtil
 * @作者： huanghua
 * @创建日期: 2021/6/23 11:38
 */
@Slf4j
public class RsaEncryptUtil {

    /**
     * 生成公钥、私钥
     *
     * @return 公钥、私钥
     */
    @SneakyThrows
    public static List<String> generateSecretKey() {
        List<String> keyList = new ArrayList<>();
        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        keyList.add(publicKeyStr);
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        keyList.add(privateKeyStr);
        return keyList;
    }

    /**
     * 加密
     *
     * @param publicKeyStr 公钥
     * @param content 原文数据
     * @return 加密结果
     */
    @SneakyThrows
    public static String encrypt(String publicKeyStr, String content){
        // 秘钥转换
        byte[] publicKeyByte = Base64.getDecoder().decode(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyByte));

        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptResult = cipher.doFinal(content.getBytes());
        String encryptResultStr = Base64.getEncoder().encodeToString(encryptResult);
        log.info("加密结果：{}", encryptResultStr);
        return encryptResultStr;
    }

    /**
     * 解密
     *
     * @param privateKeyStr 私钥
     * @param encryptResultStr 加密内容
     * @return 加密结果
     **/
    @SneakyThrows
    public static String decrypt(String privateKeyStr, String encryptResultStr){
        // 秘钥转换
        byte[] privateKeyByte = Base64.getDecoder().decode(privateKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));

        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] encryptResult = Base64.getDecoder().decode(encryptResultStr);
        byte[] decryptResult = cipher.doFinal(encryptResult);
        String decryptResultStr = new String(decryptResult);
        log.info("解密结果：{} ", decryptResultStr);
        return decryptResultStr;
    }

    public static void main(String[] args) {
        String content = "今天天气真好";
        List<String> secretKeyList = generateSecretKey();
        String encryptResultStr = encrypt(secretKeyList.get(0), content);
        decrypt(secretKeyList.get(1), encryptResultStr);
    }

}
