package com.commonsdk.teacherfile;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by z.houbin on 2017/5/24.
 */

public class TeacherFileProviderMetaData {
    public static final String AUTHORITY = "com.qimon.provider.TeacherFileProvider";
    public static final String DATABASE_NAME = "TEACHER_FILE.db";
    public static final int DATABASE_VERSION = 2;

    /**
     * 数据库中表相关的元数据
     */
    public static final class FileTableMetaData implements BaseColumns {

        public static final String TABLE_NAME = "teacher_file";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/teacher_file");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.qimon.file";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.file_item/vnd.qimon.file";

        public static final String FILE_TEXT = "text";
        public static final String FILE_TIME = "time";
        public static final String FILE_URL = "url";
        public static final String FILE_NAME = "name";
        public static final String FILE_TYPE = "type";
        public static final String FILE_DESC = "desc";
        public static final String FILE_SIZE = "size";


        public static final String DEFAULT_ORDERBY = "_id DESC";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + FILE_TEXT + " TEXT,"
                + FILE_TIME + " INTEGER,"
                + FILE_URL + " TEXT,"
                + FILE_NAME + " TEXT,"
                + FILE_TYPE + " INTEGER,"
                + FILE_DESC + " TEXT,"
                + FILE_SIZE + " TEXT"
                + ");";
    }
}
