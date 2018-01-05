package com.commonsdk.updownloader.data;

/**
 * 数据库常量
 *
 * @author fanjiao
 */
public class DataBean {
    /**
     * 学生端包名
     */
    public static final String STUDENT_PACKAGE = "org.qimon.launcher6";
    /**
     * 教师端包名
     */
    public static final String TEACHER_PACKAGE = "com.qimon.teacher";
    /**
     * 电子教鞭包名
     */
    public static final String POINTER_PACKAGE = "com.qimon.epointer";
    //**********************************************以上是拥有断点上传，下载功能的app包名
    /**
     * 学生端断点上传，下载数据库标示
     */
    public static final String AUTOHORITY = "com.qimon.updownloader";
    /**
     * 教师端断点上传，下载数据库标示
     */
    public static final String AUTOHORITY_TEACHER = "com.qimon.updownloader_teacher";
    /**
     * 电子教鞭断点上传，下载数据库标示
     */
    public static final String AUTOHORITY_POINTER = "com.qimon.updownloader_pointer";
    /**
     * 协议头
     */
    public static final String CONTENT = "content://";
    /**
     * downloaditem
     */
    public static final String STRING_DOWNLOADITEM = "/downloaditem";
    /**
     * uploaditem
     */
    public static final String STRING_UPLOADITEM = "/uploaditem";

    /**
     * 使用的上传，下载功能是学生端的（标示符关键字）
     */
    public static final String STUDENT = "updownloader_student";
    /**
     * 使用的上传，下载功能是教师端的（标示符关键字）
     */
    public static final String TEACHER = "updownloader_teacher";
    /**
     * 使用的上传，下载功能是电子教鞭的（标示符关键字）
     */
    public static final String POINTER = "updownloader_pointer";

    //*************************************************************以上是三个端的断点上传，下载功能整合后，需要的公共常量
    /**
     * 数据库名
     */
    public static final String DBNAME = "DataBean.DB";
    /**
     * 版本号
     */
    public static final int VERSION = 1;
    /****** upDownProcessItem相关 ****/
    /**
     * 起始位置
     */
    public static final String START = "start";
    /**
     * 下载的总大小
     */
    public static final String DOWNLOADCOUNT = "downloadCount";
    /**
     * 总长度
     */
    public static final String COUNT = "count";
    /**
     * 文件块的序号
     */
    public static final String INDEX = "index";
    /**
     * 块的md5值
     */
    public static final String MD5 = "md5";
    /**
     * 0表示未上传，1表示已经上传成功了
     */
    public static final String TYPE = "type";
    /**
     * 该item的类别，用于判断上传的先后顺序
     */
    public static final String FLAG = "flag";
    /**
     * 块的长度
     */
    public static final String BLOCKLENGTH = "blocklength";
    /**
     * 相对文件开始位置的偏移量
     */
    public static final String STARTPOS = "startpos";
    /**
     * 每一个文件块的长度
     */
    public static final String LENGTH = "length";
    /**
     * 文件块的个数
     */
    public static final String BLOCKCOUNT = "blockcount";
    /**
     * 文件在数据库中的id，第一块上传成功之后，从服务器返回。
     */
    public static final String FILEID = "fileid";
    /**
     * 参数，自定义参数
     */
    public static final String PARAM = "param";
    /**
     * 文件的md5值
     */
    public static final String FILEMD5 = "filemd5";
    /**
     * 服务器返回的状态码，CODE：0:代表成功；其他的代表错误。
     */
    public static final String CODE = "code";
    /**
     * 消息
     */
    public static final String MSG = "msg";
    /**
     * 文件在服务器数据库中的id
     */
    public static final String arg1 = "arg1";
    /**
     * 服务器接收到的文件块大小
     */
    public static final String arg2 = "arg2";

    /******
     * upDownProcessItem相关
     ****/

    /**
     * 4.1 上传数据表
     */
    public class Upload_Records {
        /**
         * 表名
         */
        public static final String TBNAME = "UPLOAD_RECORDS";
        /**
         * 唯一编号
         */
        public static final String UUID = "UUID";
        /**
         * 文件名
         */
        public static final String FILE_NAME = "FILE_NAME";
        /**
         * 文件大小
         */
        public static final String FILE_SIZE = "FILE_SIZE";
        /**
         * 文件绝对路径
         */
        public static final String FILE_PATH = "FILE_PATH";
        /**
         * 文件Checksum
         */
        public static final String FILE_CHECKSUM = "FILE_CHECKSUM";
        /**
         * 已经上传的大小
         */
        public static final String FILE_UPLOADED_SIZE = "FILE_UPLOADED_SIZE";
        /**
         * 当前状态：0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
         */
        public static final String STATUS = "STATUS";
        /**
         * 上传开始时间，GMT时间
         */
        public static final String START_TIME = "START_TIME";
        /**
         * 上传结束时间，GMT时间
         */
        public static final String END_TIME = "END_TIME";
        /**
         * 请求内容（断点上传下载接口要求）
         */
        public static final String REQUEST_CONTENT = "REQUEST_CONTENT";
        /**
         * 上传完成后是否删除，TRUE表示删除，FALSE表示不删除，默认为FALSE
         */
        public static final String DELETED_FILE_AFTER_FINISHED = "DELETED_FILE_AFTER_FINISHED";
        /**
         * 上传完成后是否显示在已上传列表，TRUE表示显示，FALSE表示不显示，默认为TRUE
         */
        public static final String SHOWED_FILE_AFTER_FINISHED = "SHOWED_FILE_AFTER_FINISHED";
        /**
         * 是否在通知栏提示上传进度，TRUE表示提示，FALSE表示不提示，默认为TRUE
         */
        public static final String NOTIFY_PROGRESS = "NOTIFY_PROGRESS";
        /**
         * 是否在通知栏上面提示上传结果，TRUE表示提示，FALSE表示不提示，默认为TRUE
         */
        public static final String NOTIFY_RESULT = "NOTIFY_RESULT";
        /**
         * 是否在网络连上时自动开始上传，TRUE表示是，FALSE表示否，默认为FALSE。
         */
        public static final String AUTO_START_AFTER_NETWORK_CONNECTED = "AUTO_START_AFTER_NETWORK_CONNECTED";
        /**
         * 是否被手动删除。
         */
        public static final String ISDELETE = "ISDELETE";
        /**
         * 是否删除文件。
         */
        public static final String DELETEFILE = "DELETEFILE";
        /**
         * 上传的url路径
         */
        public static final String UPURL = "UPURL";
        /**
         * 是否显示在应用列表中
         */
        public static final String AVAILABLE = "AVAILABLE";
        /**
         * 上传下载的每个块的进度
         */
        public static final String UPDOWNPROCESSITEM = "UPDOWNPROCESSITEM";
        /**
         * 文件在服务器上的唯一标示，在上传文件第一个块成功后，会从服务器返回。
         */
        public static final String FILEID = "FILEID";
        /**
         * 文件的块数。
         */
        public static final String BLOCKCOUNT = "BLOCKCOUNT";
        /**
         * 传递文件fileid
         */
        public static final String USERPARAM = "param";
    }

    /**
     * 4.2 下载数据表
     */
    public class Download_Records {
        /**
         * 表名
         */
        public static final String TBNAME = "DOWNLOAD_RECORDS";
        /**
         * 唯一编号
         */
        public static final String UUID = "UUID";
        /**
         * 文件名
         */
        public static final String FILE_NAME = "FILE_NAME";
        /**
         * 文件大小
         */
        public static final String FILE_SIZE = "FILE_SIZE";
        /**
         * 文件绝对路径
         */
        public static final String FILE_PATH = "FILE_PATH";
        /**
         * 文件Checksum
         */
        public static final String FILE_CHECKSUM = "FILE_CHECKSUM";
        /**
         * 已经下载的大小
         */
        public static final String FILE_DOWNLOAD_SIZE = "FILE_DOWNLOAD_SIZE";
        /**
         * 当前状态：0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
         */
        public static final String STATUS = "STATUS";
        /**
         * 下载开始时间，GMT时间
         */
        public static final String START_TIME = "START_TIME";
        /**
         * 下载结束时间，GMT时间
         */
        public static final String END_TIME = "END_TIME";
        /**
         * 请求内容（断点上传下载接口要求）
         */
        public static final String REQUEST_CONTENT = "REQUEST_CONTENT";
        /**
         * 下载完成后是否自动，TRUE表示自动打开，FALSE表示不自动打开，默认为FALSE
         */
        public static final String OPEN_FILE_AFTER_FINISHED = "OPEN_FILE_AFTER_FINISHED";
        /**
         * 下载完成后是否显示在已上传列表，TRUE表示显示，FALSE表示不显示，默认为TRUE
         */
        public static final String SHOWED_FILE_AFTER_FINISHED = "SHOWED_FILE_AFTER_FINISHED";
        /**
         * 是否在通知栏提示下载进度，TRUE表示提示，FALSE表示不提示，默认为TRUE
         */
        public static final String NOTIFY_PROGRESS = "NOTIFY_PROGRESS";
        /**
         * 是否在通知栏上面提示下载结果，TRUE表示提示，FALSE表示不提示，默认为TRUE
         */
        public static final String NOTIFY_RESULT = "NOTIFY_RESULT";
        /**
         * 是否在网络连上时自动开始下载，TRUE表示是，FALSE表示否，默认为FALSE。
         */
        public static final String AUTO_START_AFTER_NETWORK_CONNECTED = "AUTO_START_AFTER_NETWORK_CONNECTED";
        /**
         * 是否被手动删除。
         */
        public static final String ISDELETE = "ISDELETE";
        /**
         * 是否删除文件。
         */
        public static final String DELETEFILE = "DELETEFILE";
        /**
         * 下载的url路径
         */
        public static final String DOWNURL = "DOWNURL";
        /**
         * 是否显示在应用列表中
         */
        public static final String AVAILABLE = "AVAILABLE";
        /**
         * 上传下载的每个块的进度
         */
        public static final String UPDOWNPROCESSITEM = "UPDOWNPROCESSITEM";
        /**
         * 下载完毕是否需要校验MD5值
         */
        public static final String NEEDCHECKMD5 = "NEEDCHECKMD5";
        /**
         * 下载任务来源名称
         */
        public static final String SOURCENAME = "SOURCENAME";
        /**
         * 任务来源的包名
         */
        public static final String SOURCEPKG = "SOURCEPKG";
        /**
         * 任务来源的类名
         */
        public static final String SOURCECLASS = "SOURCECLASS";
        /**
         * 打开任务来源类的时候，需要传入的内容
         */
        public static final String TARGETCONTENT = "TARGETCONTENT";
    }

}
