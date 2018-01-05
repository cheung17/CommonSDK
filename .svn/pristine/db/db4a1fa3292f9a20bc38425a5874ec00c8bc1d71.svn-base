package com.commonsdk.updownloader.data;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 断点上传对象
 *
 * @author fanjiao
 */
public class UploadRecords implements Serializable {
    /**
     * 每条数据的唯一标示符
     */
    public String UUID = "";
    /**
     * 文件在服务器上的唯一标示，在上传文件第一个块成功后，会从服务器返回。
     */
    public String fileId = "";
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
     * 已经上传的大小
     */
    public long FILE_UPLOADED_SIZE = 0;
    /**
     * 当前状态：0正在上传、1上传完成、2上传未开始、3上传被暂停和4上传失败。
     */
    public String STATUS = "2";
    /**
     * 上传开始时间，GMT时间
     */
    public String START_TIME = "";
    /**
     * 上传结束时间，GMT时间
     */
    public String END_TIME = "";
    /**
     * 请求内容（断点上传下载接口要求）
     */
    public String REQUEST_CONTENT = "";
    /**
     * 上传完成后是否删除，TRUE表示删除，FALSE表示不删除，默认为FALSE(文件本身)
     */
    public boolean DELETED_FILE_AFTER_FINISHED = false;
    /**
     * 上传完成后是否显示在已上传列表，TRUE表示显示，FALSE表示不显示，默认为TRUE
     */
    public boolean SHOWED_FILE_AFTER_FINISHED = true;
    /**
     * 是否在通知栏提示上传进度，TRUE表示提示，FALSE表示不提示，默认为TRUE
     */
    public boolean NOTIFY_PROGRESS = true;
    /**
     * 是否在通知栏上面提示上传结果，TRUE表示提示，FALSE表示不提示，默认为TRUE
     */
    public boolean NOTIFY_RESULT = true;
    /**
     * 是否在网络连上时自动开始上传，TRUE表示是，FALSE表示否，默认为FALSE。（失败的任务自动开始或不开始。）
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
     * 上传的url路径
     */
    public String upUrl = "";
    /**
     * 是否显示在应用列表中,1:显示；－1不显示，默认为：1
     */
    public int available = 1;
    /** 关联View。 */
//	public MyHolder holder;
    /**
     * 文件块的数量
     */
    public int blockCount;
    /**
     * 记录每一个块的上传情况
     */
    public ArrayList<upDownProcessItem> upDownProcessItems;
    /**
     * add by flex，增加一个用户自定义参数，该参数不为空时作为云端数据库主键
     */
    public String param;

    public UploadRecords() {
        String temp = System.currentTimeMillis() + "";
        UUID = temp.substring(temp.length() - 9);
        Log.d("UploadRecords", "UploadRecords: ");
    }

    @Override
    public String toString() {
        return "UploadRecords{" +
                "UUID='" + UUID + '\'' +
                ", fileId='" + fileId + '\'' +
                ", FILE_NAME='" + FILE_NAME + '\'' +
                ", FILE_SIZE=" + FILE_SIZE +
                ", FILE_PATH='" + FILE_PATH + '\'' +
                ", FILE_CHECKSUM='" + FILE_CHECKSUM + '\'' +
                ", FILE_UPLOADED_SIZE=" + FILE_UPLOADED_SIZE +
                ", STATUS='" + STATUS + '\'' +
                ", START_TIME='" + START_TIME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                ", REQUEST_CONTENT='" + REQUEST_CONTENT + '\'' +
                ", DELETED_FILE_AFTER_FINISHED=" + DELETED_FILE_AFTER_FINISHED +
                ", SHOWED_FILE_AFTER_FINISHED=" + SHOWED_FILE_AFTER_FINISHED +
                ", NOTIFY_PROGRESS=" + NOTIFY_PROGRESS +
                ", NOTIFY_RESULT=" + NOTIFY_RESULT +
                ", AUTO_START_AFTER_NETWORK_CONNECTED=" + AUTO_START_AFTER_NETWORK_CONNECTED +
                ", ISDELETE=" + ISDELETE +
                ", DELETEFILE=" + DELETEFILE +
                ", upUrl='" + upUrl + '\'' +
                ", available=" + available +
//				", holder=" + holder +
                ", blockCount=" + blockCount +
                ", upDownProcessItems=" + upDownProcessItems +
                ", param='" + param + '\'' +
                '}';
    }
}
