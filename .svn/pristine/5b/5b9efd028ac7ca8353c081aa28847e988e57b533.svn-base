package com.commonsdk.log;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import com.commonsdk.apk.ApkManager;
import com.commonsdk.dateandtime.DateAndTimeUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 软件全局报错，日志收集类。
 *
 * @author 蛟
 */
public class ErrorLogUtil {
    /**
     * 报错信息存储内容最大值
     */
    private static final long FILE_MAX_SIZE = 1024 * 1024 * 1;// 文件最大值。
    /**
     * log的主目录
     */
    private static final String MAIN_DIR = "/QimonLog";
    /**
     * log的文件名
     */
    private static final String LOG_FILE_NAME = "AppError.txt";
    /**
     * log文件绝对路径
     */
    private String mPath;
    /**
     * log文件输出流
     */
    private Writer mWriter;
    /**
     * 同步锁
     */
    private Object lock = new Object();
    /**
     * 日志单列模式的全局对象
     */
    private static ErrorLogUtil MLOG = null;
    /**
     * Activity界面回调接口 ，用于显示实时日志
     */
    private ChangeObserver mChangeObserver = null;
    /**
     * true,则在控制台中打印；false,则不打印
     */
    private boolean isShowCmd = true;
    /**
     * true,则保存日志到文件中；false,则不保存
     */
    private boolean isSaveFile = true;

    /**
     * 获取单列模式的log对象。
     *
     * @param context 上下文
     * @return 错误对象
     */
    public static ErrorLogUtil getConnectionLog(Context context) {
        if (MLOG == null && context != null) {
            try {
                MLOG = new ErrorLogUtil(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return MLOG;
    }

    /**
     * 私有的构造函数
     *
     * @param context 上下文
     * @throws IOException
     */
    private ErrorLogUtil(Context context) throws IOException {
        init(context);
    }

    /**
     * 构建log目录和文件。
     *
     * @param context 上下文
     * @throws IOException
     */
    protected void init(Context context) throws IOException {
        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard, MAIN_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // log的子目录，用包名，保证唯一性
        String name = "";
        try {
            ApkManager apk = new ApkManager(null, context);
            name = apk.getAppName();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File logDir = new File(dir, "/" + name + "_" + context.getPackageName());
        if (!logDir.exists()) {
            logDir.mkdirs();
            // do not allow media scan
            new File(logDir, ".nomedia").createNewFile();// 禁止图库扫描。
        }

        String basePath = logDir.getAbsolutePath() + "/" + LOG_FILE_NAME;
        mPath = basePath;
    }

    /**
     * 将log打印在控制台，并储存到文件中。
     *
     * @param context 上下文
     * @param message 内容
     */
    public void println(Context context, String message) {
        synchronized (lock) {
            String msg = DateAndTimeUtil.getStringDataAndTime(System.currentTimeMillis()) + message + "\n";
            if (mChangeObserver != null) {
                mChangeObserver.addMessage(msg);
            }
            if (isShowCmd) {// 控制台打印。
                CirclePrintln.printError(message);
            }
            try {
                if (isSaveFile) {// log文件写入。
                    if (TextUtils.isEmpty(mPath)) {
                        init(context);
                    }
                    if (!TextUtils.isEmpty(mPath)) {
                        openWriter();
                        if (mWriter != null) {
                            mWriter.write(msg);
                            mWriter.flush();
                        }
                        closeWriter();
                    }
                }
            } catch (IOException e) {
                closeWriter();
            }
        }
    }

    /**
     * 绑定log回调函数，用于界面显示log内容。
     *
     * @param observer log显示，监听和回调接口。
     * @return 内容
     */
    public String registerObserver(ChangeObserver observer) {
        mChangeObserver = observer;
        synchronized (lock) {
            try {
                File file = new File(mPath);
                int length = (int) file.length();
                byte[] buffer = new byte[length];
                FileInputStream inputStream = new FileInputStream(file);
                inputStream.read(buffer);
                inputStream.close();
                return new String(buffer);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "log获取失败!";
        }
    }

    /**
     * 获取log文件路径
     *
     * @return log文件绝对路径
     */
    public String getPath() {
        return mPath;
    }

    /**
     * 打开log文件输出流。
     */
    private void openWriter() {
        try {
            if (mWriter == null) {
                if (logFileIsBig()) {
                    mWriter = new BufferedWriter(new FileWriter(mPath, false), 20480);
                } else {
                    mWriter = new BufferedWriter(new FileWriter(mPath, true), 20480);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输出流。
     */
    private void closeWriter() {
        try {
            if (mWriter != null) {
                mWriter.close();
                mWriter = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断log文件，是否已经到达了设定了最大值。
     *
     * @return log文件是否过大
     */
    private boolean logFileIsBig() {
        if (!TextUtils.isEmpty(mPath)) {
            File file = new File(mPath);
            return file.exists() && file.length() > FILE_MAX_SIZE;
//            if (file.exists() && file.length() > FILE_MAX_SIZE) {
//                return true;
//            } else {
//                return false;
//            }
        }
        return false;
    }

    /**
     * log显示，监听和回调接口。
     *
     * @author 蛟
     */
    public interface ChangeObserver {
        void addMessage(String message);
    }

}
