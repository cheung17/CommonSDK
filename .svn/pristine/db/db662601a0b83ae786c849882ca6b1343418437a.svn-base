package com.commonsdk.window;

import android.content.Context;
import android.provider.Settings;

/**
 * 屏幕亮度调节工具类
 *
 * @author ztx
 */
public class ScreenBright {
    /**
     * 获得当前屏幕亮度的模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     *
     * @param context 上下文
     * @return 屏幕亮度的模式
     */
    public static int getScreenMode(Context context) {
        int screenMode = 0;
        try {
            screenMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception localException) {

        }
        return screenMode;
    }

    /**
     * 获得当前屏幕亮度值 0--255
     *
     * @param context 上下文
     * @return 屏幕亮度值
     */
    public static int getScreenBrightness(Context context) {
        int screenBrightness = 255;
        try {
            screenBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return screenBrightness;
    }

    /**
     * 设置当前屏幕亮度的模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     *
     * @param paramInt 亮度的模式
     * @param context  上下文
     */
    public static void setScreenMode(int paramInt, Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 设置当前屏幕亮度值 0--255
     *
     * @param paramInt 屏幕亮度值
     * @param context  上下文
     */
    public static void saveScreenBrightness(int paramInt, Context context) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 保存当前的屏幕亮度值，并使之生效
     */
    // public static void setScreenBrightness(int paramInt, Context context) {
    // Window localWindow = context.getWindow();
    // WindowManager.LayoutParams localLayoutParams =
    // localWindow.getAttributes();
    // float f = paramInt / 255.0F;
    // localLayoutParams.screenBrightness = f;
    // localWindow.setAttributes(localLayoutParams);
    // }

}
