package com.commonsdk.gps;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Log;

import java.util.Iterator;

/**
 * gps工具类
 *
 * @author ztx
 */
public abstract class GpsUtil {
    /**
     * 判断gps是否开启
     *
     * @param ctx 上下文
     * @return 开启／关闭
     */
    public static boolean isGpsEnable(Context ctx) {
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }

    /**
     * GPS开关,仅是gps定位开关，不包含wifi定位开关<br/>
     * 2.2以下版本方法是纯粹的gps开关，若gps当前为开则toggleGps()就是关闭gps
     *
     * @param ctx 上下文
     */
    public static void toggleGps(Context ctx) {
        // ------------------2.2以下版本开启gps方法--------------
        // TODO 经测试 4.0版本 nexus s2.3.6版本，以下代码无法开启gps 推测部分机型2.3以上已经无法使用此方法开启gps
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(ctx, 0, gpsIntent, 0).send();
        } catch (CanceledException e) {
            Log.e("GpsUtil", "toggleGPS  error", e);
        }
        // ------------------2.2及其以上版本开启gps方法 需要root权限----------
        // Settings.Secure.setLocationProviderEnabled( getContentResolver(),
        // LocationManager.GPS_PROVIDER, true);
    }

    /**
     * 开启GPS
     *
     * @param ctx 上下文
     */
    public static void onGps(Context ctx) {
        if (!isGpsEnable(ctx)) {
            toggleGps(ctx);
        }
    }

    /**
     * 关闭GPS
     *
     * @param ctx 上下文
     */
    public static void offGps(Context ctx) {
        if (isGpsEnable(ctx)) {
            toggleGps(ctx);
        }
        // ------------------2.2----------
        // Settings.Secure.setLocationProviderEnabled( getContentResolver(),
        // LocationManager.GPS_PROVIDER, false);
    }

    /**
     * 获取卫星数量的 GpsStatus.Listener 监听类
     *
     * @author ztx
     */
    public abstract static class GetSatellitesCountListenner implements GpsStatus.Listener {
        /**
         * 上下文
         */
        private Context ctx;

        public GetSatellitesCountListenner(Context ctx) {
            this.ctx = ctx;
        }

        /**
         * 卫星计算完成后回调此方法
         *
         * @param satellitesCount    计算出的卫星数量
         * @param satellitesUseCount 可用卫星数量
         */
        protected abstract void haveCalcedSatellites(int satellitesCount, int satellitesUseCount);

        @Override
        public void onGpsStatusChanged(int event) {
            LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
            GpsStatus status = locationManager.getGpsStatus(null); // 取当前状态
            // 获得卫星颗数
            if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
                int maxSatellites = status.getMaxSatellites();
                Iterator<GpsSatellite> it = status.getSatellites().iterator();
                int satellitesCount = 0;
                int satellitesUseCount = 0;
                while (it.hasNext() && satellitesCount <= maxSatellites) {
                    satellitesCount++;
                    GpsSatellite s = (GpsSatellite) it.next();
                    if (!s.usedInFix()) {
                        continue;
                    }
                    satellitesUseCount++;
                    // it.next();
                }// 计算卫星个数，可在此打印出卫星的其它信息
                haveCalcedSatellites(satellitesCount, satellitesUseCount);
            }
        }
    }
}
