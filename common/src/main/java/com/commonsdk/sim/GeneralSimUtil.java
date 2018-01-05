package com.commonsdk.sim;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 单卡设备SIM卡信息工具类
 *
 * @author ztx
 */
public class GeneralSimUtil {
    /**
     * 电信手机卡
     */
    public static final int DIANXIN = 1;
    /**
     * 移动手机卡
     */
    public static final int YIDONG = 1 + DIANXIN;
    /**
     * 联通手机卡
     */
    public static final int LIANTONG = 1 + YIDONG;

    /**
     * 获取手机IMSI号码。
     *
     * @param context 上下文
     * @return 手机IMSI号码
     */
    public static String getIMSI(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getSubscriberId();
    }

    /**
     * 获取手机的IMEI号。
     *
     * @param context 上下文
     * @return 手机的IMEI号
     */
    public static String getIMEI(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    /**
     * Serial Number。手机串号
     *
     * @return Serial Number。手机串号
     */
    public static String getSerialNumber() {
        return android.os.Build.SERIAL;
    }

    /**
     * 获取手机号。
     *
     * @param context 上下文
     * @return 手机号
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * 判断是什么运营商的卡
     *
     * @param imsi imsi号
     * @return 运营商id
     */
    public static int checkSimType(String imsi) {
        if (!TextUtils.isEmpty(imsi)) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                // 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号 //中国移动
                return YIDONG;
            } else if (imsi.startsWith("46001")) {
                // 中国联通
                return LIANTONG;
            } else if (imsi.startsWith("46003")) {
                // 中国电信
                return DIANXIN;
            }
        }
        return -1;
    }
}
