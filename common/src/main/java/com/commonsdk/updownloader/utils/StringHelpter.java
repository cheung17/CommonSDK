package com.commonsdk.updownloader.utils;

/**
 * 字符串工具类
 *
 * @author fanjiao
 */
public class StringHelpter {
    /**
     * 开始必装任务
     */
    public static final String RES_DOWNLOAD_START_MUSTDOWNLOAD = "com.qimon.resdownload.service.updownloaderservice.startmustdownload";

    /**
     * 停止必装任务
     */
    public static final String RES_DOWNLOAD_STOP_MUSTDOWNLOAD = "com.qimon.resdownload.service.updownloaderservice.stopmustdownload";

    /**
     * 改变下载任务的状态
     */
    public static final String RES_DOWNLOAD_CHANGE = "com.qimon.resdownload.service.updownloaderservice.changedownloadstatus";
    /**
     * 删除下载任务的状态
     */
    public static final String RES_DELETE_DOWNLOAD_CHANGE = "com.qimon.resdownload.service.updownloaderservice.deletedownloads";

    /**
     * 改变下载任务的状态
     */
    public static final String DOWNLOAD_CHANGE = "com.qimon.updownloader.service.updownloaderservice.changedownloadstatus";
    /**
     * 改变上传任务的状态
     */
    public static final String UPLOAD_CHANGE = "com.qimon.updownloader.service.updownloaderservice.changeuploadstatus";
    /**
     * 删除下载任务的状态
     */
    public static final String DELETE_DOWNLOAD_CHANGE = "com.qimon.updownloader.service.updownloaderservice.deletedownloads";
    /**
     * 删除上传任务的状态
     */
    public static final String DELETE_UPLOAD_CHANGE = "com.qimon.updownloader.service.updownloaderservice.deleteuploads";
    /**
     * 是否删除文件的标识
     */
    public static final String ISDELETE = "isDelete";
    /**
     * 传递任务对象的key。
     */
    public static final String OBJECT = "object";

    /**
     * 下载上传对象的状态标识码。0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
     */
    public static final String STATUS_ING = "0";
    /**
     * 下载上传对象的状态标识码。0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
     */
    public static final String STATUS_OK = "1";
    /**
     * 下载上传对象的状态标识码。0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
     */
    public static final String STATUS_NORMAL = "2";
    /**
     * 下载上传对象的状态标识码。0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
     */
    public static final String STATUS_PAUSE = "3";
    /**
     * 下载上传对象的状态标识码。0正在下载、1下载完成、2下载未开始、3下载被暂停和4下载失败。
     */
    public static final String STATUS_FAILED = "4";
    /**
     * abSpecId。
     */
    public static final String DOWNINGTABSPECID = "downing";
    /**
     * abSpecId。
     */
    public static final String DOWNEDTABSPECID = "downed";
    /**
     * abSpecId。
     */
    public static final String UPINGTABSPECID = "uping";
    /**
     * abSpecId。
     */
    public static final String UPEDTABSPECID = "uped";
    /**
     * tabSpec的名称。
     */
    public static final String STRING_DOWNLOAD_TAB1 = "下载中";
    /**
     * tabSpec的名称。
     */
    public static final String STRING_DOWNLOAD_TAB2 = "已下载";
    /**
     * tabSpec的名称。
     */
    public static final String STRING_UPLOAD_TAB1 = "上传中";
    /**
     * tabSpec的名称。
     */
    public static final String STRING_UPLOAD_TAB2 = "已上传";
}
