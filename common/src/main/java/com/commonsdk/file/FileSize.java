package com.commonsdk.file;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * 文件大小工具类
 *
 * @author ztx
 */
public class FileSize {
    /**
     * 获取文件大小
     *
     * @param f 文件
     * @return 长度
     * @throws Exception
     */
    public static long getFileSize(File f) throws Exception {
        if (f == null) {
            return -1;
        }
        if (f.isDirectory()) {
            return getFolderSize(f);
        }
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                s = fis.available();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            f.createNewFile();
            System.out.println("文件不存在");
        }
        return s;
    }

    /**
     * 格式化文件大小单位(b/kb/mb/gb)
     *
     * @param fileS 文件大小，单位b
     * @return 格式化后的文件大小
     */
    public static String formetFileSize(long fileS) {// 转换文件大小
        if (fileS < 0) {
            fileS = 0;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取文件夹大小
     *
     * @param f 文件夹路径。
     * @return 文件夹大小
     * @throws Exception
     */
    public static long getFolderSize(File f) throws Exception {
        if (f == null) {
            return -1;
        }
        if (f.isFile()) {
            return getFileSize(f);
        }
        long size = 0;
        File[] flist = f.listFiles();
        if (flist != null) {
            for (int i = 0; i < flist.length; i++) {
                if (flist[i] != null) {
                    if (flist[i].isDirectory()) {
                        size = size + getFolderSize(flist[i]);
                    } else {
                        size = size + getFileSize(flist[i]);// flist[i].length();调用最上面的getFileSize方法。
                    }
                }
            }
        }
        return size;
    }

    /**
     * 获取文件个数
     *
     * @param f 文件夹
     * @return 文件个数
     */
    public static long getlist(File f) {// 递归求取目录文件个数
        if (f == null) {
            return -1;
        }
        if (f.isFile()) {
            return 1;
        }
        long size = 0;
        File[] flist = f.listFiles();
        if (flist != null) {
            size = flist.length;
            for (int i = 0; i < flist.length; i++) {
                if (flist[i].isDirectory()) {
                    size = size + getlist(flist[i]);
                    size--;
                }
            }
        }
        return size;
    }
}
