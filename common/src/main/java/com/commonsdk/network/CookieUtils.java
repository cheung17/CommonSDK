package com.commonsdk.network;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/**
 * 貌似只能在webview中使用，（慎用）
 *
 * @author ztx
 */
public class CookieUtils {

    /**
     * 设置cookie(这里一定要注意一点，在调用设置Cookie之后不能再设置web属性)
     *
     * @param context 上下文
     * @param url     url地址
     * @param cookies cookies
     */
    public static void synCookies(Context context, String url, String cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        //	cookieManager.setAcceptCookie(true);
        //	cookieManager.removeSessionCookie();// 移除
        cookieManager.setCookie(url, cookies);// cookies
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 获取cookies在onPageFinished函数中调用。
     *
     * @param url url地址
     * @return cookies
     */
    public static String getCookies(String url) {
        CookieManager cookieManager = CookieManager.getInstance();
        return cookieManager.getCookie(url);
    }

}
