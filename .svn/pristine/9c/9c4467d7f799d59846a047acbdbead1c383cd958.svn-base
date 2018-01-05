package com.commonsdk.updownloader.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.commonsdk.file.FileCompare;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 教师端，上传下载工具类(废弃，统一使用UpDownloadUtils进行上传，通过model参数来区分使用的是哪一个应用的上传，下载功能)
 *
 * @author fanjiao
 */
@Deprecated
public class UpDownloadUtils_Teacher {
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
    public static final Uri CONTENT_URI = Uri.parse("content://com.qimon.updownloader_teacher");
    /**
     * 下载结束
     */
    public static final String INTENT_DOWNLOAD_END = "updownloader_teacher.download.end";
    /**
     * 上传完成
     */
    public static final String INTENT_UPLOAD_END = "updownloader_teacher.upload.end";
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
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleCheckSumNoOpen(Context context, String filePath, String fileName, String md5,
                                                                                  String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false);
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
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSumNoOpen(Context context, String filePath, String fileName, String md5,
                                                                                     String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, false);
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
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataVisibleNotCheckSum(Context context, String filePath, String fileName, String md5,
                                                                               String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, false, true);
    }

    /**
     * 添加一个显示在列表中的下载项
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
    public static HashMap<String, Object> insertDownloadDataVisible(Context context, String filePath, String fileName, String md5,
                                                                    String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, true, true, true);
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
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDownloadDataInvisibleNotCheckSum(Context
                                                                                         context, String filePath, String fileName, String md5,
                                                                                 String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, false, true);
    }

    /**
     * 添加一个不显示在列表中的下载项
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
    public static HashMap<String, Object> insertDownloadDataInvisible(Context context, String filePath, String fileName, String md5,
                                                                      String url, long filelength) throws Exception {
        return insertDataBody(context, filePath, fileName, md5, url, filelength, false, true, true);
    }

    /**
     * 插入数据的执行体。
     *
     * @param context      句柄
     * @param filePath     存储的文件夹
     * @param fileName     存储的文件名
     * @param md5          md5值
     * @param url          下载的地址
     * @param filelength   文件长度
     * @param visible      是否出现在上传下载列表中
     * @param needCheckMD5 下载完毕是否要检验MD5值
     * @param isOpenFile   下载完毕是否打开文件
     * @return status ： -1:表示添加失败，0:表示已经存在，1:表示添加成功 ；object ：操作的对象
     * @throws Exception
     */
    public static HashMap<String, Object> insertDataBody(Context context, String filePath, String fileName, String md5, String url,
                                                         long filelength, boolean visible, boolean needCheckMD5, boolean isOpenFile) throws Exception {
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
        DownloadRecords records = queryDownloadData(context, md5);
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
            if ("1".equals(records.STATUS)) {
                File downloadFile = new File(new File(records.FILE_PATH), records.FILE_NAME);
                if (downloadFile != null && downloadFile.exists()) {
                    String downloadMd5 = FileCompare.getFileMD5(downloadFile);
                    if (downloadMd5 != null && downloadMd5.equals(md5)) {
                        Intent intent = new Intent(INTENT_DOWNLOAD_END);
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
                        records.STATUS = "2";
                        records.upDownProcessItems = upDownProcessItemUtils.getFileBlockList(records.FILE_SIZE);
                        return updateDownloadData(context,
                                buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible));
                    }
                } else {
                    // 重新下载。
                    records.FILE_DOWNLOAD_SIZE = 0;
                    records.STATUS = "2";
                    records.upDownProcessItems = upDownProcessItemUtils.getFileBlockList(records.FILE_SIZE);
                    return updateDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible));
                }
            } else if ("3".equals(records.STATUS)) {
                // 继续下载。
                records.STATUS = "2";
                if (visible) {
                    records.available = 1;
                    records.NOTIFY_PROGRESS = true;
                    records.NOTIFY_RESULT = true;
                } else {
                    records.available = -1;
                    records.NOTIFY_PROGRESS = false;
                    records.NOTIFY_RESULT = true;
                }
//                return updateDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible));
                return updateDownloadData(context, records);
            } else if ("4".equals(records.STATUS)) {
                File downloadFile = new File(new File(filePath), fileName);
                if (downloadFile != null && downloadFile.exists()) {
                    downloadFile.delete();
                }
                records.FILE_DOWNLOAD_SIZE = 0;
                records.STATUS = "2";
                records.upDownProcessItems = upDownProcessItemUtils.getFileBlockList(records.FILE_SIZE);
                return updateDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible));
            } else if ("0".equals(records.STATUS) || "2".equals(records.STATUS)) {
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
            records.upDownProcessItems = upDownProcessItemUtils.getFileBlockList(filelength);
            if (needCheckMD5) {
                records.needCheckMD5 = DownloadRecords.NEEDCHECKMD5_YES;
            } else {
                records.needCheckMD5 = DownloadRecords.NEEDCHECKMD5_NO;
            }
            records.OPEN_FILE_AFTER_FINISHED = isOpenFile;
            return insertDownloadData(context, buildDownloadRecords(records, filePath, fileName, md5, url, filelength, visible));
        }
    }

    /**
     * 构建一个下载对象。
     *
     * @param records    DownloadRecords
     * @param filePath   存储的文件夹
     * @param fileName   存储的文件名
     * @param md5        md5值
     * @param url        下载的地址
     * @param filelength 文件长度
     * @param visible    是否出现在上传下载列表中
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
     * @return HashMap
     */
    private static HashMap<String, Object> insertDownloadData(Context context, DownloadRecords records) {
        if (records == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        Object2SQ(records, values);
        Uri downuri = cr.insert(Uri.withAppendedPath(CONTENT_URI, "downloaditem"), values);
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
     * @return HashMap
     */
    private static HashMap<String, Object> updateDownloadData(Context context, DownloadRecords records) {
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
        int count = cr.update(Uri.withAppendedPath(CONTENT_URI, "downloaditem"), values, where, selectionArgs);
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
     * @return DownloadRecords
     * @throws Exception
     */
    private static DownloadRecords queryDownloadData(Context context, String md5) throws Exception {
        DownloadRecords records = new DownloadRecords();
        if (TextUtils.isEmpty(md5) || context == null) {
            throw new Exception("参数不能为空。");
        }
        ContentResolver cr = context.getContentResolver();
        String selection = DataBean.Download_Records.FILE_CHECKSUM + " = ?";
        String[] selectionArgs = new String[]{md5};
        Cursor c = cr.query(Uri.withAppendedPath(CONTENT_URI, "downloaditem"), null, selection, selectionArgs, null);
        if (c != null && c.moveToFirst()) {
            SQ2Object(c, records);
            return records;
        }
        return null;
    }

    /**
     * sqlite转化为对象。
     *
     * @param cursor  数据库游标
     * @param records DownloadRecords
     */
    private static void SQ2Object(Cursor cursor, DownloadRecords records) {
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
    }

    /**
     * 对象转化为sqlite数据。
     *
     * @param records DownloadRecords
     * @param values  ContentValues
     */
    private static void Object2SQ(DownloadRecords records, ContentValues values) {
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
    }
    // ********************************************************************************以下是上传工具函数

    /**
     * 添加一个显示在列表中的上传项
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传路径
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
                param);
    }

    /**
     * 添加一个不显示在列表中的上传项
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传路径
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
                param);
    }

    /**
     * 添加上传项。
     *
     * @param context    上下文
     * @param filePath   文件路径
     * @param fileName   文件名字
     * @param md5        md5值
     * @param url        上传路径
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param visible    是否显示在上传管理列表界面
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
            String param) throws Exception {
        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(md5) || TextUtils.isEmpty(url)) {
            throw new Exception("参数不能为空。");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在。");
        }
        UploadRecords records = queryUpData(context, md5);
        if (records != null && url.equals(records.upUrl)) {
            /**
             *正在上传或者已经上传成功了，就不再重复上传了。（注：必须是上传都同一个服务器工程，如果是两个工程，还是需要重新上传）
             */
            /** 当前状态：0正在上传、1上传完成、2上传未开始、3上传被暂停和4上传失败。 */
            if ("1".equals(records.STATUS)) {
                File uploadFile = new File(records.FILE_PATH);
                if (uploadFile != null && uploadFile.exists()) {
                    String downloadMd5 = FileCompare.getFileMD5(uploadFile);
                    if (downloadMd5 != null && downloadMd5.equals(md5)) {
                        Intent intent = new Intent(INTENT_UPLOAD_END);
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
                        records.STATUS = "2";
                        return updateUploadData(context, buildUploadRecords(
                                records,
                                filePath,
                                fileName,
                                md5,
                                url,
                                filelength,
                                visible,
                                param));
                    }
                } else {
                    // 重新上传。
                    records.FILE_UPLOADED_SIZE = 0;
                    records.STATUS = "2";
                    return updateUploadData(context, buildUploadRecords(
                            records,
                            filePath,
                            fileName,
                            md5,
                            url,
                            filelength,
                            visible,
                            param));
                }

            } else if ("3".equals(records.STATUS) || "4".equals(records.STATUS)) {
                // 重新上传。
                records.STATUS = "2";
                return updateUploadData(context, buildUploadRecords(
                        records,
                        filePath,
                        fileName,
                        md5,
                        url,
                        filelength,
                        visible,
                        param));
            } else if ("0".equals(records.STATUS) || "2".equals(records.STATUS)) {
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
                    param));
        }
    }

    /**
     * 查询数据库中的一条数据。
     *
     * @param context 上下文
     * @param md5     md5值
     * @return UploadRecords
     * @throws Exception
     */
    private static UploadRecords queryUpData(Context context, String md5) throws Exception {
        UploadRecords records = new UploadRecords();
        if (TextUtils.isEmpty(md5) || context == null) {
            throw new Exception("参数不能为空。");
        }
        ContentResolver cr = context.getContentResolver();
        String selection = DataBean.Upload_Records.FILE_CHECKSUM + " = ?";
        String[] selectionArgs = new String[]{md5};
        Cursor c = cr.query(Uri.withAppendedPath(CONTENT_URI, "uploaditem"), null, selection, selectionArgs, null);
        if (c != null && c.moveToFirst()) {
            SQ2Upload(c, records);
            return records;
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
     * @param url        上传路径
     * @param filelength 文件长度
     * @param param      如果参数不为空则使用该值为云端数据库id
     * @param visible    是否显示在上传管理列表界面
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
            String param) throws Exception {
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
     * @param records UploadRecords
     * @return HashMap
     */
    private static HashMap<String, Object> updateUploadData(Context context, UploadRecords records) {
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
        int count = cr.update(Uri.withAppendedPath(CONTENT_URI, "uploaditem"), values, where, selectionArgs);
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
     * @return HashMap
     */
    private static HashMap<String, Object> insertUploadData(Context context, UploadRecords records) {
        if (records == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(STATUS, fail);
            map.put(OBJECT, null);
            return map;
        }
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        Upload2SQ(records, values);
        Uri downuri = cr.insert(Uri.withAppendedPath(CONTENT_URI, "uploaditem"), values);
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
    private static void SQ2Upload(Cursor cursor, UploadRecords records) {
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
    private static void Upload2SQ(UploadRecords records, ContentValues values) {
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
}
