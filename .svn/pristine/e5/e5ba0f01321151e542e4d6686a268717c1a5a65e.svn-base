package com.commonsdk.updownloader.myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.commonsdk.updownloader.dao.TaskChangeListener;
import com.commonsdk.updownloader.data.DownloadRecords;


/**
 * @author Administrator
 *         <p>
 *         <pre>
 *          // 下载广播
 *          public static final String INTENT_DOWNLOAD_ADDED = &quot;updownloader.download.added&quot;;
 *          public static final String INTENT_DOWNLOAD_START = &quot;updownloader.download.start&quot;;
 *          public static final String INTENT_DOWNLOAD_PROGRESS = &quot;updownloader.download.progress&quot;;
 *          public static final String INTENT_DOWNLOAD_END = &quot;updownloader.download.end&quot;;
 *          public static final String INTENT_DOWNLOAD_ERROR = &quot;updownloader.download.error&quot;;
 *          // 上传广播
 *          public static final String INTENT_UPLOAD_ADDED = &quot;updownloader.upload.added&quot;;
 *          public static final String INTENT_UPLOAD_START = &quot;updownloader.upload.start&quot;;
 *          public static final String INTENT_UPLOAD_PROGRESS = &quot;updownloader.upload.progress&quot;;
 *          public static final String INTENT_UPLOAD_END = &quot;updownloader.upload.end&quot;;
 *          public static final String INTENT_UPLOAD_ERROR = &quot;updownloader.upload.error&quot;;
 *          </pre>
 */
public class DownloaderReceiver extends BroadcastReceiver {
    // 资源下载
    /**
     * 添加新下载项
     */
    public static final String RES_INTENT_DOWNLOAD_ADDED = "resdownloader.download.added";
    /**
     * 开始下载
     */
    public static final String RES_INTENT_DOWNLOAD_START = "resdownloader.download.start";
    /**
     * 正在下载
     */
    public static final String RES_INTENT_DOWNLOAD_RUNNING = "resdownloader.download.running";
    /**
     * 下载暂停
     */
    public static final String RES_INTENT_DOWNLOAD_PAUSE = "resdownloader.download.pause";
    /**
     * 下载完成
     */
    public static final String RES_INTENT_DOWNLOAD_END = "resdownloader.download.end";
    /**
     * 下载失败
     */
    public static final String RES_INTENT_DOWNLOAD_ERROR = "resdownloader.download.error";
    /**
     * 删除某个对象
     */
    public static final String RES_INTENT_DOWNLOAD_DELETE = "resdownloader.download.delete";
    // 学生端下载广播
    /**
     * 添加新下载项
     */
    public static final String INTENT_DOWNLOAD_ADDED = "updownloader.download.added";
    /**
     * 开始下载
     */
    public static final String INTENT_DOWNLOAD_START = "updownloader.download.start";
    /**
     * 正在下载
     */
    public static final String INTENT_DOWNLOAD_RUNNING = "updownloader.download.running";
    /**
     * 下载暂停
     */
    public static final String INTENT_DOWNLOAD_PAUSE = "updownloader.download.pause";
    /**
     * 下载完成
     */
    public static final String INTENT_DOWNLOAD_END = "updownloader.download.end";
    /**
     * 下载失败
     */
    public static final String INTENT_DOWNLOAD_ERROR = "updownloader.download.error";
    /**
     * 删除某个对象
     */
    public static final String INTENT_DOWNLOAD_DELETE = "updownloader.download.delete";

    //教师端下载广播
    /**
     * 添加新下载项
     */
    public static final String INTENT_DOWNLOAD_ADDED_TEACHER = "updownloader_teacher.download.added";
    /**
     * 开始下载
     */
    public static final String INTENT_DOWNLOAD_START_TEACHER = "updownloader_teacher.download.start";
    /**
     * 正在下载
     */
    public static final String INTENT_DOWNLOAD_RUNNING_TEACHER = "updownloader_teacher.download.running";
    /**
     * 下载暂停
     */
    public static final String INTENT_DOWNLOAD_PAUSE_TEACHER = "updownloader_teacher.download.pause";
    /**
     * 下载完成
     */
    public static final String INTENT_DOWNLOAD_END_TEACHER = "updownloader_teacher.download.end";
    /**
     * 下载失败
     */
    public static final String INTENT_DOWNLOAD_ERROR_TEACHER = "updownloader_teacher.download.error";
    /**
     * 删除某个对象
     */
    public static final String INTENT_DOWNLOAD_DELETE_TEACHER = "updownloader_teacher.download.delete";

    //电子教鞭下载广播
    /**
     * 添加新下载项
     */
    public static final String INTENT_DOWNLOAD_ADDED_POINTER = "updownloader_pointer.download.added";
    /**
     * 开始下载
     */
    public static final String INTENT_DOWNLOAD_START_POINTER = "updownloader_pointer.download.start";
    /**
     * 正在下载
     */
    public static final String INTENT_DOWNLOAD_RUNNING_POINTER = "updownloader_pointer.download.running";
    /**
     * 下载暂停
     */
    public static final String INTENT_DOWNLOAD_PAUSE_POINTER = "updownloader_pointer.download.pause";
    /**
     * 下载完成
     */
    public static final String INTENT_DOWNLOAD_END_POINTER = "updownloader_pointer.download.end";
    /**
     * 下载失败
     */
    public static final String INTENT_DOWNLOAD_ERROR_POINTER = "updownloader_pointer.download.error";
    /**
     * 删除某个对象
     */
    public static final String INTENT_DOWNLOAD_DELETE_POINTER = "updownloader_pointer.download.delete";
    //
//    public static final String INTENT_ID = "INTENT_ID";
//    public static final String INTENT_PROGRESS = "INTENT_PROGRESS";
    /**
     * 界面注册的下载进度和状态监听对象
     */
    private TaskChangeListener mTaskListener;

    // private NotificationManager manager;
    // private Context context;
    /**
     * 传递任务对象的key。
     */
    public static final String OBJECT = "object";

    @Override
    public void onReceive(Context context, Intent intent) {
        // this.context = context;
        String action = intent.getAction();
        DownloadRecords down = (DownloadRecords) intent.getSerializableExtra(OBJECT);
        String _id = down.UUID;
        int progress = (int) down.FILE_DOWNLOAD_SIZE;
        if ((INTENT_DOWNLOAD_ADDED.equals(action)
                || INTENT_DOWNLOAD_ADDED_TEACHER.equals(action)
                || INTENT_DOWNLOAD_ADDED_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_ADDED.equals(action)) && mTaskListener != null) {
            mTaskListener.onWait(_id);
        } else if ((INTENT_DOWNLOAD_RUNNING.equals(action)
                || INTENT_DOWNLOAD_RUNNING_TEACHER.equals(action)
                || INTENT_DOWNLOAD_RUNNING_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_RUNNING.equals(action)) && mTaskListener != null) {
            mTaskListener.onRunning(_id, progress);
        } else if ((INTENT_DOWNLOAD_PAUSE.equals(action)
                || INTENT_DOWNLOAD_PAUSE_TEACHER.equals(action)
                || INTENT_DOWNLOAD_PAUSE_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_PAUSE.equals(action)) && mTaskListener != null) {
            mTaskListener.onPause(_id, progress);
        } else if ((INTENT_DOWNLOAD_END.equals(action)
                || INTENT_DOWNLOAD_END_TEACHER.equals(action)
                || INTENT_DOWNLOAD_END_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_END.equals(action)) && mTaskListener != null) {
            // 第二个参数用于判断是否将其添加到成功列表。
            mTaskListener.onSuccess(_id, down.SHOWED_FILE_AFTER_FINISHED);
        } else if ((INTENT_DOWNLOAD_ERROR.equals(action)
                || INTENT_DOWNLOAD_ERROR_TEACHER.equals(action)
                || INTENT_DOWNLOAD_ERROR_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_ERROR.equals(action)) && mTaskListener != null) {
            mTaskListener.onFailed(_id);
        } else if ((INTENT_DOWNLOAD_START.equals(action)
                || INTENT_DOWNLOAD_START_TEACHER.equals(action)
                || INTENT_DOWNLOAD_START_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_START.equals(action)) && mTaskListener != null) {
            mTaskListener.onStart(_id);
        } else if ((INTENT_DOWNLOAD_DELETE.equals(action)
                || INTENT_DOWNLOAD_DELETE_TEACHER.equals(action)
                || INTENT_DOWNLOAD_DELETE_POINTER.equals(action)
                || RES_INTENT_DOWNLOAD_DELETE.equals(action)) && mTaskListener != null) {
            mTaskListener.ondelete(_id);
        }
    }

    /**
     * 设置下载监听对象
     *
     * @param taskListener 界面传入的下载监听对象
     */
    public void setTaskChangeListener(TaskChangeListener taskListener) {
        mTaskListener = taskListener;
    }

}
