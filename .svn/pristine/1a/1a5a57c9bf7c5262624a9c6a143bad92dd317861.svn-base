package com.commonsdk.device;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Vibrator;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 设备操作
 *
 * @author ztx
 */
public class DeviceOperate {
    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context 上下文
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {

        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 判断是否为平板，通过判断屏幕尺寸来判断（大于6寸的，为平板）
     *
     * @param context 上下文
     * @return true：是平板／false：不是平板
     */
    public static boolean isTablet2(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度
        float screenWidth = display.getWidth();
        // 屏幕高度
        float screenHeight = display.getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        // 屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad
        if (screenInches >= 6.0) {
            return true;
        }
        return false;
    }

    /**
     * 关闭wifi。
     *
     * @param context 上下文
     */
    public static void CloseWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
    }

    /**
     * 开启wifi。
     *
     * @param context 上下文
     */
    public static void OpenWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

    /**
     * 获取当前wifi的状态值。
     * <p>
     * 1. wifiManager.WIFI_STATE_DISABLED (1)(已经关闭wifi)
     * <p>
     * 2. wifiManager..WIFI_STATE_ENABLED (3)(已经打开wifi....未连上)
     * <p>
     * 3. wifiManager..WIFI_STATE_DISABLING (0)(正在关闭wifi)
     * <p>
     * 4. wifiManager..WIFI_STATE_ENABLING (2)(正在打开wifi)
     * <p>
     * 5. wifiManager.WIFI_STATE_UNKNOWN (4)(未知)
     *
     * @param context 上下文
     * @return 当前wifi的状态值。
     */
    public static int GetWifiState(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int wifiState = -1;
        if (wifiManager != null) {
            wifiState = wifiManager.getWifiState();
        }
        return wifiState;
    }

    /**
     * 当前 Android 设备是否支持 Bluetooth
     *
     * @return true：支持 Bluetooth false：不支持 Bluetooth
     */
    public static boolean isBluetoothSupported() {
        return BluetoothAdapter.getDefaultAdapter() != null ? true : false;
    }

    /**
     * 当前 Android 设备的 bluetooth 是否已经开启
     *
     * @return true：Bluetooth 已经开启 false：Bluetooth 未开启
     */
    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    /**
     * 关闭蓝牙
     *
     * @return true为成功，false为失败。
     */
    public static boolean CloseSB() {
        if (isBluetoothSupported()) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter != null) {
                return bluetoothAdapter.disable();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 开启蓝牙
     *
     * @return true为成功，false为失败。
     */
    public static boolean OpenSB() {
        if (isBluetoothSupported()) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter != null) {
                return bluetoothAdapter.enable();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 获取当前GPS状态，true为开启，false为关闭。
     *
     * @param context 上下文
     * @return true为开启，false为关闭。
     */
    public static boolean getGPSState(Context context) {
        ContentResolver cr = context.getContentResolver();
        return Secure.isLocationProviderEnabled(cr, LocationManager.GPS_PROVIDER);
    }

    /**
     * 设备震动
     *
     * @param context 上下文
     */
    public static void DeviceVibrator(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {10, 200};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, -1);//重复上面的pattern 如果只想震动一次，index设为-1
    }

    /**
     * 设备震动
     *
     * @param context 上下文
     * @param pattern 震动时长，停止时长列表（停止 开启 停止 开启）
     */
    public static void DeviceVibrator(Context context, long[] pattern) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //long[] pattern = {10, 200};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, -1);//重复上面的pattern 如果只想震动一次，index设为-1
    }

    /**
     * 设备震动
     *
     * @param context 上下文
     * @param pattern 震动时长，停止时长列表（停止 开启 停止 开启）
     * @param time    震动循环次数（重复上面的pattern 如果只想震动一次，index设为-1）
     */
    public static void DeviceVibrator(Context context, long[] pattern, int time) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //long[] pattern = {10, 200};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, time);//重复上面的pattern 如果只想震动一次，index设为-1
    }
}
