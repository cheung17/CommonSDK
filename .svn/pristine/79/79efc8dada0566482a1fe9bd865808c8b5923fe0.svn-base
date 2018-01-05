package com.commonsdk.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5值加密
 *
 * @author ztx
 */
public class MD5Crypto {
    /**
     * Md5 32位 or 16位 加密(只返回8~24之间的字符)
     *
     * @param plainText 待加密内容
     * @return 32位加密
     */
    public static String Md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // Log.e("555","result: " + buf.toString());//32位的加密
            // Log.e("555","result: " + buf.toString().substring(8,24));//16位的加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (buf == null) {
            return null;
        }
        return buf.toString().substring(8, 24);
    }

    /**
     * Md5 32位 or 16位 加密（全部返回）
     *
     * @param plainText 待加密内容
     * @return 32位加密
     */
    public static String Md5PassWord(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // Log.e("555","result: " + buf.toString());//32位的加密
            // Log.e("555","result: " + buf.toString().substring(8,24));//16位的加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (buf == null) {
            return null;
        }
        return buf.toString().toLowerCase();
    }

    /**
     * 附：java String 转化成 MD5 byte[]
     *
     * @param hex 字符串
     * @return byte数组
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * 字符转byte
     *
     * @param c 字符
     * @return byte
     */
    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }

    /**
     * 获取文件md5
     *
     * @param file 文件
     * @return md5值
     */
    public static String getMd5ByFile(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
