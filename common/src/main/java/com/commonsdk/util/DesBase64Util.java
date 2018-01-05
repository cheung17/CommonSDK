package com.commonsdk.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yjzx13 on 2016/12/26.
 */
public class DesBase64Util {
    //加密
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64.encodeToString(encryptedData, Base64.DEFAULT).replaceAll("%", "%25").replaceAll("\\+", "%2B")
                .replaceAll(" ", "%20").replaceAll("/", "%2F").replaceAll("\\?", "%3F")
                .replaceAll("#", "%23").replaceAll("&", "%26").replaceAll("=", "%3D");
    }

    //获取解码内容
    public static String DEStoStr(String ciphertext) throws Exception {
        ciphertext = ciphertext.replaceAll("%2B", "+").replaceAll("%20", " ").replaceAll("%2F", "/")
                .replaceAll("%3F", "\\?").replaceAll("%23", "#").replaceAll("%26", "&")
                .replaceAll("%3D", "=").replaceAll("%25", "%");
        return decryptDES(ciphertext, "leiyonyj");
    }

    public static String decryptDES(String decryptString, String decryptKey) {
        try {
            decryptString = decryptString.replaceAll("%2B", "+").replaceAll("%20", " ").replaceAll("%2F", "/")
                    .replaceAll("%3F", "\\?").replaceAll("%23", "#").replaceAll("%26", "&")
                    .replaceAll("%3D", "=").replaceAll("%25", "%");
            if (decryptString.equals("null")) {
                return "";
            }
            byte[] byteMi = Base64.decode(decryptString, Base64.DEFAULT);
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte decryptedData[] = cipher.doFinal(byteMi);
            return new String(decryptedData);
        } catch (Exception e) {
            System.out.println("出错了" + e.getMessage());
            return "";
        }

    }


}
