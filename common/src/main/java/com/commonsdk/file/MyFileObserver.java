package com.commonsdk.file;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;

/**
 * 文件件变化监听
 * <p/>
 * Created by 郑治玄 on 2016/6/13.
 *
 * @author 郑治玄
 */
public class MyFileObserver {
    /**
     * 文件监听路径
     **/
    private String mPath = "";
    /**
     * 文件监听器
     **/
    private TempFileObserver tempFileObserver;
    /**
     * 文件变化回调
     **/
    private OnFileChangeListener mFileChangeListener;

    public MyFileObserver(Context context, String filePath) {
        this.mPath = filePath;
    }

    public MyFileObserver(Context context, File file) {
        if (file != null) {
            this.mPath = file.getAbsolutePath();
        }
    }

    /**
     * 开始监听文件变化
     */
    public void startListen() {
        if (TextUtils.isEmpty(mPath)) {
            return;
        }
        tempFileObserver = new TempFileObserver(mPath);
        tempFileObserver.startWatching();
    }

    /***
     * 结束监听文件事件
     */
    public void stopListen() {
        if (tempFileObserver != null) {
            tempFileObserver.stopWatching();
            tempFileObserver = null;
        }
    }

    /***
     * 设备文件变化 回调
     *
     * @param onFileChangeListener 文件变化回调
     */
    public void setFileChangeListenre(OnFileChangeListener onFileChangeListener) {
        this.mFileChangeListener = onFileChangeListener;
    }

    /**
     * 文件变化，监听。
     */
    class TempFileObserver extends android.os.FileObserver {

        TempFileObserver(String path) {
            super(path);
        }

        @Override
        public void onEvent(int event, String path) {
            final int action = event & android.os.FileObserver.ALL_EVENTS;
            switch (action) {
                case android.os.FileObserver.ACCESS:
                    // System.out.println("event: 文件或目录被访问, path: " + path);
                    break;
                case android.os.FileObserver.DELETE:
                    // System.out.println("event: 文件或目录被删除, path: " + path);
                    break;
                case android.os.FileObserver.OPEN:
                    //System.out.println("event: 文件或目录被打开, path: " + path);
                    break;
                case android.os.FileObserver.MODIFY:
                    //System.out.println("event: 文件或目录被修改, path: " + path);
                    if (mFileChangeListener != null) {
                        mFileChangeListener.onFileChange(mPath);
                    }
                    break;
                case android.os.FileObserver.CREATE:
                    // System.out.println("event: 文件或目录被创建, path: " + path);
                    // if (mFileChangeListener != null) mFileChangeListener.onFileChange(mPath);
                    break;
            }
        }
    }

    /**
     * 文件变化接口
     */
    public interface OnFileChangeListener {
        /**
         * 文件被修改
         *
         * @param path 文件监听路径
         **/
        void onFileChange(String path);
    }

}
