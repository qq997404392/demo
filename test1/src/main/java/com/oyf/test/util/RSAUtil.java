package com.oyf.test.util;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/9/28
 * @description：签名与验签
 */
@Slf4j
public class RSAUtil {

    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();
    private static String ALGORITHM = "SHA256withRSA";

    /**
     * 随机生成公私钥
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // 获取指定算法的密钥对生成器
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        gen.initialize(1024, new SecureRandom());
        // 随机生成一对密钥（包含公钥和私钥）
        KeyPair keyPair = gen.generateKeyPair();

        // 将公钥和私钥保存到Map
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        keyMap.put(0, org.apache.commons.codec.binary.Base64.encodeBase64String(publicKey.getEncoded()));
        keyMap.put(1, org.apache.commons.codec.binary.Base64.encodeBase64String(privateKey.getEncoded()));
    }

    /**
     * 生成签名
     *
     * @param content    待加密字符串
     * @param privateKey 加密密钥
     * @return 加密后密文
     * @throws Exception
     */
    public static String sign(String content, String privateKey) throws Exception {
        try {
            byte[] dataEncode = Base64.encodeBase64(content.getBytes("UTF-8"));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(keySpec);
            Signature signatureChecker;
            Security.addProvider(new BouncyCastleProvider());
            signatureChecker = Signature.getInstance(ALGORITHM);
            signatureChecker.initSign(priKey);
            signatureChecker.update(dataEncode);
            byte[] sign = signatureChecker.sign();
            return new String(Base64.encodeBase64(sign));
        } catch (Exception e) {
            log.error("加密失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证签名
     *
     * @param data       实际数据
     * @param signedText 加密后待验证数据
     * @param publicKey  加密公钥
     * @return
     */
    public static boolean verify(String data, String signedText, String publicKey) {
        try {
            String dataEncode = new String(Base64.encodeBase64(data.getBytes("UTF-8")));
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Signature signatureChecker;
            Security.addProvider(new BouncyCastleProvider());
            signatureChecker = Signature.getInstance(ALGORITHM);
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(dataEncode.replace("\n", "").getBytes());
            byte[] signBytes = signedText.getBytes("UTF-8");
            byte[] sign = Base64.decodeBase64(signBytes);
            return signatureChecker.verify(sign);
        } catch (Exception e) {
            log.error("验签失败");
        }
        return false;
    }

    public static void main(String args[]) {
        try {
            genKeyPair();
            String publicKey = keyMap.get(0);
            String privateKey = keyMap.get(1);
            System.out.println("随机生成的公钥为: " + privateKey);
            System.out.println("随机生成的私钥为: " + publicKey);
            String singedText = sign("hello=world&我爱=中国", privateKey);
            boolean flag = verify("hello=world&我爱=中国", singedText, publicKey);
            System.out.println("验签结果: " + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
