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
 * 上传，SQLite帮助类。
 *
 * @author Administrator
 */
public class UploadRecordsSqlHelper {
    /**
     * 数据库句柄
     */
    private ContentResolver resolver;
    /**
     * 句柄
     */
    private Context context;
    /**
     * 访问的数据库的URI（用来区分学生端，教师端，电子教鞭三个端的断点上传下载数据库）
     */
    private String uploadRecordsUri;

    public UploadRecordsSqlHelper(Context context, String model) {
        this.context = context;
        resolver = context.getContentResolver();
        uploadRecordsUri = UpDownloadUtils.getUri4Model(model, false);
    }

    /**
     * 查询全部上传数据
     *
     * @return 返回所有的上传数据
     */
    public ArrayList<UploadRecords> Query() {
        ArrayList<UploadRecords> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(uploadRecordsUri);
        Cursor cursor = resolver.query(mUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                UploadRecords records = new UploadRecords();
                UpDownloadUtils.SQ2Upload(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询所有正在上传，或等待上传的对象
     *
     * @return 目标对象列表
     */
    public ArrayList<UploadRecords> QueryAllRunningItem() {
        ArrayList<UploadRecords> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(uploadRecordsUri);
        String selection = DataBean.Upload_Records.STATUS + " = ? or " + DataBean.Upload_Records.STATUS + " = ? ";
        String[] selectionArgs = new String[]{StringHelpter.STATUS_NORMAL, StringHelpter.STATUS_ING};
        Cursor cursor = resolver.query(mUri, null, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                UploadRecords records = new UploadRecords();
                UpDownloadUtils.SQ2Upload(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询需要显示的全部上传数据
     *
     * @return 需要显示的全部上传数据
     */
    public ArrayList<UploadRecords> QueryIsAvailableData() {
        ArrayList<UploadRecords> arrayList = new ArrayList<UploadRecords>();
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(uploadRecordsUri);
        String selection = DataBean.Upload_Records.AVAILABLE + " = ?";
        String[] selectionArgs = new String[]{"1"};
        Cursor cursor = null;
        cursor = resolver.query(mUri, null, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                UploadRecords records = new UploadRecords();
                UpDownloadUtils.SQ2Upload(cursor, records);
                arrayList.add(records);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /**
     * 查询需要显示并且正在上传的数据
     *
     * @return 需要显示并且正在上传的全部上传对象
     */
    public ArrayList<UploadRecords> QueryIsAvailableUploadingData() {
        ArrayList<UploadRecords> arrayList = new ArrayList<UploadRecords>();
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return arrayList;
        }
        Uri mUri = Uri.parse(uploadRecordsUri);
        String selection = DataBean.Upload_Records.AVAILABLE + " = ? and " + DataBean.Upload_Records.STATUS + " = ?";
        String[] selectionArgs = new String[]{"1", StringHelpter.STATUS_ING};
        Cursor cursor = resolver.query(mUri, null, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                UploadRecords records = new UploadRecords();
                UpDownloadUtils.SQ2Upload(cursor, records);
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
     * @param _id 上传对象的uuid
     * @return 对应的上传数据
     */
    public UploadRecords QueryItem(String _id) {
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return null;
        }
        Uri mUri = Uri.parse(uploadRecordsUri + "/" + _id);
        UploadRecords records = null;
        Cursor cursor = resolver.query(mUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            records = new UploadRecords();
            UpDownloadUtils.SQ2Upload(cursor, records);
        }
        if (cursor != null) {
            cursor.close();
        }
        return records;
    }

    /**
     * 插入一条上传数据。
     *
     * @param records 上传数据
     * @return true：插入成功，false：插入失败。
     */
    public boolean Insert(UploadRecords records) {
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return false;
        }
        Uri mUri = Uri.parse(uploadRecordsUri);
        ContentValues values = new ContentValues();
        UpDownloadUtils.Upload2SQ(records, values);
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
     * @param records 上传数据
     * @return true：更新成功，false：更新失败。
     */
    public boolean Update(UploadRecords records) {
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return false;
        }
        Uri mUri = Uri.parse(uploadRecordsUri + "/" + records.UUID);
        ContentValues values = new ContentValues();
        UpDownloadUtils.Upload2SQ(records, values);
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
     * @param records 上传对象
     * @return true：删除成功，false：删除失败。
     */
    public boolean Delete(UploadRecords records) {
        if (TextUtils.isEmpty(uploadRecordsUri)) {
            return false;
        }
        Uri mUri = Uri.parse(uploadRecordsUri + "/" + records.UUID);
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
     * @param records 上传对象
     * @param values  ContentValues
     */
//    private void Object2SQ(UploadRecords records, ContentValues values) {
//        values.put(DataBean.Upload_Records.FILE_NAME, records.FILE_NAME);
//        values.put(DataBean.Upload_Records.FILE_SIZE, records.FILE_SIZE);
//        values.put(DataBean.Upload_Records.FILE_PATH, records.FILE_PATH);
//        values.put(DataBean.Upload_Records.FILE_CHECKSUM, records.FILE_CHECKSUM);
//        values.put(DataBean.Upload_Records.FILE_UPLOADED_SIZE, records.FILE_UPLOADED_SIZE);
//        values.put(DataBean.Upload_Records.STATUS, records.STATUS);
//        values.put(DataBean.Upload_Records.START_TIME, records.START_TIME);
//        values.put(DataBean.Upload_Records.END_TIME, records.END_TIME);
//        values.put(DataBean.Upload_Records.REQUEST_CONTENT, records.REQUEST_CONTENT);
//        values.put(DataBean.Upload_Records.DELETED_FILE_AFTER_FINISHED, records.DELETED_FILE_AFTER_FINISHED);
//        values.put(DataBean.Upload_Records.SHOWED_FILE_AFTER_FINISHED, records.SHOWED_FILE_AFTER_FINISHED);
//        values.put(DataBean.Upload_Records.NOTIFY_PROGRESS, records.NOTIFY_PROGRESS);
//        values.put(DataBean.Upload_Records.NOTIFY_RESULT, records.NOTIFY_RESULT);
//        values.put(DataBean.Upload_Records.AUTO_START_AFTER_NETWORK_CONNECTED, records.AUTO_START_AFTER_NETWORK_CONNECTED);
//        values.put(DataBean.Upload_Records.UPURL, records.upUrl);
//        values.put(DataBean.Upload_Records.AVAILABLE, records.available);
//        values.put(DataBean.Upload_Records.UPDOWNPROCESSITEM,
//                upDownProcessItemUtils.upDownProcessItemList2String(records.upDownProcessItems));
//        values.put(DataBean.Upload_Records.FILEID, records.fileId);
//        values.put(DataBean.Upload_Records.BLOCKCOUNT, records.blockCount);
//        values.put(DataBean.Upload_Records.USERPARAM, records.param);
//    }

    /**
     * sqlite转化为对象。
     *
     * @param cursor  游标
     * @param records 上传对象
     */
//    private void SQ2Object(Cursor cursor, UploadRecords records) {
//        records.UUID = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.UUID));
//        records.FILE_NAME = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILE_NAME));
//        records.FILE_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Upload_Records.FILE_SIZE));
//        records.FILE_PATH = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILE_PATH));
//        records.FILE_CHECKSUM = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILE_CHECKSUM));
//        records.FILE_UPLOADED_SIZE = cursor.getLong(cursor.getColumnIndex(DataBean.Upload_Records.FILE_UPLOADED_SIZE));
//        records.STATUS = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.STATUS));
//        records.START_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.START_TIME));
//        records.END_TIME = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.END_TIME));
//        records.REQUEST_CONTENT = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.REQUEST_CONTENT));
//        records.upUrl = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.UPURL));
//        records.DELETED_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.DELETED_FILE_AFTER_FINISHED)) > 0;
//        records.SHOWED_FILE_AFTER_FINISHED = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.SHOWED_FILE_AFTER_FINISHED)) > 0;
//        records.NOTIFY_PROGRESS = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.NOTIFY_PROGRESS)) > 0;
//        records.NOTIFY_RESULT = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.NOTIFY_RESULT)) > 0;
//        records.AUTO_START_AFTER_NETWORK_CONNECTED = cursor
//                .getInt(cursor.getColumnIndex(DataBean.Upload_Records.AUTO_START_AFTER_NETWORK_CONNECTED)) > 0;
//        records.available = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.AVAILABLE));
//        records.upDownProcessItems = upDownProcessItemUtils
//                .String2upDownProcessItemList(cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.UPDOWNPROCESSITEM)));
//        records.fileId = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.FILEID));
//        records.blockCount = cursor.getInt(cursor.getColumnIndex(DataBean.Upload_Records.BLOCKCOUNT));
//        records.param = cursor.getString(cursor.getColumnIndex(DataBean.Upload_Records.USERPARAM));
//    }
}
