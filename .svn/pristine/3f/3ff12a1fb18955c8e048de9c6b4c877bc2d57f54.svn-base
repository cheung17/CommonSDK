package com.commonsdk.network;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 跟网络相关的工具类
 *
 * @author ztx
 */
public class NetUtils {
    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context 上下文
     * @return 判断网络是否连接
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     *
     * @param context 上下文
     * @return 是否是wifi移动网络连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        }
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断是否是mobile移动网络连接
     *
     * @param context 上下文
     * @return 是否是mobile移动网络连接
     */
    public static boolean isMobile(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 打开网络设置界面
     *
     * @param activity 上下文
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}
