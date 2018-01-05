package com.commonsdk.sim;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * SIM卡检测工具类，可判断：单卡，双卡，并获取双卡的信息。
 *
 * @author ztx
 */
public class SimCheck {
    /**
     * 使用演示：
     *
     * Object o = isDoubleSim(context); if (o == null) {
     * System.out.println("单卡"); } else if (o instanceof GaotongDoubleInfo) {
     * System.out.println("高通双卡"); } else if (o instanceof MtkDoubleInfo) {
     * System.out.println("mtk双卡"); }
     *
     */
    /**
     * 判断是双卡还是单卡，以及双卡的平台。
     *
     * @param c 上下文
     * @return GaotongDoubleInfo ， MtkDoubleInfo ， null
     */
    public static Object isDoubleSim(Context c) {
        GaotongDoubleInfo gaotongDoubleInfo = initQualcommDoubleSim(c);
        MtkDoubleInfo mtkDoubleInfo = initMtkDoubleSim(c);
        boolean isGaoTongCpu = gaotongDoubleInfo.isGaotongDoubleSim();
        boolean isMtkCpu = mtkDoubleInfo.isMtkDoubleSim();
        if (isGaoTongCpu) {
            // 高通芯片双卡
            return gaotongDoubleInfo;
        } else if (isMtkCpu) {
            // MTK芯片双卡
            return mtkDoubleInfo;
        } else {
            // 普通单卡手机
            return null;
        }
    }

    /**
     * 获取高通双卡平台，检测到的第一张卡的imsi号。
     *
     * @param gaotong GaotongDoubleInfo
     * @return imsi号。
     */
    public static String getGaotongFirstImsi(GaotongDoubleInfo gaotong) {
        if (gaotong != null) {
            String imsi_1 = "";
            if (!TextUtils.isEmpty(gaotong.getImsi_1())) {
                imsi_1 = gaotong.getImsi_1().trim();
            }
            if (TextUtils.isEmpty(imsi_1)) {
                String imsi_2 = "";
                if (!TextUtils.isEmpty(gaotong.getImsi_2())) {
                    imsi_2 = gaotong.getImsi_2().trim();
                }
                if (!TextUtils.isEmpty(imsi_1)) {
                    return imsi_2;
                } else {
                    return "";
                }
            } else {
                return imsi_1;
            }
        }
        return "";
    }

    /**
     * 获取MTK双卡平台，检测到的第一张卡的imsi号。
     *
     * @param mtk MtkDoubleInfo
     * @return imsi号。
     */
    public static String getMTKFirstImsi(MtkDoubleInfo mtk) {
        if (mtk != null) {
            String imsi_1 = "";
            if (!TextUtils.isEmpty(mtk.getImsi_1())) {
                imsi_1 = mtk.getImsi_1().trim();
            }
            if (TextUtils.isEmpty(imsi_1)) {
                String imsi_2 = "";
                if (!TextUtils.isEmpty(mtk.getImsi_2())) {
                    imsi_2 = mtk.getImsi_2().trim();
                }
                if (!TextUtils.isEmpty(imsi_1)) {
                    return imsi_2;
                } else {
                    return "";
                }
            } else {
                return imsi_1;
            }
        }
        return "";
    }

    /**
     * 判断是不是MTK双卡平台
     *
     * @param mContext 上下文
     * @return null不是MTK双卡平台，不为null，就是MTK双卡平台。
     */
    private static MtkDoubleInfo initMtkDoubleSim(Context mContext) {
        MtkDoubleInfo mtkDoubleInfo = new MtkDoubleInfo();
        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> c = Class.forName("com.android.internal.telephony.Phone");
            Field fields1 = c.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            mtkDoubleInfo.setSimId_1((Integer) fields1.get(null));
            Field fields2 = c.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            mtkDoubleInfo.setSimId_2((Integer) fields2.get(null));
            Method m = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", int.class);
            mtkDoubleInfo.setImsi_1((String) m.invoke(tm, mtkDoubleInfo.getSimId_1()));
            mtkDoubleInfo.setImsi_2((String) m.invoke(tm, mtkDoubleInfo.getSimId_2()));

            Method m1 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", int.class);
            mtkDoubleInfo.setImei_1((String) m1.invoke(tm, mtkDoubleInfo.getSimId_1()));
            mtkDoubleInfo.setImei_2((String) m1.invoke(tm, mtkDoubleInfo.getSimId_2()));

            Method mx = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", int.class);
            mtkDoubleInfo.setPhoneType_1((Integer) mx.invoke(tm, mtkDoubleInfo.getSimId_1()));
            mtkDoubleInfo.setPhoneType_2((Integer) mx.invoke(tm, mtkDoubleInfo.getSimId_2()));

            if (TextUtils.isEmpty(mtkDoubleInfo.getImsi_1()) && (!TextUtils.isEmpty(mtkDoubleInfo.getImsi_2()))) {
                mtkDoubleInfo.setDefaultImsi(mtkDoubleInfo.getImsi_2());
            }
            if (TextUtils.isEmpty(mtkDoubleInfo.getImsi_2()) && (!TextUtils.isEmpty(mtkDoubleInfo.getImsi_1()))) {
                mtkDoubleInfo.setDefaultImsi(mtkDoubleInfo.getImsi_1());
            }
        } catch (Exception e) {
            mtkDoubleInfo.setMtkDoubleSim(false);
            return mtkDoubleInfo;
        }
        mtkDoubleInfo.setMtkDoubleSim(true);
        return mtkDoubleInfo;
    }

    /**
     * 判断手机是否高通平台
     *
     * @param mContext 上下文
     * @return GaotongDoubleInfo
     */
    private static GaotongDoubleInfo initQualcommDoubleSim(Context mContext) {
        GaotongDoubleInfo gaotongDoubleInfo = new GaotongDoubleInfo();
        gaotongDoubleInfo.setSimId_1(0);
        gaotongDoubleInfo.setSimId_2(1);
        try {
            Class<?> cx = Class.forName("android.telephony.MSimTelephonyManager");
            Object obj = mContext.getSystemService("phone_msim");

            Method md = cx.getMethod("getDeviceId", int.class);
            Method ms = cx.getMethod("getSubscriberId", int.class);
            Method getLine1Number = cx.getMethod("getLine1Number", int.class);

            gaotongDoubleInfo.setImei_1((String) md.invoke(obj, gaotongDoubleInfo.getSimId_1()));
            gaotongDoubleInfo.setImei_2((String) md.invoke(obj, gaotongDoubleInfo.getSimId_2()));
            gaotongDoubleInfo.setImsi_1((String) ms.invoke(obj, gaotongDoubleInfo.getSimId_1()));
            gaotongDoubleInfo.setImsi_2((String) ms.invoke(obj, gaotongDoubleInfo.getSimId_2()));

            if (TextUtils.isEmpty(gaotongDoubleInfo.getImsi_1()) && (!TextUtils.isEmpty(gaotongDoubleInfo.getImsi_2()))) {
                gaotongDoubleInfo.setDefaultImsi(gaotongDoubleInfo.getImsi_2());
            }
            if (TextUtils.isEmpty(gaotongDoubleInfo.getImsi_2()) && (!TextUtils.isEmpty(gaotongDoubleInfo.getImsi_1()))) {
                gaotongDoubleInfo.setDefaultImsi(gaotongDoubleInfo.getImsi_1());
            }

            gaotongDoubleInfo.setPhone_1((String) getLine1Number.invoke(obj, gaotongDoubleInfo.getSimId_1()));
            gaotongDoubleInfo.setPhone_2((String) getLine1Number.invoke(obj, gaotongDoubleInfo.getSimId_2()));
        } catch (Exception e) {
            gaotongDoubleInfo.setGaotongDoubleSim(false);
            return gaotongDoubleInfo;
        }
        gaotongDoubleInfo.setGaotongDoubleSim(true);
        return gaotongDoubleInfo;
    }
}
