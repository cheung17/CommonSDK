package com.commonsdk.file;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.commonsdk.app.AppInfo;
import com.commonsdk.data.DataConstant;
import com.commonsdk.string.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文件管理工具类
 *
 * @author ztx
 */
public class FileManager {
    /**
     * 缓存目录
     */
    public static final String STUDYROOTDIR = "/mnt/sdcard/studycache";
    /**
     * 缓存目录
     */
    public static final String BOOKDIR = "book";
    /**
     * 缓存目录
     */
    public static final String ASKQUESTIONDIR = "myask";
    /**
     * 缓存目录
     */
    public static final String CAMERADIR = "camera";
    /**
     * 缓存目录
     */
    public static final String RECORDERDIR = "record";

    /**
     * 获取拍照的路径
     *
     * @return 获取拍照的路径
     */
    public static String getCameraCachePath() {
        String sCacheDir = FileManager.STUDYROOTDIR + "/" + FileManager.CAMERADIR;
        FileManager.createDirectory(FileManager.STUDYROOTDIR);
        FileManager.createDirectory(sCacheDir);
        return sCacheDir;
    }


    /**
     * 获取拍照的图片路径名
     *
     * @return 获取拍照的图片路径名
     */
    public static String newCameraJpegFile() {
        long dataTake = System.currentTimeMillis();
        String jpegName = FileManager.getCameraCachePath() + "/" + dataTake + ".jpg";
        return jpegName;
    }

    /**
     * 获取录音的路径
     *
     * @return 获取录音的路径
     */
    public static String getRecorderMp3CachePath() {
        String sCacheDir = FileManager.STUDYROOTDIR + "/" + FileManager.RECORDERDIR;
        FileManager.createDirectory(FileManager.STUDYROOTDIR);
        FileManager.createDirectory(sCacheDir);
        return sCacheDir;
    }

    /**
     * 判断文件是否存在。
     *
     * @param filePath 文件路径
     * @return 是否存在
     */
    public static boolean fileIsExist(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            return file.exists();
//            if (file.exists()) {
//                return true;
//            } else {
//                return false;
//            }
        } else {
            return false;
        }

    }

    /**
     * 判断路径是否为文件。
     *
     * @param filePath 文件路径
     * @return 是否为文件。
     */
    public static boolean isFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            return file.isFile();
//            if (file.isFile()) {
//                return true;
//            } else {
//                return false;
//            }
        } else {
            return false;
        }
    }

    /**
     * 判断路径是否为文件夹
     *
     * @param filePath 文件路径
     * @return 是否为文件夹。
     */
    public static boolean isDirectory(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            return file.isDirectory();
//            if (file.isDirectory()) {
//                return true;
//            } else {
//                return false;
//            }
        } else {
            return false;
        }
    }

    /**
     * 文件名改后缀
     *
     * @param fileName 文件名改后缀
     * @return 是否为文件夹。
     */
    public static String changeFileSuffix(String fileName, String suffixName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(0, fileName.lastIndexOf(".") + 1) + suffixName;
    }

    /**
     * 复制单个文件
     *
     * @param oldPath 旧的文件路径+名字。
     * @param newPath 新的文件路径+名字。
     * @param append  追加模式／覆盖模式。
     * @return 成功／失败
     */
    public static boolean copyFile(String oldPath, String newPath, boolean append) {
        if (!TextUtils.isEmpty(oldPath)) {
            InputStream in = null;
            try {
                in = new FileInputStream(oldPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (in != null) {
                return copyFile(in, newPath, append);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 复制单个文件
     *
     * @param inStream InputStream 流
     * @param newPath  String 复制后路径 如：f:/fqf.txt
     * @param append   追加模式／覆盖模式。
     * @return boolean 成功／失败
     */
    public static boolean copyFile(InputStream inStream, String newPath, boolean append) {
        if (TextUtils.isEmpty(newPath)) {
            return false;
        }
        if (inStream == null) {
            return false;
        }
        boolean success = false;
        FileOutputStream fs = null;
        try {
            int bytesum = 0;
            int byteread = 0;
            if (inStream != null) { // 文件存在时
                fs = new FileOutputStream(newPath, append);
                byte[] buffer = new byte[14440];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                success = true;
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (fs != null) {
                    fs.flush();
                    fs.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean 成功／失败
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        if (TextUtils.isEmpty(oldPath) || TextUtils.isEmpty(newPath)) {
            return false;
        }
        (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
        File a = new File(oldPath);
        if (a != null) {
            String[] file = a.list();
            if (file != null) {
                File temp = null;
                for (int i = 0; i < file.length; i++) {
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }
                    if (temp != null && temp.isFile()) {
                        FileInputStream input = null;
                        FileOutputStream output = null;
                        try {
                            input = new FileInputStream(temp);
                            output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                            byte[] b = new byte[1024 * 5];
                            int len;
                            while ((len = input.read(b)) != -1) {
                                output.write(b, 0, len);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            return false;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        } finally {
                            if (output != null) {
                                try {
                                    output.flush();
                                    output.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (input != null) {
                                try {
                                    input.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else if (temp != null && temp.isDirectory()) {// 如果是子文件夹
                        return copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 递归删除文件夹中的文件
     *
     * @param filePath 文件路径
     */
    public static void deleteFileOrDir(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            deleteFileOrDir(new File(filePath));
        }
    }

    /**
     * 递归删除文件夹中的文件
     *
     * @param file 要删除的根目录
     * @return 成功／失败
     */
    public static boolean deleteFileOrDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        } else if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                return file.delete();
                // return false;
            }
            boolean is = false;
            for (File f : childFile) {
                is = deleteFileOrDir(f);
            }
            file.delete();
            return is;
        } else {
            return false;
        }
    }

    /**
     * 创建文件夹
     *
     * @param filePath 文件路径
     * @return 成功／失败。
     */
    public static boolean createDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 传入文件路径，按文件名排序子文件
     *
     * @param fliePath 路径
     * @return 返回排序后的文件集合
     */
    public static List<File> orderByName(String fliePath) {
        List<File> files = Arrays.asList(new File(fliePath).listFiles());
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });
        return files;
    }

    /**
     * 打开文件
     *
     * @param path 文件
     */
    public static void openFile(Context context, String path) {

        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (AppInfo.isPackageExist(context, DataConstant.PACKAGE_WPS)
                && (path.endsWith(DataConstant.DOC_SUFFIX)
                || path.endsWith(DataConstant.PPT_SUFFIX)
                || path.endsWith(DataConstant.DOCX_SUFFIX)
                || path.endsWith(DataConstant.EXCEL_SUFFIX))) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            //   bundle.putString(OPEN_MODE, "NORMAL");
            bundle.putBoolean("SEND_CLOSE_BROAD", true);
            bundle.putString("THIRD_PACKAGE", context.getPackageName());
//            bundle.putBoolean(CLEAR_BUFFER, true);
            //          bundle.putBoolean(CLEAR_TRACE, true);
            //bundle.putBoolean(CLEAR_FILE, true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(DataConstant.PACKAGE_WPS, "cn.wps.moffice.documentmanager.PreStartActivity");
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            intent.putExtras(bundle);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//设置intent的Action属性
            intent.setAction(Intent.ACTION_VIEW);
            //获取文件file的MIME类型
            String type = getMIMEType(file);
//设置intent的data和Type属性。
            intent.setDataAndType(Uri.fromFile(new
                    File(path)
            ), type);
//跳转
            try

            {
                context.startActivity(intent); //这里最好try一下，有可能会报错。 //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
            } catch (
                    Exception e
                    )

            {
                Toast.makeText(context, " ，请安装后重试", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    public static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
//获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
//在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }


    public static final String[][] MIME_MapTable = {
//{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };
}