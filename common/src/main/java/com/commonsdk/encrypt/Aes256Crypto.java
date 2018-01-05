package com.commonsdk.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 秘钥一定要是16的倍数
 *
 * @author ztx
 */
public class Aes256Crypto {
    /**
     * 加密
     *
     * @param seed      秘钥
     * @param cleartext 待加密的内容
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String seed, String cleartext) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    /**
     * 秘钥一定要是16的倍数（解密）
     *
     * @param seed      秘钥
     * @param encrypted 密文
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        /*
         * KeyGenerator kgen = KeyGenerator.getInstance("AES"); SecureRandom sr
		 * = SecureRandom.getInstance("SHA1PRNG"); sr.setSeed(seed);
		 * kgen.init(128, sr); // 192 and 256 bits may not be available
		 * SecretKey skey = kgen.generateKey(); byte[] raw = skey.getEncoded();
		 * return raw;
		 */
        return seed;
    }

    /**
     * 加密
     *
     * @param raw   RawKey
     * @param clear 明文
     * @return 密文
     * @throws Exception
     */
    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    /**
     * 解密
     *
     * @param raw       RawKey
     * @param encrypted 密文
     * @return 明文
     * @throws Exception
     */
    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    /**
     * 字符串转字节数组
     *
     * @param hexString 字符串
     * @return 字节数组
     */
    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    /**
     * HEX
     */
    private static final String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
