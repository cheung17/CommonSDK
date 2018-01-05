package com.commonsdk.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密，支持java和android互通。
 *
 * @author 蛟
 */
public class AES {
    /**
     * main
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        String content = "AEStest";
        String password = "!QIMON@USERINFOS";
        AES aes = new AES();
        System.out.println("密　钥：" + password);
        System.out.println("加密前：" + content);

        // 加密
        // aes.init_enc();
        // String encryptResult = aes.encrypt(content );
        // System.out.println("加密后：" + encryptResult);
        // 解密
        aes.init_dec();
        String decryptResult = aes
                .decrypt("5460D32862CE24257851E2530FE2B7EA17EDEC7FB0F43743253CA01B1916180178085D8C92D996B4696D61D52EEFF6B5");
        System.out.println("解密后：" + decryptResult);
    }

    /**
     * 加密的KEY
     */
    private static String key_password = "!QIMON@USERINFOS";
    /**
     * Cipher对象
     */
    private static Cipher cipher;

    /**
     * 加密初始化
     */
    public void init_enc() {
        KeyGenerator kgen;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key_password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解密初始化
     */
    public void init_dec() {
        KeyGenerator kgen;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key_password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    /**
     * 加密
     *
     * @param content 待加密内容
     * @return 密文
     */
    public String encrypt(String content) {
        try {
            byte[] byteContent = content.getBytes("utf-8");
            byte[] byteRresult = cipher.doFinal(byteContent);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteRresult.length; i++) {
                String hex = Integer.toHexString(byteRresult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
            // return byteRresult.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return 明文
     */
    public String decrypt(String content) {
        if (content.length() < 1) {
            return null;
        }
        byte[] byteRresult = new byte[content.length() / 2];
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
        try {
            byte[] result = cipher.doFinal(byteRresult);
            return new String(result);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

}