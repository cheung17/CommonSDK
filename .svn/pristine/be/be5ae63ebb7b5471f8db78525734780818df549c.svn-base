package com.commonsdk.updownloader.dao;

/**
 * 下载上传任务状态变化的监听。
 *
 * @author fanjiao
 */
public interface TaskChangeListener {

    /**
     * 上传下载任务,排队等待
     *
     * @param id 文件的主键id
     */
    void onWait(String id);

    /**
     * 上传下载任务,开始
     *
     * @param id 文件的主键id
     */
    void onStart(String id);

    /**
     * 上传下载任务 ，请求中
     *
     * @param id       文件的主键id
     * @param progress 下载进度
     */
    void onRunning(String id, int progress);

    /**
     * 上传下载任务 ，暂停
     *
     * @param id       文件的主键id
     * @param progress 下载进度
     */
    void onPause(String id, int progress);

    /**
     * 上传下载任务 ，成功
     *
     * @param id     文件的主键id
     * @param isShow 是否显示在界面上。
     */
    void onSuccess(String id, boolean isShow);

    /**
     * 上传下载任务 ，失败
     *
     * @param id 文件的主键id
     */
    void onFailed(String id);

    /**
     * 删除任务
     *
     * @param id 文件的主键id
     */
    void ondelete(String id);
}
