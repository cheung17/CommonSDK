package com.commonsdk.updownloader.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.commonsdk.file.FileCompare;
import com.commonsdk.updownloader.myreceiver.DownloaderReceiver;
import com.commonsdk.updownloader.myreceiver.UploaderReceiver;
import com.commonsdk.updownloader.utils.StringHelpter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 学生端上传，下载工具类
 *
 * @author fanjiao
 */
public class UpDownloadUtils {
    /**
     * 添加失败
     */
    public static final int fail = -1;
    /**
     * 已经存在
     */
    public static final int exist = 0;
    /**
     * 添加成功
     */
    public static final int success = 1;
    /**
     * 数据库协议
     */
    public static final Uri CONTENT_URI = Uri.parse("content://com.qimon.updownloader");
    /**
     * 下载结束
     */
    public static final String INTENT_DOWNLOAD_END = "updownloader.download.end";
    /**
     * 上传完成
     */
    public static final String INTENT_UPLOAD_END = "updownloader.upload.end";
    /**
     * 传递任务对象。
     */
    public static final String OBJECT = "object";
    /**
     * 执行后的状态。（－1表示添加失败；0表示任务已经存在；1表示添加成功）
     */
    public static final String STATUS = "status";

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，但是不打开。
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, model, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，但是不打开。
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param model         标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, model, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验，也不打开。
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, model, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验，也不打开。
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param model         标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, model, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验,直接打开文件。
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, true, model, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验,直接打开文件。
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param model         标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, true, model, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，并打开文件。
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisible(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, true, true, model, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，并打开文件。
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param model         标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisible(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, true, true, model, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个不显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验，直接打开文件。
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataInvisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, false, true, model, "", "", "", "");
    }

    /**
     * 添加一个不显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，并打开文件。
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataInvisible(Context context, String
            filePath, String fileName, String md5, String url, long filelength, String model) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, true, true, model, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，但是不打开。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，但是不打开。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, DataBean.STUDENT, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验，也不打开。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验，也不打开。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSumNoOpen(Context context, String
            filePath, String fileName, String md5, String url, long filelength
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false, DataBean.STUDENT, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验,直接打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, true, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验,直接打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, true, DataBean.STUDENT, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，并打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisible(Context context, String
            filePath, String fileName, String md5, String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, true, true, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 添加一个显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，并打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisible(Context context, String
            filePath, String fileName, String md5, String url, long filelength
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, true, true, DataBean.STUDENT, sourceName, sourcePkg, sourceClass, targetContent);
    }

    /**
     * 添加一个不显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验，直接打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataInvisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, false, true, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 添加一个不显示在列表中的下载项，并且下载完毕之后，不进行MD5值校验， 打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @param open  是否打开
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataInvisibleNotCheckSum(Context context, String
            filePath, String fileName, String md5, String url, long filelength,boolean open) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, false, open, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 添加一个不显示在列表中的下载项，并且下载完毕之后，进行MD5值校验，并打开文件。(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    句柄
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataInvisible(Context context, String
            filePath, String fileName, String md5, String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, true, true, DataBean.STUDENT, "", "", "", "");
    }

    /**
     * 插入数据的执行体。
     *
     * @param context       句柄
     * @param filePath      存储的文件夹
     * @param fileName      存储的文件名
     * @param md5           md5值
     * @param url           下载的地址
     * @param filelength    文件长度
     * @param visible       是否出现在上传下载列表中
     * @param needCheckMD5  下载完毕是否要检验MD5值
     * @param isOpenFile    下载完毕是否打开文件
     * @param model         标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param sourceClass   用于打开任务文件的类。
     * @param sourceName    任务来源的应用名。
     * @param sourcePkg     用于打开任务文件的应用包名。
     * @param targetContent 打开任务文件是返回给目标类的数据，放在Intent的Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}，代码示例{String target = getIntent().getExtras().getString(DownloadRecords.TARGETCONTENT);}
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDataBody(Context context, String
            filePath, String fileName, String md5, String url,
                                                         long filelength, boolean visible, boolean needCheckMD5, boolean isOpenFile, String model
            , String sourceName, String sourcePkg, String sourceClass, String targetContent) throws Exception {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(md5) || TextUtils.isEmpty(url)) {
            throw new Exception("参数不能为空。");
        }
        File file = new File(filePath);
        if (file.isFile()) {
            filePath = file.getParent();
            file = new File(filePath);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        DownloadRecords records = queryDownloadData(context, md5, model);
        if (records != null) {
            //更新下载项的MD5值检查需求。
            if (needCheckMD5) {
                records.needCheckMD5 = DownloadRecords.NEEDCHECKMD5_YES;
            } else {
                records.needCheckMD5 = DownloadRecords.NEEDCHECKMD5_NO;
            }
            records.OPEN_FILE_AFTER_FINISHED = isOpenFile;
            // 正在下载则不重复添加数据。
            /** 当前状态：0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。 */
            if (StringHelpter.STATUS_OK.equals(records.STATUS)) {
                File downloadFile = new File(new File(records.FILE_PATH), records.FILE_NAME);
                if (downloadFile != null && downloadFile.exists()) {
                    String downloadMd5 = FileCompare.getFileMD5(downloadFile);
                    if (downloadMd5 != null && downloadMd5.equals(md5)) {
                        Intent intent = new Intent(getEndDownloadBroadcastAction4Model(model));
                        intent.putExtra(OBJECT, records);
                        context.sendBroadcast(intent);
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put(STATUS, exist);
                        map.put(OBJECT, records);
                        return map;
                    } else {
                        if (downloadFile != null && downloadFile.exists()) {
                            downloadFile.delete();
                        }
                        records.FILE_DOWNLOAD_SIZE = 0;
                        records.STATUS = StringHelpter.STATUS_NORMAL;
                        records.upDownProcessItems = disposeBlocks(records.FILE_SIZE);//upDownProcessItemUtils.getFileBlockList(records.FILE_SIZE);
                        records.sourcePkg = sourcePkg;
                        records.sourceClass = sourceClass;
                        records.sourceName = sourceName;
                        records.targetContent = targetContent;
                        return updateDownloadData(context,
                                buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible), model);
                    }
                } else {
                    // 重新下载。
                    records.FILE_DOWNLOAD_SIZE = 0;
                    records.STATUS = StringHelpter.STATUS_NORMAL;
                    records.upDownProcessItems = disposeBlocks(records.FILE_SIZE);//upDownProcessItemUtils.getFileBlockList(records.FILE_SIZE);
                    records.sourcePkg = sourcePkg;
                    records.sourceClass = sourceClass;
                    records.sourceName = sourceName;
                    records.targetContent = targetContent;
                    return updateDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible), model);
                }
            } else if (StringHelpter.STATUS_PAUSE.equals(records.STATUS)) {
                // 继续下载。
                records.STATUS = StringHelpter.STATUS_NORMAL;
                if (visible) {
                    records.available = 1;
                    records.NOTIFY_PROGRESS = true;
                    records.NOTIFY_RESULT = true;
                } else {
                    records.available = -1;
                    records.NOTIFY_PROGRESS = false;
                    records.NOTIFY_RESULT = true;
                }
                records.sourcePkg = sourcePkg;
                records.sourceClass = sourceClass;
                records.sourceName = sourceName;
                records.targetContent = targetContent;
//                return updateDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible));
                return updateDownloadData(context, records, model);
            } else if (StringHelpter.STATUS_FAILED.equals(records.STATUS)) {
//                File downloadFile = new File(new File(records.FILE_PATH), records.FILE_NAME);
//                if (downloadFile != null && downloadFile.exists()) {
//                    downloadFile.delete();
//                }
//                records.FILE_SIZE = 0;
//                records.FILE_DOWNLOAD_SIZE = 0;
                records.STATUS = StringHelpter.STATUS_NORMAL;
//                records.upDownProcessItems = ;//;upDownProcessItemUtils.getFileBlockList(records.FILE_SIZE);
                records.sourcePkg = sourcePkg;
                records.sourceClass = sourceClass;
                records.sourceName = sourceName;
                records.targetContent = targetContent;
                return updateDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible), model);
            } else if (StringHelpter.STATUS_ING.equals(records.STATUS) || StringHelpter.STATUS_NORMAL.equals(records.STATUS)) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put(STATUS, exist);
                map.put(OBJECT, records);
                return map;
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, records);
            return map;
        } else {
            // 新增下载项。
            File downloadFile = new File(new File(filePath), fileName);
            if (downloadFile != null && downloadFile.exists()) {
                downloadFile.delete();
            }
            records = new DownloadRecords();
//            records.upDownProcessItems = upDownProcessItemUtils.getFileBlockList(filelength);
            if (needCheckMD5) {
                records.needCheckMD5 = DownloadRecords.NEEDCHECKMD5_YES;
            } else {
                records.needCheckMD5 = DownloadRecords.NEEDCHECKMD5_NO;
            }
            records.OPEN_FILE_AFTER_FINISHED = isOpenFile;
            records.sourcePkg = sourcePkg;
            records.sourceClass = sourceClass;
            records.sourceName = sourceName;
            records.targetContent = targetContent;
            return insertDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible), model);
        }
    }

    /**
     * 构建一个下载对象。
     *
     * @param records    DownloadRecords
     * @param filePath   文件夹路径
     * @param fileName   文件名
     * @param md5        md5值
     * @param url        下载路径
     * @param filelength 文件大小
     * @param visible    是否出现在下载管理列表界面
     * @return DownloadRecords
     * @throws Exception
     */
    private static DownloadRecords buildDownloadRecords(DownloadRecords records, String filePath, String fileName, String md5, String url,
                                                        long filelength, boolean visible) throws Exception {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(md5) || TextUtils.isEmpty(url)) {
            throw new Exception("参数不能为空。");
        }
        if (records == null) {
            records = new DownloadRecords();
        }
        records.FILE_PATH = filePath;
        records.FILE_NAME = fileName;
        records.downUrl = url;
        records.FILE_CHECKSUM = md5;
        if (filelength > 0) {
            records.FILE_SIZE = filelength;
        }
        if (visible) {
            records.available = 1;
            records.NOTIFY_PROGRESS = true;
            records.NOTIFY_RESULT = true;
        } else {
            records.available = -1;
            records.NOTIFY_PROGRESS = false;
            records.NOTIFY_RESULT = true;
        }
        records.SHOWED_FILE_AFTER_FINISHED = true;
        records.ISDELETE = false;
        records.DELETEFILE = false;
        return records;
    }

    /**
     * 向数据库中插入一条下载数据。
     *
     * @param context 上下文
     * @param records DownloadRecords
     * @param model   标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     */
    private static HashMap<String, Object> insertDownloadData(Context context, DownloadRecords records, String model) {
        if (records == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        Object2SQ(records, values);
//        Uri downuri = cr.insert(Uri.withAppendedPath(CONTENT_URI, "downloaditem"), values);
        String uri = getUri4Model(model, true);
        if (TextUtils.isEmpty(uri)) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        Uri downuri = cr.insert(Uri.parse(uri), values);
        if (downuri != null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, success);
            map.put(OBJECT, records);
            return map;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(STATUS, fail);
        map.put(OBJECT, records);
        return map;
    }

    /**
     * 更新数据库中的一条下载数据。
     *
     * @param context 上下文
     * @param records DownloadRecords
     * @param model   标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     */
    private static HashMap<String, Object> updateDownloadData(Context context, DownloadRecords records, String model) {
        if (records == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        Object2SQ(records, values);
        String where = DataBean.Download_Records.FILE_CHECKSUM + " = ?";
        String[] selectionArgs = new String[]{records.FILE_CHECKSUM};
//        int count = cr.update(Uri.withAppendedPath(CONTENT_URI, "downloaditem"), values, where, selectionArgs);
        String uri = getUri4Model(model, true);
        if (TextUtils.isEmpty(uri)) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        int count = cr.update(Uri.parse(uri), values, where, selectionArgs);
        if (count > 0) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, success);
            map.put(OBJECT, records);
            return map;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(STATUS, fail);
        map.put(OBJECT, records);
        return map;
    }

    /**
     * 查询数据库中的一条数据。
     *
     * @param context 上下文
     * @param md5     md5值
     * @param model   标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return DownloadRecords
     * @throws Exception
     */
    public static DownloadRecords queryDownloadData(Context context, String md5, String model) throws Exception {
        DownloadRecords records = new DownloadRecords();
        if (TextUtils.isEmpty(md5) || context == null) {
            throw new Exception("参数不能为空。");
        }
        ContentResolver cr = context.getContentResolver();
        String selection = DataBean.Download_Records.FILE_CHECKSUM + " = ?";
        String[] selectionArgs = new String[]{md5};
//        Cursor c = cr.query(Uri.withAppendedPath(CONTENT_URI, "downloaditem"), null, selection, selectionArgs, null);
        String uri = getUri4Model(model, true);
        if (TextUtils.isEmpty(uri)) {
            return null;
        }
        Cursor c = cr.query(Uri.parse(uri), null, selection, selectionArgs, null);
        if (c != null && c.moveToFirst()) {
            SQ2Object(c, records);
            c.close();
            return records;
        }
        if (c != null) {
            c.close();
        }
        return null;
    }

    /**
     * sqlite转化为对象。
     *
     * @param cursor  数据库游标
     * @param records DownloadRecords
     */
    public static void SQ2Object(Cursor cursor, DownloadRecords records) {
        records.UUID = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.UUID));
        records.FILE_NAME = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.FILE_NAME));
        records.FILE_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Download_Records.FILE_SIZE));
        records.FILE_PATH = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.FILE_PATH));
        records.FILE_CHECKSUM = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.FILE_CHECKSUM));
        records.FILE_DOWNLOAD_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Download_Records.FILE_DOWNLOAD_SIZE));
        records.STATUS = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.STATUS));
        records.START_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.START_TIME));
        records.END_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.END_TIME));
        records.REQUEST_CONTENT = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.REQUEST_CONTENT));
        records.downUrl = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.DOWNURL));
        records.OPEN_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.OPEN_FILE_AFTER_FINISHED)) > 0;
        records.SHOWED_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.SHOWED_FILE_AFTER_FINISHED)) > 0;
        records.NOTIFY_PROGRESS = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.NOTIFY_PROGRESS)) > 0;
        records.NOTIFY_RESULT = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.NOTIFY_RESULT)) > 0;
        records.AUTO_START_AFTER_NETWORK_CONNECTED = cursor
                .getInt(cursor.getColumnIndex(DataBean.Download_Records.AUTO_START_AFTER_NETWORK_CONNECTED)) > 0;
        records.available = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.AVAILABLE));
        records.upDownProcessItems = upDownProcessItemUtils
                .String2upDownProcessItemList(cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.UPDOWNPROCESSITEM)));
        records.needCheckMD5 = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.NEEDCHECKMD5));
        records.sourceClass = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.SOURCECLASS));
        records.sourceName = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.SOURCENAME));
        records.sourcePkg = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.SOURCEPKG));
        records.targetContent = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.TARGETCONTENT));
    }

    /**
     * 对象转化为sqlite数据。
     *
     * @param records DownloadRecords
     * @param values  ContentValues
     */
    public static void Object2SQ(DownloadRecords records, ContentValues values) {
        values.put(DataBean.Download_Records.UUID, records.UUID);
        values.put(DataBean.Download_Records.FILE_NAME, records.FILE_NAME);
        values.put(DataBean.Download_Records.FILE_SIZE, records.FILE_SIZE);
        values.put(DataBean.Download_Records.FILE_PATH, records.FILE_PATH);
        values.put(DataBean.Download_Records.FILE_CHECKSUM, records.FILE_CHECKSUM);
        values.put(DataBean.Download_Records.FILE_DOWNLOAD_SIZE, records.FILE_DOWNLOAD_SIZE);
        values.put(DataBean.Download_Records.STATUS, records.STATUS);
        values.put(DataBean.Download_Records.START_TIME, records.START_TIME);
        values.put(DataBean.Download_Records.END_TIME, records.END_TIME);
        values.put(DataBean.Download_Records.REQUEST_CONTENT, records.REQUEST_CONTENT);
        values.put(DataBean.Download_Records.OPEN_FILE_AFTER_FINISHED, records.OPEN_FILE_AFTER_FINISHED);
        values.put(DataBean.Download_Records.SHOWED_FILE_AFTER_FINISHED, records.SHOWED_FILE_AFTER_FINISHED);
        values.put(DataBean.Download_Records.NOTIFY_PROGRESS, records.NOTIFY_PROGRESS);
        values.put(DataBean.Download_Records.NOTIFY_RESULT, records.NOTIFY_RESULT);
        values.put(DataBean.Download_Records.AUTO_START_AFTER_NETWORK_CONNECTED, records.AUTO_START_AFTER_NETWORK_CONNECTED);
        values.put(DataBean.Download_Records.DOWNURL, records.downUrl);
        values.put(DataBean.Download_Records.AVAILABLE, records.available);
        values.put(DataBean.Download_Records.UPDOWNPROCESSITEM,
                upDownProcessItemUtils.upDownProcessItemList2String(records.upDownProcessItems));
        values.put(DataBean.Download_Records.NEEDCHECKMD5, records.needCheckMD5);
        values.put(DataBean.Download_Records.SOURCECLASS, records.sourceClass);
        values.put(DataBean.Download_Records.SOURCENAME, records.sourceName);
        values.put(DataBean.Download_Records.SOURCEPKG, records.sourcePkg);
        values.put(DataBean.Download_Records.TARGETCONTENT, records.targetContent);
    }
    // *************************************************************************************************************************以下是上传工具函数

    /**
     * 添加一个显示在列表中的上传项(不会默认开始上传)
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertUpDataWaitVisible(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            String param, String model) throws Exception {//modifly by flex,增加param参数，如果参数不为空则使用该值为云端数据库id
        return insertUpDataBody(
                context,
                filePath,
                fileName,
                md5,
                url,
                filelength,
                true,
                param, model, false);
    }

    /**
     * 添加一个不显示在列表中的上传项(不会默认开始上传)
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertUpDataWaitInvisible(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            String param, String model) throws Exception {
        return insertUpDataBody(
                context,
                filePath,
                fileName,
                md5,
                url,
                filelength,
                false,
                param, model, false);
    }


    /**
     * 添加一个显示在列表中的上传项，直接上传
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertUpDataVisible(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            String param, String model) throws Exception {//modifly by flex,增加param参数，如果参数不为空则使用该值为云端数据库id
        return insertUpDataBody(
                context,
                filePath,
                fileName,
                md5,
                url,
                filelength,
                true,
                param, model, true);
    }

    /**
     * 添加一个不显示在列表中的上传项，直接上传
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertUpDataInvisible(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            String param, String model) throws Exception {
        return insertUpDataBody(
                context,
                filePath,
                fileName,
                md5,
                url,
                filelength,
                false,
                param, model, true);
    }


    /**
     * 添加一个显示在列表中的上传项(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertUpDataVisible(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            String param) throws Exception {//modifly by flex,增加param参数，如果参数不为空则使用该值为云端数据库id
        return insertUpDataBody(
                context,
                filePath,
                fileName,
                md5,
                url,
                filelength,
                true,
                param, DataBean.STUDENT, true);
    }

    /**
     * 添加一个不显示在列表中的上传项(固定访问学生桌面的断点上传下载数据库)
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertUpDataInvisible(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            String param) throws Exception {
        return insertUpDataBody(
                context,
                filePath,
                fileName,
                md5,
                url,
                filelength,
                false,
                param, DataBean.STUDENT, true);
    }

    /**
     * 添加上传项。
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param visible    是否出现在上传管理界面的列表中
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param model      标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param isStartRun 添加的任务是否为开始上传的状态
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    private static HashMap<String, Object> insertUpDataBody(
            Context context,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            boolean visible,
            String param, String model, boolean isStartRun) throws Exception {

        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(md5) || TextUtils.isEmpty(url)) {
            throw new Exception("参数不能为空。");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在。");
        }
        UploadRecords records = queryUpData(context, md5, model);

        if (records != null && url.equals(records.upUrl)) {
            /**
             *正在上传或者已经上传成功了，就不再重复上传了。（注：必须是上传都同一个服务器工程，如果是两个工程，还是需要重新上传）
             */
            /** 当前状态：0正在上传、1上传完成、2上传未开始、3上传被暂停和4上传失败。 */
            if (StringHelpter.STATUS_OK.equals(records.STATUS)) {
                File uploadFile = new File(records.FILE_PATH);
                if (uploadFile != null && uploadFile.exists()) {
                    String downloadMd5 = FileCompare.getFileMD5(uploadFile);
                    if (downloadMd5 != null && downloadMd5.equals(md5)) {
                        Intent intent = new Intent(getEndUploadBroadcastAction4Model(model));
                        intent.putExtra(OBJECT, records);
                        context.sendBroadcast(intent);
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put(STATUS, exist);
                        map.put(OBJECT, records);
                        return map;
                    } else {
                        // if (downloadFile != null && downloadFile.exists()) {
                        // downloadFile.delete();
                        // }
                        records.FILE_UPLOADED_SIZE = 0;
//                        records.STATUS = StringHelpter.STATUS_NORMAL;
                        return updateUploadData(context, buildUploadRecords(
                                records,
                                filePath,
                                fileName,
                                md5,
                                url,
                                filelength,
                                visible,
                                param, isStartRun), model);
                    }
                } else {
                    // 重新上传。
                    records.FILE_UPLOADED_SIZE = 0;
//                    records.STATUS = StringHelpter.STATUS_NORMAL;
                    return updateUploadData(context, buildUploadRecords(
                            records,
                            filePath,
                            fileName,
                            md5,
                            url,
                            filelength,
                            visible,
                            param, isStartRun), model);
                }

            } else if (StringHelpter.STATUS_PAUSE.equals(records.STATUS) || StringHelpter.STATUS_FAILED.equals(records.STATUS)) {
                // 重新上传。
//                records.STATUS = StringHelpter.STATUS_NORMAL;
                return updateUploadData(context, buildUploadRecords(
                        records,
                        filePath,
                        fileName,
                        md5,
                        url,
                        filelength,
                        visible,
                        param, isStartRun), model);
            } else if (StringHelpter.STATUS_ING.equals(records.STATUS) || StringHelpter.STATUS_NORMAL.equals(records.STATUS)) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put(STATUS, exist);
                map.put(OBJECT, records);
                return map;
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, records);
            return map;
        } else {
            // 新增上传项。
            return insertUploadData(context, buildUploadRecords(
                    records,
                    filePath,
                    fileName,
                    md5,
                    url,
                    filelength,
                    visible,
                    param, isStartRun), model);
        }
    }

    /**
     * 查询数据库中的一条数据。
     *
     * @param context 上下文
     * @param md5     md5值
     * @param model   标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return UploadRecords
     * @throws Exception
     */
    private static UploadRecords queryUpData(Context context, String md5, String model) throws Exception {
        UploadRecords records = new UploadRecords();
        if (TextUtils.isEmpty(md5) || context == null) {
            throw new Exception("参数不能为空。");
        }
        ContentResolver cr = context.getContentResolver();
        String selection = DataBean.Upload_Records.FILE_CHECKSUM + " = ?";
        String[] selectionArgs = new String[]{md5};
//        Cursor c = cr.query(Uri.withAppendedPath(CONTENT_URI, "uploaditem"), null, selection, selectionArgs, null);
        String uri = getUri4Model(model, false);
        if (TextUtils.isEmpty(uri)) {
            return null;
        }
        Cursor c = cr.query(Uri.parse(uri), null, selection, selectionArgs, null);
        if (c != null && c.moveToFirst()) {
            SQ2Upload(c, records);
            c.close();
            return records;
        }
        if (c != null) {
            c.close();
        }
        return null;
    }

    /**
     * 构建一个上传对象。
     *
     * @param records    UploadRecords
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传地址
     * @param filelength 文件长度
     * @param visible    是否出现在上传管理列表中
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param isStartRun 是否马上开始上传任务
     * @return UploadRecords
     * @throws Exception
     */
    private static UploadRecords buildUploadRecords(
            UploadRecords records,
            String filePath,
            String fileName,
            String md5,
            String url,
            long filelength,
            boolean visible,
            String param, boolean isStartRun) throws Exception {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(md5) || TextUtils.isEmpty(url)) {
            throw new Exception("参数不能为空。");
        }
        if (records == null) {
            records = new UploadRecords();
        }
        records.FILE_PATH = filePath;
        records.FILE_NAME = fileName;
        records.upUrl = url;
        records.FILE_CHECKSUM = md5;
        if (filelength > 0) {
            records.FILE_SIZE = filelength;
        }
        if (visible) {
            records.available = 1;
            records.NOTIFY_PROGRESS = true;
            records.NOTIFY_RESULT = true;
        } else {
            records.available = -1;
            records.NOTIFY_PROGRESS = false;
            records.NOTIFY_RESULT = true;
        }
        // 新任务默认的状态
        if (isStartRun) {
            records.STATUS = StringHelpter.STATUS_NORMAL;//默认执行上传。
        } else {
            records.STATUS = StringHelpter.STATUS_PAUSE;//默认不执行上传。
        }
        records.SHOWED_FILE_AFTER_FINISHED = true;
        records.ISDELETE = false;
        records.DELETEFILE = false;
        ArrayList<upDownProcessItem> itemList = upDownProcessItemUtils.getFileBlockList(records.FILE_PATH);
        if (itemList != null) {
            records.upDownProcessItems = itemList;
            records.blockCount = itemList.size();
        } else {
            throw new Exception("文件分割失败，请确保文件存在，并可读。");
        }
        records.param = param;
        records.fileId = param;
        return records;
    }

    /**
     * 更新数据库中的一条下载数据。
     *
     * @param context 上下文
     * @param model   标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @param records UploadRecords
     * @return HashMap
     */
    private static HashMap<String, Object> updateUploadData(Context context, UploadRecords records, String model) {
        if (records == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        Upload2SQ(records, values);
        String where = DataBean.Upload_Records.FILE_CHECKSUM + " = ?";
        String[] selectionArgs = new String[]{records.FILE_CHECKSUM};
//        int count = cr.update(Uri.withAppendedPath(CONTENT_URI, "uploaditem"), values, where, selectionArgs);
        String uri = getUri4Model(model, false);
        if (TextUtils.isEmpty(uri)) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        int count = cr.update(Uri.parse(uri), values, where, selectionArgs);
        if (count > 0) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, success);
            map.put(OBJECT, records);
            return map;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(STATUS, fail);
        map.put(OBJECT, records);
        return map;
    }

    /**
     * 向数据库中插入一条下载数据。
     *
     * @param context 上下文
     * @param records UploadRecords
     * @param model   标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return HashMap
     */
    private static HashMap<String, Object> insertUploadData(Context context, UploadRecords records, String model) {
        if (records == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        Upload2SQ(records, values);
//        Uri downuri = cr.insert(Uri.withAppendedPath(CONTENT_URI, "uploaditem"), values);
        String uri = getUri4Model(model, false);
        if (TextUtils.isEmpty(uri)) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        Uri downuri = cr.insert(Uri.parse(uri), values);
        if (downuri != null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, success);
            map.put(OBJECT, records);
            return map;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(STATUS, fail);
        map.put(OBJECT, records);
        return map;
    }

    /**
     * sqlite转化为对象。
     *
     * @param cursor  数据库游标
     * @param records UploadRecords
     */
    public static void SQ2Upload(Cursor cursor, UploadRecords records) {
        records.UUID = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.UUID));
        records.FILE_NAME = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILE_NAME));
        records.FILE_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Upload_Records.FILE_SIZE));
        records.FILE_PATH = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILE_PATH));
        records.FILE_CHECKSUM = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILE_CHECKSUM));
        records.FILE_UPLOADED_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Upload_Records.FILE_UPLOADED_SIZE));
        records.STATUS = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.STATUS));
        records.START_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.START_TIME));
        records.END_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.END_TIME));
        records.REQUEST_CONTENT = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.REQUEST_CONTENT));
        records.upUrl = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.UPURL));
        records.DELETED_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.DELETED_FILE_AFTER_FINISHED)) > 0;
        records.SHOWED_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.SHOWED_FILE_AFTER_FINISHED)) > 0;
        records.NOTIFY_PROGRESS = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.NOTIFY_PROGRESS)) > 0;
        records.NOTIFY_RESULT = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.NOTIFY_RESULT)) > 0;
        records.AUTO_START_AFTER_NETWORK_CONNECTED = cursor
                .getInt(cursor.getColumnIndex(DataBean.Upload_Records.AUTO_START_AFTER_NETWORK_CONNECTED)) > 0;
        records.available = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.AVAILABLE));
        records.upDownProcessItems = upDownProcessItemUtils
                .String2upDownProcessItemList(cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.UPDOWNPROCESSITEM)));
        records.fileId = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILEID));
        records.blockCount = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.BLOCKCOUNT));
        records.param = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.USERPARAM));
    }

    /**
     * 对象转化为sqlite数据。
     *
     * @param records UploadRecords
     * @param values  ContentValues
     */
    public static void Upload2SQ(UploadRecords records, ContentValues values) {
        values.put(DataBean.Upload_Records.UUID, records.UUID);
        values.put(DataBean.Upload_Records.FILE_NAME, records.FILE_NAME);
        values.put(DataBean.Upload_Records.FILE_SIZE, records.FILE_SIZE);
        values.put(DataBean.Upload_Records.FILE_PATH, records.FILE_PATH);
        values.put(DataBean.Upload_Records.FILE_CHECKSUM, records.FILE_CHECKSUM);
        values.put(DataBean.Upload_Records.FILE_UPLOADED_SIZE, records.FILE_UPLOADED_SIZE);
        values.put(DataBean.Upload_Records.STATUS, records.STATUS);
        values.put(DataBean.Upload_Records.START_TIME, records.START_TIME);
        values.put(DataBean.Upload_Records.END_TIME, records.END_TIME);
        values.put(DataBean.Upload_Records.REQUEST_CONTENT, records.REQUEST_CONTENT);
        values.put(DataBean.Upload_Records.DELETED_FILE_AFTER_FINISHED, records.DELETED_FILE_AFTER_FINISHED);
        values.put(DataBean.Upload_Records.SHOWED_FILE_AFTER_FINISHED, records.SHOWED_FILE_AFTER_FINISHED);
        values.put(DataBean.Upload_Records.NOTIFY_PROGRESS, records.NOTIFY_PROGRESS);
        values.put(DataBean.Upload_Records.NOTIFY_RESULT, records.NOTIFY_RESULT);
        values.put(DataBean.Upload_Records.AUTO_START_AFTER_NETWORK_CONNECTED, records.AUTO_START_AFTER_NETWORK_CONNECTED);
        values.put(DataBean.Upload_Records.UPURL, records.upUrl);
        values.put(DataBean.Upload_Records.AVAILABLE, records.available);
        values.put(DataBean.Upload_Records.UPDOWNPROCESSITEM,
                upDownProcessItemUtils.upDownProcessItemList2String(records.upDownProcessItems));
        values.put(DataBean.Upload_Records.FILEID, records.fileId);
        values.put(DataBean.Upload_Records.BLOCKCOUNT, records.blockCount);
        values.put(DataBean.Upload_Records.USERPARAM, records.param);
    }

    /**
     * 通过传入的关键字，返回对应应用的上传下载功能的Uri字符串
     *
     * @param model      关键字（DataBean.STUDENT，DataBean.TEACHER，DataBean.POINTER）默认：DataBean.STUDENT
     * @param isDownload 需要访问的是否为下载数据表
     * @return 对应应用的上传，下载数据库Uri字符串
     */
    public static final String getUri4Model(String model, boolean isDownload) {
        if (!TextUtils.isEmpty(model)) {
            if (DataBean.STUDENT.equals(model)) {
                if (isDownload) {
                    return DataBean.CONTENT + DataBean.AUTOHORITY + DataBean.STRING_DOWNLOADITEM;
                } else {
                    return DataBean.CONTENT + DataBean.AUTOHORITY + DataBean.STRING_UPLOADITEM;
                }
            } else if (DataBean.TEACHER.equals(model)) {
                if (isDownload) {
                    return DataBean.CONTENT + DataBean.AUTOHORITY_TEACHER + DataBean.STRING_DOWNLOADITEM;
                } else {
                    return DataBean.CONTENT + DataBean.AUTOHORITY_TEACHER + DataBean.STRING_UPLOADITEM;
                }
            } else if (DataBean.POINTER.equals(model)) {
                if (isDownload) {
                    return DataBean.CONTENT + DataBean.AUTOHORITY_POINTER + DataBean.STRING_DOWNLOADITEM;
                } else {
                    return DataBean.CONTENT + DataBean.AUTOHORITY_POINTER + DataBean.STRING_UPLOADITEM;
                }
            }
        }
//        if (isDownload) {
//            return DataBean.CONTENT + DataBean.AUTOHORITY + DataBean.STRING_DOWNLOADITEM;
//        } else {
//            return DataBean.CONTENT + DataBean.AUTOHORITY + DataBean.STRING_UPLOADITEM;
//        }
        return null;
    }

    /**
     * 根据包名获取包名对应应用的上传下载模块的关键字（用于数据库操作）
     *
     * @param packageName 包名
     * @return 关键字，可能是空，表示包名没有上传下载数据库
     */
    public static final String getModel4Package(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            if (DataBean.STUDENT_PACKAGE.equals(packageName)) {
                return DataBean.STUDENT;
            } else if (DataBean.TEACHER_PACKAGE.equals(packageName)) {
                return DataBean.TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(packageName)) {
                return DataBean.POINTER;
            }
        }
        return null;
    }
//********************************************************通过包名获取下载相关广播Action值

    /**
     * 通过包名获取新增下载对象的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getAddDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_ADDED;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_ADDED_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_ADDED_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取开始下载的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getStartDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_START;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_START_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_START_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取正在下载的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getRunningDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_RUNNING;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_RUNNING_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_RUNNING_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取暂停下载的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getPauseDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_PAUSE;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_PAUSE_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_PAUSE_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取下载完成的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getEndDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_END;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_END_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_END_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取下载失败的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getFailDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_ERROR;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_ERROR_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_ERROR_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取删除下载对象的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getDeleteDownloadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_DELETE;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_DELETE_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_DELETE_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过关键字获取下载完成的Action
     *
     * @param model 标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return 对应不同应用的Action。
     */
    public static final String getEndDownloadBroadcastAction4Model(String model) {
        if (!TextUtils.isEmpty(model)) {
            if (DataBean.STUDENT.equals(model)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_END;
            } else if (DataBean.TEACHER.equals(model)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_END_TEACHER;
            } else if (DataBean.POINTER.equals(model)) {
                return DownloaderReceiver.INTENT_DOWNLOAD_END_POINTER;
            }
        }
        return null;
    }
    //********************************************************通过包名获取上传相关广播Action值

    /**
     * 通过包名获取新增上传对象的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getAddUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_ADDED;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_ADDED_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_ADDED_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取开始上传的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getStartUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_START;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_START_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_START_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取正在上传的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getRunningUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_RUNNING;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_RUNNING_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_RUNNING_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取暂停上传的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getPauseUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_PAUSE;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_PAUSE_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_PAUSE_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取上传完成的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getEndUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_END;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_END_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_END_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取上传失败的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getFailUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_ERROR;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_ERROR_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_ERROR_POINTER;
            }
        }
        return null;
    }

    /**
     * 通过包名获取删除上传对象的Action
     *
     * @param pkg 包名
     * @return 对应不同应用的Action。
     */
    public static final String getDeleteUploadBroadcastAction4Package(String pkg) {
        if (!TextUtils.isEmpty(pkg)) {
            if (DataBean.STUDENT_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_DELETE;
            } else if (DataBean.TEACHER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_DELETE_TEACHER;
            } else if (DataBean.POINTER_PACKAGE.equals(pkg)) {
                return UploaderReceiver.INTENT_UPLOAD_DELETE_POINTER;
            }
        }
        return null;
    }


    /**
     * 通过包名获取上传完成的Action
     *
     * @param model 标示具体访问哪一个应用的上传下载功能（DataBean.STUDENT,DataBean.TEACHER,DataBean.POINTER）。
     * @return 对应不同应用的Action。
     */
    public static final String getEndUploadBroadcastAction4Model(String model) {
        if (!TextUtils.isEmpty(model)) {
            if (DataBean.STUDENT.equals(model)) {
                return UploaderReceiver.INTENT_UPLOAD_END;
            } else if (DataBean.TEACHER.equals(model)) {
                return UploaderReceiver.INTENT_UPLOAD_END_TEACHER;
            } else if (DataBean.POINTER.equals(model)) {
                return UploaderReceiver.INTENT_UPLOAD_END_POINTER;
            }
        }
        return null;
    }


    /**
     * 默认线程
     */
    public static final int THREAD_NUM = 10;
    /**
     * 最小的分块
     */
    public static final int MIN_BLOCK_SIZE = 15 * 1024 * 1024;

    /***
     * 只有第一次会调用这个分块操作，下次就不会了，所以下载进度=0OK的。
     * @param pTotal 文件大小
     * @return 分好的块
     */
    public static ArrayList<upDownProcessItem> disposeBlocks(long pTotal) {
        ArrayList<upDownProcessItem> blocks = new ArrayList<>();
        int blockNum = pTotal < MIN_BLOCK_SIZE ? 1 : THREAD_NUM;
        int blockLength = (int) Math.ceil(pTotal / blockNum);
        for (int i = 0; i < blockNum; i++) {
            upDownProcessItem tempItem = new upDownProcessItem();
            tempItem.setBlockLength(i != blockNum - 1 ? blockLength : (pTotal - i * blockLength));
            tempItem.setCount(pTotal);
            tempItem.setDownloadCount(0);
            tempItem.setIndex(i);
            tempItem.setStart(i * blockLength);
            blocks.add(tempItem);
        }
        return blocks;
    }

}
