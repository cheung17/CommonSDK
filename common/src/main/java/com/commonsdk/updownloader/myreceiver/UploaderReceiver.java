package com.commonsdk.updownloader.myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.commonsdk.updownloader.dao.TaskChangeListener;
import com.commonsdk.updownloader.data.UploadRecords;


/**
 * @author Administrator
 *         <p>
 *         <pre>
 *  // 下载广播
 *  public static final String INTENT_DOWNLOAD_ADDED = &quot;updownloader.download.added&quot;;
 *  public static final String INTENT_DOWNLOAD_START = &quot;updownloader.download.start&quot;;
 *  public static final String INTENT_DOWNLOAD_PROGRESS = &quot;updownloader.download.progress&quot;;
 *  public static final String INTENT_DOWNLOAD_END = &quot;updownloader.download.end&quot;;
 *  public static final String INTENT_DOWNLOAD_ERROR = &quot;updownloader.download.error&quot;;
 *  // 上传广播
 *  public static final String INTENT_UPLOAD_ADDED = &quot;updownloader.upload.added&quot;;
 *  public static final String INTENT_UPLOAD_START = &quot;updownloader.upload.start&quot;;
 *  public static final String INTENT_UPLOAD_PROGRESS = &quot;updownloader.upload.progress&quot;;
 *  public static final String INTENT_UPLOAD_END = &quot;updownloader.upload.end&quot;;
 *  public static final String INTENT_UPLOAD_ERROR = &quot;updownloader.upload.error&quot;;
 *  </pre>
 */
public class UploaderReceiver extends BroadcastReceiver {
    // 学生端上传广播
    /**
     * 添加一个上传对象
     */
    public static final String INTENT_UPLOAD_ADDED = "updownloader.upload.added";
    /**
     * 开始上传某个对象
     */
    public static final String INTENT_UPLOAD_START = "updownloader.upload.start";
    /**
     * 正在上传某个对象
     */
    public static final String INTENT_UPLOAD_RUNNING = "updownloader.upload.running";
    /**
     * 暂停上传某个对象
     */
    public static final String INTENT_UPLOAD_PAUSE = "updownloader.upload.pause";
    /**
     * 上传完成
     */
    public static final String INTENT_UPLOAD_END = "updownloader.upload.end";
    /**
     * 上传失败
     */
    public static final String INTENT_UPLOAD_ERROR = "updownloader.upload.error";
    /**
     * 删除某个上传任务
     */
    public static final String INTENT_UPLOAD_DELETE = "updownloader.upload.delete";
    // 教师端上传广播
    /**
     * 添加一个上传对象
     */
    public static final String INTENT_UPLOAD_ADDED_TEACHER = "updownloader_teacher.upload.added";
    /**
     * 开始上传某个对象
     */
    public static final String INTENT_UPLOAD_START_TEACHER = "updownloader_teacher.upload.start";
    /**
     * 正在上传某个对象
     */
    public static final String INTENT_UPLOAD_RUNNING_TEACHER = "updownloader_teacher.upload.running";
    /**
     * 暂停上传某个对象
     */
    public static final String INTENT_UPLOAD_PAUSE_TEACHER = "updownloader_teacher.upload.pause";
    /**
     * 上传完成
     */
    public static final String INTENT_UPLOAD_END_TEACHER = "updownloader_teacher.upload.end";
    /**
     * 上传失败
     */
    public static final String INTENT_UPLOAD_ERROR_TEACHER = "updownloader_teacher.upload.error";
    /**
     * 删除某个上传任务
     */
    public static final String INTENT_UPLOAD_DELETE_TEACHER = "updownloader_teacher.upload.delete";

    // 电子教鞭上传广播
    /**
     * 添加一个上传对象
     */
    public static final String INTENT_UPLOAD_ADDED_POINTER = "updownloader_pointer.upload.added";
    /**
     * 开始上传某个对象
     */
    public static final String INTENT_UPLOAD_START_POINTER = "updownloader_pointer.upload.start";
    /**
     * 正在上传某个对象
     */
    public static final String INTENT_UPLOAD_RUNNING_POINTER = "updownloader_pointer.upload.running";
    /**
     * 暂停上传某个对象
     */
    public static final String INTENT_UPLOAD_PAUSE_POINTER = "updownloader_pointer.upload.pause";
    /**
     * 上传完成
     */
    public static final String INTENT_UPLOAD_END_POINTER = "updownloader_pointer.upload.end";
    /**
     * 上传失败
     */
    public static final String INTENT_UPLOAD_ERROR_POINTER = "updownloader_pointer.upload.error";
    /**
     * 删除某个上传任务
     */
    public static final String INTENT_UPLOAD_DELETE_POINTER = "updownloader_teacher.upload.delete";

    //
//	public static final String INTENT_ID = "INTENT_ID";
//	public static final String INTENT_PROGRESS = "INTENT_PROGRESS";
    /**
     * 界面注册的下载进度和状态监听对象
     */
    private TaskChangeListener mTaskListener;
    /**
     * 传递任务对象的key。
     */
    public static final String OBJECT = "object";
    @Override
    public void onReceive(Context context, Intent intent) {
        // this.context = context;
        String action = intent.getAction();
        UploadRecords up = (UploadRecords) intent.getSerializableExtra(OBJECT);
        String _id = up.UUID;
        int progress = (int) up.FILE_UPLOADED_SIZE;
        if ((INTENT_UPLOAD_ADDED.equals(action)
                || INTENT_UPLOAD_ADDED_TEACHER.equals(action)
                || INTENT_UPLOAD_ADDED_POINTER.equals(action)) && mTaskListener != null) {
            mTaskListener.onWait(_id);
        } else if ((INTENT_UPLOAD_RUNNING.equals(action)
                || INTENT_UPLOAD_RUNNING_TEACHER.equals(action)
                || INTENT_UPLOAD_RUNNING_POINTER.equals(action)) && mTaskListener != null) {
            mTaskListener.onRunning(_id, progress);
        } else if ((INTENT_UPLOAD_PAUSE.equals(action)
                || INTENT_UPLOAD_PAUSE_TEACHER.equals(action)
                || INTENT_UPLOAD_PAUSE_POINTER.equals(action)) && mTaskListener != null) {
            mTaskListener.onPause(_id, progress);
        } else if ((INTENT_UPLOAD_END.equals(action)
                || INTENT_UPLOAD_END_TEACHER.equals(action)
                || INTENT_UPLOAD_END_POINTER.equals(action)) && mTaskListener != null) {
            // 第二个参数用于判断是否将其添加到成功列表。
            mTaskListener.onSuccess(_id, up.SHOWED_FILE_AFTER_FINISHED);
        } else if ((INTENT_UPLOAD_ERROR.equals(action)
                || INTENT_UPLOAD_ERROR_TEACHER.equals(action)
                || INTENT_UPLOAD_ERROR_POINTER.equals(action)) && mTaskListener != null) {
            mTaskListener.onFailed(_id);
        } else if ((INTENT_UPLOAD_START.equals(action)
                || INTENT_UPLOAD_START_TEACHER.equals(action)
                || INTENT_UPLOAD_START_POINTER.equals(action)) && mTaskListener != null) {
            mTaskListener.onStart(_id);
        } else if ((INTENT_UPLOAD_DELETE.equals(action)
                || INTENT_UPLOAD_DELETE_TEACHER.equals(action)
                || INTENT_UPLOAD_DELETE_POINTER.equals(action)) && mTaskListener != null) {
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
