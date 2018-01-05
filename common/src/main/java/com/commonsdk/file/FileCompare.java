package com.commonsdk.file;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 文件比较
 *
 * @author ztx
 */
public class FileCompare {
    /**
     * 用MD5比较两个文件是否相同。
     *
     * @param src 文件对象。
     * @param des 文件对象。
     * @return 是否相同
     */
    public static boolean MD5Compare(File src, File des) {
        String md5_src = getFileMD5(src);
        String md5_des = getFileMD5(des);
        if (!TextUtils.isEmpty(md5_src) && !TextUtils.isEmpty(md5_des) && md5_src.equals(md5_des)) {
            return true;
        }
        return false;
    }

    /**
     * 用MD5比较两个文件是否相同。
     *
     * @param src 文件对象。
     * @param des 文件对象。
     * @return 是否相同
     */
    public static boolean MD5Compare(InputStream src, InputStream des) {
        String md5_src = getFileMD5(src);
        String md5_des = getFileMD5(des);
        if (!TextUtils.isEmpty(md5_src) && !TextUtils.isEmpty(md5_des) && md5_src.equals(md5_des)) {
            return true;
        }
        return false;
    }

    /**
     * 用MD5比较两个文件是否相同。
     *
     * @param src 文件对象。
     * @param des 文件对象。
     * @return 是否相同
     */
    public static boolean MD5Compare(InputStream src, File des) {
        String md5_src = getFileMD5(src);
        String md5_des = getFileMD5(des);
        if (!TextUtils.isEmpty(md5_src) && !TextUtils.isEmpty(md5_des) && md5_src.equals(md5_des)) {
            return true;
        }
        return false;
    }

    /**
     * 计算文件的 MD5 值，用于比较两个文件是否相同。
     *
     * @param filePath 文件路径
     * @return MD5值
     */
    public static String getFileMD5(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            return getFileMD5(new File(filePath));
        }
        return null;
    }

    /**
     * 计算文件的 MD5 值，用于比较两个文件是否相同。
     *
     * @param file 文件
     * @return MD5值
     */
    public static String getFileMD5(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return getFileMD5(in);
    }

    /**
     * 计算文件的 MD5 值，用于比较两个文件是否相同。
     *
     * @param in 输入流
     * @return MD5值
     */
    public static String getFileMD5(InputStream in) {
        if (in == null) {
            return null;
        }
        MessageDigest digest = null;
        byte[] buffer = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算文件的 MD5 值，用于比较两个文件是否相同。in读取之后，游标会停留在上一次使用的位置，因此，循环读取一个in对象时，只需要设置读取的长度，
     * 即可从上一次结束的位置继续读取。
     * （输入流read之后，游标会停留在上一次的问题，同一个输入流对象，反复使用的时候，需要注意游标位置。in.reset()貌似无用）
     *
     * @param in     输入流
     * @param length 读取文件的长度
     * @return 文件一段流的MD5值
     */
    public static String getPartFileMD5(InputStream in, int length) {
        if (in == null) {
            return null;
        }
        MessageDigest digest = null;
        byte[] buffer = new byte[length];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            // if ((len = in.read(buffer, start, length)) > 0) {
            // digest.update(buffer, 0, len);
            // BigInteger bigInt = new BigInteger(1, digest.digest());
            // return bigInt.toString(16);
            // }
            if ((len = in.read(buffer)) > 0) {
                digest.update(buffer, 0, len);
                BigInteger bigInt = new BigInteger(1, digest.digest());
                return bigInt.toString(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件某段流的MD5值（输入流read之后，游标会停留在上一次的问题，同一个输入流对象，反复使用的时候，需要注意游标位置。in.reset()貌似无用）
     *
     * @param in     输入流
     * @param start  跳过的字节长度
     * @param length 读取文件的长度
     * @return 文件一段流的MD5值
     */
    public static String getPartFileMD5(InputStream in, long start, int length) {
        if (in == null) {
            return null;
        }
        MessageDigest digest = null;
        byte[] buffer = new byte[length];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            if (in.skip(start) > 0) {
                if ((len = in.read(buffer)) > 0) {
                    digest.update(buffer, 0, len);
                    BigInteger bigInt = new BigInteger(1, digest.digest());
                    return bigInt.toString(16);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用sha1比较两个文件是否相同。
     *
     * @param src 文件对象。
     * @param des 文件对象。
     * @return 是否相同
     */
    public static boolean ShaCompare(File src, File des) {
        String sha_src = getFileSha1(src);
        String sha_des = getFileSha1(des);
        if (!TextUtils.isEmpty(sha_src) && !TextUtils.isEmpty(sha_des) && sha_src.equals(sha_des)) {
            return true;
        }
        return false;
    }

    /**
     * 用sha1比较两个文件是否相同。
     *
     * @param src 文件输入流
     * @param des 文件输入流
     * @return 是否相同
     */
    public static boolean ShaCompare(InputStream src, InputStream des) {
        String sha_src = getFileSha1(src);
        String sha_des = getFileSha1(des);
        if (!TextUtils.isEmpty(sha_src) && !TextUtils.isEmpty(sha_des) && sha_src.equals(sha_des)) {
            return true;
        }
        return false;
    }

    /**
     * 用sha1比较两个文件是否相同。
     *
     * @param src 文件输入流
     * @param des 文件对象
     * @return 是否相同
     */
    public static boolean ShaCompare(InputStream src, File des) {
        String sha_src = getFileSha1(src);
        String sha_des = getFileSha1(des);
        if (!TextUtils.isEmpty(sha_src) && !TextUtils.isEmpty(sha_des) && sha_src.equals(sha_des)) {
            return true;
        }
        return false;
    }

    /**
     * 计算文件的 SHA-1 值，用于比较两个文件是否相同。
     *
     * @param file 文件
     * @return SHA-1 值
     */
    public static String getFileSha1(File file) {
        if (!file.isFile()) {
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return getFileSha1(in);
    }

    /**
     * 计算文件的 SHA-1 值，用于比较两个文件是否相同。
     *
     * @param in 输入流
     * @return SHA-1 值
     */
    public static String getFileSha1(InputStream in) {
        if (in == null) {
            return null;
        }
        MessageDigest digest = null;
        byte[] buffer = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
