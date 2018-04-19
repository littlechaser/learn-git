package com.test;

import com.google.common.io.BaseEncoding;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Author   yang_tao@<yangtao.letzgo.com.cn>
 * Date     2018-01-09 13:00
 * Version  1.0
 */
public class RSA {

    private static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static Map<String, Key> keyPairContainer = new HashMap<>();

    public static byte[] encode(byte[] content) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPairContainer.get(PUBLIC_KEY));
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decode(byte[] content) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPairContainer.get(PRIVATE_KEY));
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Key> generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        instance.initialize(1024);
        KeyPair keyPair = instance.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyPairContainer.put(PUBLIC_KEY, publicKey);
        keyPairContainer.put(PRIVATE_KEY, privateKey);
        BaseEncoding base64 = BaseEncoding.base64();
        System.out.println(base64.encode(publicKey.getEncoded()));
        System.out.println(base64.encode(privateKey.getEncoded()));
        return keyPairContainer;
    }

    private static Map<String, Key> loadKeyPair() throws Exception {
        BaseEncoding base64 = BaseEncoding.base64();
        byte[] publicBuffer = base64.decode(RSA.publicKey);
        byte[] privateBuffer = base64.decode(RSA.privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBuffer);
        PKCS8EncodedKeySpec keySpec2 = new PKCS8EncodedKeySpec(privateBuffer);
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec2);
        keyPairContainer.put(PUBLIC_KEY, publicKey);
        keyPairContainer.put(PRIVATE_KEY, privateKey);
        return keyPairContainer;
    }


    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX432NGLAadVG1HwHcsYa5oT3RFprQE6XoDGWyWMTmqI3JJIaABOtzbS64T0INUIZBvrYDSMO0rRgyGk5LpE56ghe9IvuUXBHhzdCjcF5TDsuFuML+yvwOZ17hzJscmuV/BEAcCw0DC5IRbAvChUUbgCkpFiasuw6m6xNdAkO9hwIDAQAB";
    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJfjfY0YsBp1UbUfAdyxhrmhPdEWmtATpegMZbJYxOaojckkhoAE63NtLrhPQg1QhkG+tgNIw7StGDIaTkukTnqCF70i+5RcEeHN0KNwXlMOy4W4wv7K/A5nXuHMmxya5X8EQBwLDQMLkhFsC8KFRRuAKSkWJqy7DqbrE10CQ72HAgMBAAECgYBCr8jh+CLpmvTi1thUc0U5n0eq04fpL5c6CKdl5H+e3gZSd8ztzDssFJXcqj/P5LjtiLQVQQ9b9luXM+GIaH1jsMeH/DAl7uPr+nKmCMAUzHntdOqEsOyDMy49bjuRICaiQYPN3CpksqATJoIncLR0sheHYlQxnZqlN0DEFgDzYQJBAOjnCeTPXGmia/bhxcquYmcWBr/pDrm1ntZhMj0k7jShaQscU/qLPHPf6b9cniCPn0lZjAnrvHRpYOPjPuafHTkCQQCm86kVx47Fj4RftXDX3WhHNN1qDlOKPqim2Maigo29y8cuRYq/RpqCFlqmEi7iVZl65/E7UQVvREEVETu+5HC/AkAN41kPr93O3sSZYDUvNN7hTsrwPrGGGXJzTjB0vPMHaGifJP1M/2iHcF677oXvYyEYeqKDJO+D8ZS5gh+KGQrhAkEAjxhRWKWOra5xG0d8zMis1Dtve7ODzv4dt8QdnLShoIfE1NguL/vn+pAGakkzfjM4NawPi3PKYK/zyIbY2qkaKQJAFBg49iG/aZINJzhGPDkjmr30cJWxOrD2/dRMLd24CjVWbsGLs0i96iXckmmuf3rMuKoDu2K1WOYdHONNr5nLLw==";

    public static void main(String[] args) throws Exception {
//        System.out.println(generateKeyPair().get("publicKey"));
//        System.out.println(generateKeyPair().get("privateKey"));
        String msg = "this is a test message";
//        generateKeyPair();
        loadKeyPair();
        byte[] encode = encode(msg.getBytes("UTF-8"));
        System.out.println("加密结果：" + (new String(encode, "UTF-8")));
        byte[] decode = decode(encode);
        System.out.println("解密结果：" + (new String(decode, "UTF-8")));
    }

}
