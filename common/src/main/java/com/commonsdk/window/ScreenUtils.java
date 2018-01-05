package com.commonsdk.window;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 获得屏幕相关的辅助类
 *
 * @author ztx
 */
public class ScreenUtils {
    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 动态设置Activity背景透明度
     *
     * @param isLight2Dark 是从亮到暗
     * @param activity     activity
     */
    public static void setWindowAlpa(boolean isLight2Dark, Activity activity) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        final Window window = activity.getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (!isLight2Dark) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                lp.alpha = alpha;
                window.setAttributes(lp);
            }
        });
        animator.start();
    }

    /**
     * 获得屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /***
     * 获取屏幕高度
     *
     * @param pContext 上下文
     * @return 高度
     */
    public static int getScreenHeight2(Context pContext) {
        DisplayMetrics dm = pContext.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 通过反射，获取包含虚拟键的整体屏幕高度
     *
     * @return
     */
    public static int getHasVirtualKey(Activity pContext) {
        int dpi = 0;
        Display display = pContext.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获得状态栏的高度，通过反射的方式获取高度。
     *
     * @param context 上下文
     * @return 状态栏的高度
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获得通知栏的高度，通过获取可使用区域的top值得出通知栏高度（可能为0）。
     *
     * @param activity 上下文
     * @return 通知栏的高度
     */
    public static int getStatusHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * 获得通知栏的高度，通过读取系统配置文件的方式得到通知栏的高度（感觉更加可靠）。
     *
     * @return 通知栏的高度
     */
    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity 上下文
     * @return 当前屏幕截图，包含状态栏
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity 上下文
     * @return 前屏幕截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 去掉标题栏（需要写在setContentView语句之前）
     *
     * @param context 上下文
     */
    public static void noTitle(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).requestWindowFeature(Window.FEATURE_NO_TITLE);
        } else if (context instanceof FragmentActivity) {
            ((FragmentActivity) context).requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    /**
     * 全屏显示（需要写在setContentView语句之前）
     *
     * @param context 上下文
     */
    public static void fillScreen(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
            ((Activity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
        } else if (context instanceof FragmentActivity) {
            ((FragmentActivity) context).requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
            ((FragmentActivity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
        }
    }

    /**
     * 判断是否有底部NavigationBar
     *
     * @param context 上下文
     * @return 是否有底部NavigationBar
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
//            Log.w(TAG, e);
        }

        return hasNavigationBar;

    }

    /**
     * 判断是否有底部NavigationBar（通过判断是否有物理菜单按钮来判断）《不太靠谱》
     *
     * @param activity 上下文
     * @return 是否有底部NavigationBar
     */
    public static boolean checkDeviceHasNavigationBar2(Context activity) {
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        boolean hasHomeKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_HOME);
        if (!hasMenuKey && !hasBackKey && !hasHomeKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    /**
     * 获取屏幕底部NavigationBar的高度
     *
     * @param context 上下文
     * @return 屏幕底部NavigationBar的高度
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }

    /**
     * 获取屏幕旋转的角度
     *
     * @param rotation 屏幕所处角度的状态值
     * @return 角度值
     */
    public static int getRotationAngle(int rotation) {
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;

            case Surface.ROTATION_90:
                degrees = 90;
                break;

            case Surface.ROTATION_180:
                degrees = 180;
                break;

            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        return degrees;
    }

    /**
     * 获取屏幕旋转的角度
     *
     * @param activity 界面对象
     * @return 角度值
     */
    public static int getRotationAngle(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        return degrees;
    }

    /**
     * 设置横屏和竖屏。
     * 注：1.调用该函数的Activity，不能在xml中设置android:configChanges＝“orientation”，否则加载的xml永远都是竖屏布局。
     * 2.使用该函数后，会使Activity的生命周期重新调用一次，
     * 如：不使用该函数，onCreate...onResume...；使用该函数，onCreate...onResume...onDestroy,onCreate...onResume...
     * （onDestroy的时候会调用onSaveInstanceState函数，将需要保持一致的值放入Bundle中，在onCreate函数的Bundle参数中获取出来。）
     *
     * @param pActivity   检查的页面
     * @param isLandscape 是否为横屏
     */
    public static void checkScreenOrientation(Activity pActivity, boolean isLandscape) {
        if (pActivity == null) {
            return;
        }
        if (isLandscape) {
            pActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
            pActivity.getResources().getConfiguration().orientation = Configuration.ORIENTATION_LANDSCAPE;
        } else {
            pActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //竖屏
            pActivity.getResources().getConfiguration().orientation = Configuration.ORIENTATION_PORTRAIT;
        }
    }
}
