package com.commonsdk.updownloader.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 断点下载对象
 *
 * @author fanjiao
 */
public class DownloadRecords implements Serializable {
    /**
     * SerializableID
     */
    public static final long serialVersionUID = 2368187937444814968L; //assign a long value
    /**
     * 给来源程序返回Bundle数据时，用的KEY。
     */
    public static final String TARGETCONTENT = "TARGETCONTENT";
    /**
     * 需要检测下载数据的MD5值
     */
    public static final int NEEDCHECKMD5_YES = 0;
    /**
     * 不需要检测下载数据的MD5值
     */
    public static final int NEEDCHECKMD5_NO = -1;
    /**
     * 每条数据的唯一标示符
     */
    public String UUID = "";
    /**
     * 文件名
     */
    public String FILE_NAME = "FileName";
    /**
     * 文件大小
     */
    public long FILE_SIZE = 0;
    /**
     * 文件绝对路径
     */
    public String FILE_PATH = "";
    /**
     * 文件MD5值
     */
    public String FILE_CHECKSUM = "";
    /**
     * 已经下载的大小
     */
    public long FILE_DOWNLOAD_SIZE = 0;
    /**
     * 当前状态：0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
     */
    public String STATUS = "2";
    /**
     * 下载开始时间，GMT时间
     */
    public String START_TIME = "";
    /**
     * 下载结束时间，GMT时间
     */
    public String END_TIME = "";//
    /**
     * 请求内容（断点上传下载接口要求）
     */
    public String REQUEST_CONTENT = "";
    /**
     * 下载完成后是否自动打开，TRUE表示自动打开，FALSE表示不自动打开，默认为FALSE
     */
    public boolean OPEN_FILE_AFTER_FINISHED = false;
    /**
     * 下载完成后是否显示在已下载列表，TRUE表示显示，FALSE表示不显示，默认为TRUE
     */
    public boolean SHOWED_FILE_AFTER_FINISHED = true;
    /**
     * 是否在通知栏提示下载进度，TRUE表示提示，FALSE表示不提示，默认为TRUE
     */
    public boolean NOTIFY_PROGRESS = true;
    /**
     * 是否在通知栏上面提示下载结果，TRUE表示提示，FALSE表示不提示，默认为TRUE
     */
    public boolean NOTIFY_RESULT = true;//
    /**
     * 是否在网络连上时自动开始下载，TRUE表示是，FALSE表示否，默认为FALSE。
     */
    public boolean AUTO_START_AFTER_NETWORK_CONNECTED = false;
    /**
     * 是否被手动删除。
     */
    public boolean ISDELETE = false;
    /**
     * 是否删除文件。
     */
    public boolean DELETEFILE = false;
    /**
     * 下载的url路径
     */
    public String downUrl = "";
    /**
     * 是否显示在应用列表中,1:显示；－1不显示，默认为：1
     */
    public int available = 1;
    /** 关联View。 */
//	public MyHolder holder;
    /**
     * 记录每一个块的上传情况
     */
    public ArrayList<upDownProcessItem> upDownProcessItems;
    /**
     * 下载完毕是否需要校验MD5值
     */
    public int needCheckMD5 = NEEDCHECKMD5_YES;
    /**
     * 下载任务来源名称
     */
    public String sourceName = "";
    /**
     * 任务来源的包名
     */
    public String sourcePkg = "";
    /**
     * 任务来源的类名
     */
    public String sourceClass = "";
    /**
     * 打开任务来源类的时候，需要传入的内容，该内容会在调用目标类时，放在Bundle中，key＝{@link DownloadRecords#TARGETCONTENT}
     */
    public String targetContent = "";

    public DownloadRecords() {
        String temp = System.currentTimeMillis() + "";
        UUID = temp.substring(temp.length() - 9);
    }

//    public DownloadRecords(DownloadRecords down) {
//        this.UUID = down.UUID;
//        this.FILE_NAME = down.FILE_NAME;
//        this.FILE_SIZE = down.FILE_SIZE;
//        this.FILE_PATH = down.FILE_PATH;
//        this.FILE_CHECKSUM = down.FILE_CHECKSUM;
//        this.FILE_DOWNLOAD_SIZE = down.FILE_DOWNLOAD_SIZE;
//        this.STATUS = down.STATUS;
//        this.START_TIME = down.START_TIME;
//        this.END_TIME = down.END_TIME;
//        this.REQUEST_CONTENT = down.REQUEST_CONTENT;
//        this.OPEN_FILE_AFTER_FINISHED = down.OPEN_FILE_AFTER_FINISHED;
//        this.SHOWED_FILE_AFTER_FINISHED = down.SHOWED_FILE_AFTER_FINISHED;
//        this.NOTIFY_PROGRESS = down.NOTIFY_PROGRESS;
//        this.NOTIFY_RESULT = down.NOTIFY_RESULT;
//        this.AUTO_START_AFTER_NETWORK_CONNECTED = down.AUTO_START_AFTER_NETWORK_CONNECTED;
//        this.ISDELETE = down.ISDELETE;
//        this.DELETEFILE = down.DELETEFILE;
//        this.downUrl = down.downUrl;
//        this.available = down.available;
////		this.holder = down.holder;
//        this.needCheckMD5 = down.needCheckMD5;
//    }

    @Override
    public String toString() {
        return "DownloadRecords{" +
                "UUID='" + UUID + '\'' +
                ", FILE_NAME='" + FILE_NAME + '\'' +
                ", FILE_SIZE=" + FILE_SIZE +
                ", FILE_PATH='" + FILE_PATH + '\'' +
                ", FILE_CHECKSUM='" + FILE_CHECKSUM + '\'' +
                ", FILE_DOWNLOAD_SIZE=" + FILE_DOWNLOAD_SIZE +
                ", STATUS='" + STATUS + '\'' +
                ", START_TIME='" + START_TIME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                ", REQUEST_CONTENT='" + REQUEST_CONTENT + '\'' +
                ", OPEN_FILE_AFTER_FINISHED=" + OPEN_FILE_AFTER_FINISHED +
                ", SHOWED_FILE_AFTER_FINISHED=" + SHOWED_FILE_AFTER_FINISHED +
                ", NOTIFY_PROGRESS=" + NOTIFY_PROGRESS +
                ", NOTIFY_RESULT=" + NOTIFY_RESULT +
                ", AUTO_START_AFTER_NETWORK_CONNECTED=" + AUTO_START_AFTER_NETWORK_CONNECTED +
                ", ISDELETE=" + ISDELETE +
                ", DELETEFILE=" + DELETEFILE +
                ", downUrl='" + downUrl + '\'' +
                ", available=" + available +
                ", upDownProcessItems=" + upDownProcessItems +
                ", needCheckMD5=" + needCheckMD5 +
                ", sourceName='" + sourceName + '\'' +
                ", sourcePkg='" + sourcePkg + '\'' +
                ", sourceClass='" + sourceClass + '\'' +
                ", targetContent='" + targetContent + '\'' +
                '}';
    }
}
