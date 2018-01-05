package com.commonsdk.gps;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

/**
 * 封装gps操作<br/>
 * 流程变更了 以下作废<br/>
 * GPS定位，首先会在代码里去强行打开gps,不一定成功<br/>
 * 其中gps监听就算拿到数据了也要判断卫星数量，若小于3颗，提示 <br/>
 * <br/>
 *
 * @author ztx
 */
public class GpsOps {
    /**
     * message bundler的key:是否成功获得gps
     */
    public static final String KEY_GPS_SUCCESS = "KEY_GPS_SUCCESS";
    /**
     * message bundler的key:GPS操作完成后的提示信息
     */
    public static final String KEY_GPS_TIP = "KEY_GPS_TIP";
    /**
     * message bundler的key:GPS是否可用
     */
    public static final String KEY_GPS_ENABLED = "KEY_GPS_ENABLED";
    /**
     * gps监听时间间隔 单位ms
     */
    private static final long GPS_INTERVAL_TIME = 2000;
    /**
     * gps监听距离间隔 单位m
     */
    private static final float GPS_INTERVAL_DISTANCE = 5;
    /**
     * handler传递数据的key : gps数据获取是否成功
     */
    // private static final String DATA_KEY_GPS_SUCCESS =
    // "DATA_KEY_GPS_SUCCESS";
    public static final int LOC_GET_SUCCESS = 1;
    /***/
    public static final int LOC_GET_FAILURE = 0;
    /***/
    public static final int GPS_NUMBER = 2;
    /***/
    public static final int LOC_GET_LOCATION = 3;

    /**
     * gps超时时间
     */
    private long gpsOutTime;
    /**
     * gps Provider Location Listener
     */
    private LocationListener gpsLoctionListener;
    /**
     * wifi Provider Location Listener
     */
    private LocationListener wifiLoctionListener;
    /**
     * 卫星状态监听
     */
    private GpsStatus.Listener gpsStatusListener;
    /**
     * 卫星数量
     */
    private int satellitesCount = 0;
    /**
     * 校准卫星数量
     */
    private int satellitesUseCount = 0;
    /** 是否需要进行自定义基站定位 */
    // private boolean needSelfWifiLocate=true;
    /**
     * 是否已经送出message
     */
    private boolean haveSendMsg;
    /**
     * 上下文
     */
    private Context ctx;
    // private CommonString commonString;
    /**
     * ui线程的handler
     */
    Handler handler;
    /**
     * 是否结束
     */
    boolean done;
    /**
     * TAG
     */
    private static String TAG = "GpsUtil";

    // public static boolean sendLocation = false;
    /**
     * GPS定位信息
     */
    private Location mLastLocation;

    // private LocationManager mLocationManager;

    /**
     * @param ctx        上下文
     * @param uiHandler  回调
     * @param gpsOutTime GPS超时时间单位ms，限制GPS监听时间，过期自动销毁gps资源
     */
    public GpsOps(Context ctx, Handler uiHandler, long gpsOutTime) {
        this.ctx = ctx;
        this.handler = uiHandler;
        this.gpsOutTime = gpsOutTime;
    }

    /**
     * 向界面发送信息
     *
     * @param getGpsSuccess 是否成功获得gps
     * @param tip           GPS操作完成后的提示信息
     * @param msgType       消息类型
     */
    private void sendMsg(boolean getGpsSuccess, String tip, int msgType) {
        if (done) {
            return;
        }
        if (tip == null) {
            tip = "";
        }
        haveSendMsg = true;
        Message msg = Message.obtain(handler);
        msg.what = msgType;
        msg.getData().putBoolean(KEY_GPS_SUCCESS, getGpsSuccess);
        msg.getData().putString(KEY_GPS_TIP, tip);
        handler.sendMessage(msg);
    }

    /**
     * 初始化GPS信息,并开始一系列gps操作
     */
    public void initGpsOperation() {
        done = false;
        GpsUtil.onGps(ctx);
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        // 通过条件获取location，经测试，在成功开启gps后strLocation="gps",若没有成功开启gps,分为两种情况，可能是"network"，可能是null.
        boolean isGpsLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isGpsLocationEnabled) {
            Builder builder = new Builder(ctx);
            builder.setTitle("GPS开关");
            builder.setMessage("请开启“设置->位置”中的相关选项");
            builder.setPositiveButton("确定", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        ctx.startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        intent.setAction(Settings.ACTION_SETTINGS);
                        try {
                            ctx.startActivity(intent);
                        } catch (Exception e) {
                        }
                    }
                }
            });
            builder.setNegativeButton("取消", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }

    /**
     * 获取GPS
     *
     * @return 成功／失败
     */
    public boolean getGpsProvideEnable() {
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 开始定位
     *
     * @param listener 定位监听
     */
    public void startLocation(LocationListener listener) {
        done = false;
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGpsLocationEnabled) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPS_INTERVAL_TIME, GPS_INTERVAL_DISTANCE, listener);

            if (gpsOutTime != 0) {
                new GpsOutTimeMoniter().start();
            }

        }
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        done = false;
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGpsLocationEnabled) {
            if (gpsLoctionListener == null) {
                gpsLoctionListener = new GpsLocationListener();
            }
            if (gpsStatusListener == null) {
                gpsStatusListener = new GpsStatusListener();
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPS_INTERVAL_TIME, GPS_INTERVAL_DISTANCE,
                    gpsLoctionListener);
            locationManager.addGpsStatusListener(gpsStatusListener);

            if (gpsOutTime != 0) {
                new GpsOutTimeMoniter().start();
            }

        }
    }

    /**
     * 该线程负责gps定位的超时控制 若超时则转为自定义基站定位
     *
     * @author ztx
     */
    private class GpsOutTimeMoniter extends Thread {
        @Override
        public void run() {
            haveSendMsg = false;
            try {
                Thread.sleep(gpsOutTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!haveSendMsg) {
                sendMsg(false, "定位服务超时退出,请重试！", LOC_GET_FAILURE);
                // offGps();
            }
        }
    }

    /**
     * 该线程负责gps定位的超时控制 若超时则转为自定义基站定位
     *
     * @author ztx
     */
    private class NormalOutTimeMoniter extends Thread {
        @Override
        public void run() {
            haveSendMsg = false;
            try {
                Thread.sleep(gpsOutTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!haveSendMsg) {
                sendMsg(false, "定位服务超时退出,请重试！", LOC_GET_FAILURE);
                // offGps();
            }
        }
    }

    protected void success(double lat, double lon) {

    }

    protected void failure(String str) {

    }

    /**
     * 移除监听 关闭gps
     */
    private void offGps() {
        LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        if (null != gpsLoctionListener) {
            locationManager.removeUpdates(gpsLoctionListener);
            gpsLoctionListener = null;
        }
        if (null != gpsStatusListener) {
            locationManager.removeGpsStatusListener(gpsStatusListener);
            gpsLoctionListener = null;
        }
        // GpsUtil.offGps(ctx);
    }

    /**
     * 停止
     */
    public void stop() {
        done = true;
        offGps();
    }

    public Location getLastLocation() {
        return mLastLocation;
    }

    /**
     * GPS位置变化监听
     */
    private class GpsLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Message msg = handler.obtainMessage(LOC_GET_LOCATION);
            Bundle data = new Bundle();
            data.putParcelable("Location", location);
            msg.setData(data);
            handler.sendMessage(msg);
            // offGps();
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d(TAG, "当前GPS状态为可见状态");
                    break;
                case LocationProvider.OUT_OF_SERVICE: {
                    Log.d(TAG, "当前GPS状态为服务区外状态");
                    Message msg = handler.obtainMessage(LOC_GET_FAILURE);
                    handler.sendMessage(msg);
                }
                break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE: {
                    Log.d(TAG, "当前GPS状态为暂停服务状态");
                    Message msg = handler.obtainMessage(LOC_GET_FAILURE);
                    handler.sendMessage(msg);
                }
                break;
            }
        }
    }

    /**
     * 获得卫星数量的gps状态监听
     */
    private class GpsStatusListener extends GpsUtil.GetSatellitesCountListenner {
        public GpsStatusListener() {
            super(GpsOps.this.ctx);
        }

        @Override
        protected void haveCalcedSatellites(int satellitesCount, int satellitesUseCount) {
            GpsOps.this.satellitesCount = satellitesCount;
            GpsOps.this.satellitesUseCount = satellitesUseCount;
            Message msg = handler.obtainMessage(GPS_NUMBER);
            Bundle data = new Bundle();
            data.putInt("Total", satellitesCount);
            data.putInt("UseTotal", satellitesUseCount);
            msg.setData(data);
            handler.sendMessage(msg);
            Log.d("GpsOps", "haveCalcedSatellites: satellitesCount=" + satellitesCount + ", satellitesUseCount=" + satellitesUseCount);
        }
    }
}
