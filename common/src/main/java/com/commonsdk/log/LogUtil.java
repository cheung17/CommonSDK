package com.commonsdk.log;

import android.content.Context;
import android.util.Log;

/**
 * 日志提醒
 *
 * @author 蛟
 */
@Deprecated
public class LogUtil {
    /**
     * 是否需要打印bug
     */
    private static boolean IS_SHOWLOG = false;
    /**
     * TAG
     */
    private static String TAG = "Log";

    /**
     * v
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void v(Context context, String msg) {
        if (IS_SHOWLOG) {
            Log.v(TAG, msg);
        }
        printlnLog(context, TAG + "  >>>>>>  " + msg);
    }

    /**
     * d
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void d(Context context, String msg) {
        if (IS_SHOWLOG) {
            Log.d(TAG, msg);
        }
        printlnLog(context, TAG + "  >>>>>>  " + msg);
    }

    /**
     * e
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void e(Context context, String msg) {
        if (IS_SHOWLOG) {
            Log.e(TAG, msg);
        }
        printlnLog(context, TAG + "  >>>>>>  " + msg);
    }

    /**
     * w
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void w(Context context, String msg) {
        if (IS_SHOWLOG) {
            Log.w(TAG, msg);
        }
        printlnLog(context, TAG + "  >>>>>>  " + msg);
    }

    /**
     * i
     *
     * @param context 上下文
     * @param msg     信息
     */
    public static void i(Context context, String msg) {
        if (IS_SHOWLOG) {
            Log.i(TAG, msg);
        }
        printlnLog(context, TAG + "  >>>>>>  " + msg);
    }

    /**
     * w
     *
     * @param context 上下文
     * @param tag     TAG
     * @param msg     信息
     */
    public static void i(Context context, String tag, String msg) {
        if (IS_SHOWLOG) {
            Log.i(tag, msg);
        }
        printlnLog(context, tag + "  >>>>>>  " + msg);
    }

    /**
     * w
     *
     * @param context 上下文
     * @param tag     TAG
     * @param msg     信息
     */
    public static void d(Context context, String tag, String msg) {
        if (IS_SHOWLOG) {
            Log.i(tag, msg);
        }
        printlnLog(context, tag + "  >>>>>>  " + msg);
    }

    /**
     * w
     *
     * @param context 上下文
     * @param tag     TAG
     * @param msg     信息
     */
    public static void e(Context context, String tag, String msg) {
        if (IS_SHOWLOG) {
            Log.i(tag, msg);
        }
        printlnLog(context, tag + "  >>>>>>  " + msg);
    }

    /**
     * w
     *
     * @param context 上下文
     * @param tag     TAG
     * @param msg     信息
     */
    public static void v(Context context, String tag, String msg) {
        if (IS_SHOWLOG) {
            Log.i(tag, msg);
        }
        printlnLog(context, tag + "  >>>>>>  " + msg);
    }

    /**
     * w
     *
     * @param context 上下文
     * @param tag     TAG
     * @param msg     信息
     */
    public static void w(Context context, String tag, String msg) {
        if (IS_SHOWLOG) {
            Log.w(tag, msg);
        }
        printlnLog(context, tag + "  >>>>>>  " + msg);
    }

    /**
     * 存储log到文件。
     *
     * @param context 上下文
     * @param message 信息
     */
    private static void printlnLog(Context context, String message) {
        RunTimeLogUtil logUtil = RunTimeLogUtil.getConnectionLog(context);
        logUtil.println(context, message);
    }
}
