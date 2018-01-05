package com.commonsdk.updownloader.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;


import com.commonsdk.updownloader.utils.StringHelpter;

import java.util.ArrayList;

/**
 * 下载，SQLite帮助类。
 *
 * @author Administrator
 */
public class DownloadRecordsSqlHelper {
    /**
     * 数据库访问，工具类
     */
    private ContentResolver resolver;
    /**
     * 句柄
     */
    private Context context;
    /**
     * 访问的数据库的URI（用来区分学生端，教师端，电子教鞭三个端的断点上传下载数据库）
     */
    private String downloadRecordsUri;

    public DownloadRecordsSqlHelper(Context context, String model) {
        this.context = context;
        resolver = context.getContentResolver();
        downloadRecordsUri = UpDownloadUtils.getUri4Model(model, true);
    }

    /**
     * 查询全部下载数据
     *
     * @return 全部下载数据
     */
    public ArrayList<DownloadRecords> Query() {
        ArrayList<DownloadRecords> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(downloadRecordsUri);
        Cursor cursor = resolver.query(mUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DownloadRecords records = new DownloadRecords();
                UpDownloadUtils.SQ2Object(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询所有正在下载，或等待下载的对象
     *
     * @return 目标对象列表
     */
    public ArrayList<DownloadRecords> QueryAllRunningItem() {
        ArrayList<DownloadRecords> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(downloadRecordsUri);
        String selection = DataBean.Download_Records.STATUS + " = ? or " + DataBean.Download_Records.STATUS + " = ? ";
        String[] selectionArgs = new String[]{StringHelpter.STATUS_NORMAL, StringHelpter.STATUS_ING};
        Cursor cursor = resolver.query(mUri, null, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DownloadRecords records = new DownloadRecords();
                UpDownloadUtils.SQ2Object(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询需要显示在列表中的全部下载数据
     *
     * @return 需要显示的数据
     */
    public ArrayList<DownloadRecords> QueryIsAvailableData() {
        ArrayList<DownloadRecords> arrayList = new ArrayList<DownloadRecords>();
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(downloadRecordsUri);
        String selection = DataBean.Download_Records.AVAILABLE + " = ?";
        String[] selectionArgs = new String[]{"1"};
        Cursor cursor = resolver.query(mUri, null, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DownloadRecords records = new DownloadRecords();
                UpDownloadUtils.SQ2Object(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询需要显示并正在下载的数据
     *
     * @return 需要显示并正在下载的数据
     */
    public ArrayList<DownloadRecords> QueryIsAvailableDownloadingData() {
        ArrayList<DownloadRecords> arrayList = new ArrayList<DownloadRecords>();
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(downloadRecordsUri);
        String selection = DataBean.Download_Records.AVAILABLE + " = ? and " + DataBean.Download_Records.STATUS + " =?";
        String[] selectionArgs = new String[]{"1", StringHelpter.STATUS_ING};
        Cursor cursor = resolver.query(mUri, null, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DownloadRecords records = new DownloadRecords();
                UpDownloadUtils.SQ2Object(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询一条数据
     *
     * @param id 数据库uuid
     * @return 一条数据
     */
    public DownloadRecords queryItem(String id) {
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return null;
        }
        Uri mUri = Uri.parse(downloadRecordsUri + "/" + id);
        DownloadRecords records = null;
        Cursor cursor = resolver.query(mUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            records = new DownloadRecords();
            UpDownloadUtils.SQ2Object(cursor, records);
        }
        if (cursor != null) {
            cursor.close();
        }
        return records;
    }

    /**
     * 插入一条下载数据。
     *
     * @param records 下载数据
     * @return true：成功。false：失败
     */
    public boolean Insert(DownloadRecords records) {
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return false;
        }
        Uri mUri = Uri.parse(downloadRecordsUri);
        ContentValues values = new ContentValues();
        UpDownloadUtils.Object2SQ(records, values);
        Uri uri = resolver.insert(mUri, values);
        return !(uri == null);
//        if (uri == null) {
//            return false;
//        } else {
//            return true;
//        }
    }

    /**
     * 更新一条数据。
     *
     * @param records 下载数据
     * @return true：成功。false：失败
     */
    public synchronized boolean Update(DownloadRecords records) {
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return false;
        }
        Uri mUri = Uri.parse(downloadRecordsUri + "/" + records.UUID);
        ContentValues values = new ContentValues();
        UpDownloadUtils.Object2SQ(records, values);
        int row = resolver.update(mUri, values, null, null);
        return row > 0;
//        if (row > 0) {
//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * 删除一条数据。
     *
     * @param records 下载数据
     * @return true：成功。false：失败
     */
    public boolean Delete(DownloadRecords records) {
        if (TextUtils.isEmpty(downloadRecordsUri)) {
            return false;
        }
        Uri mUri = Uri.parse(downloadRecordsUri + "/" + records.UUID);
        // ContentValues values = new ContentValues();
        // Object2SQ(records, values);
        int row = resolver.delete(mUri, null, null);
        return row > 0;
//        if (row > 0) {
//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * 对象转化为sqlite数据。
     *
     * @param records 下载数据
     * @param values  ContentValues对象
     */
//    private void Object2SQ(DownloadRecords records, ContentValues values) {
//        values.put(DataBean.Download_Records.FILE_NAME, records.FILE_NAME);
//        values.put(DataBean.Download_Records.FILE_SIZE, records.FILE_SIZE);
//        values.put(DataBean.Download_Records.FILE_PATH, records.FILE_PATH);
//        values.put(DataBean.Download_Records.FILE_CHECKSUM, records.FILE_CHECKSUM);
//        values.put(DataBean.Download_Records.FILE_DOWNLOAD_SIZE, records.FILE_DOWNLOAD_SIZE);
//        values.put(DataBean.Download_Records.STATUS, records.STATUS);
//        values.put(DataBean.Download_Records.START_TIME, records.START_TIME);
//        values.put(DataBean.Download_Records.END_TIME, records.END_TIME);
//        values.put(DataBean.Download_Records.REQUEST_CONTENT, records.REQUEST_CONTENT);
//        values.put(DataBean.Download_Records.OPEN_FILE_AFTER_FINISHED, records.OPEN_FILE_AFTER_FINISHED);
//        values.put(DataBean.Download_Records.SHOWED_FILE_AFTER_FINISHED, records.SHOWED_FILE_AFTER_FINISHED);
//        values.put(DataBean.Download_Records.NOTIFY_PROGRESS, records.NOTIFY_PROGRESS);
//        values.put(DataBean.Download_Records.NOTIFY_RESULT, records.NOTIFY_RESULT);
//        values.put(DataBean.Download_Records.AUTO_START_AFTER_NETWORK_CONNECTED, records.AUTO_START_AFTER_NETWORK_CONNECTED);
//        values.put(DataBean.Download_Records.DOWNURL, records.downUrl);
//        values.put(DataBean.Download_Records.AVAILABLE, records.available);
//        values.put(DataBean.Download_Records.NEEDCHECKMD5, records.needCheckMD5);
//        values.put(DataBean.Download_Records.UPDOWNPROCESSITEM,
//                upDownProcessItemUtils.upDownProcessItemList2String(records.upDownProcessItems));
//    }

    /**
     * sqlite转化为对象。
     *
     * @param cursor  数据库游标
     * @param records 下载数据
     */
//    private void SQ2Object(Cursor cursor, DownloadRecords records) {
//        records.UUID = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.UUID));
//        records.FILE_NAME = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.FILE_NAME));
//        records.FILE_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Download_Records.FILE_SIZE));
//        records.FILE_PATH = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.FILE_PATH));
//        records.FILE_CHECKSUM = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.FILE_CHECKSUM));
//        records.FILE_DOWNLOAD_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Download_Records.FILE_DOWNLOAD_SIZE));
//        records.STATUS = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.STATUS));
//        records.START_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.START_TIME));
//        records.END_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.END_TIME));
//        records.REQUEST_CONTENT = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.REQUEST_CONTENT));
//        records.downUrl = cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.DOWNURL));
//        records.OPEN_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.OPEN_FILE_AFTER_FINISHED)) > 0;
//        records.SHOWED_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.SHOWED_FILE_AFTER_FINISHED)) > 0;
//        records.NOTIFY_PROGRESS = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.NOTIFY_PROGRESS)) > 0;
//        records.NOTIFY_RESULT = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.NOTIFY_RESULT)) > 0;
//        records.AUTO_START_AFTER_NETWORK_CONNECTED = cursor
//                .getInt(cursor.getColumnIndex(DataBean.Download_Records.AUTO_START_AFTER_NETWORK_CONNECTED)) > 0;
//        records.available = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.AVAILABLE));
//        records.upDownProcessItems = upDownProcessItemUtils
//                .String2upDownProcessItemList(cursor.getString(cursor.getColumnIndex(DataBean.Download_Records.UPDOWNPROCESSITEM)));
//        records.needCheckMD5 = cursor.getInt(cursor.getColumnIndex(DataBean.Download_Records.NEEDCHECKMD5));
//    }
}
