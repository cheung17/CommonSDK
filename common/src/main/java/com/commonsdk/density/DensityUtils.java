package com.commonsdk.density;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 屏幕单位转换。
 *
 * @author 蛟
 */
public final class DensityUtils {

    private DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpVal   dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spVal   sp值
     * @return px值
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxVal   px值
     * @return dp值
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context 上下文
     * @param pxVal   px值
     * @return sp值
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 在界面中的onResume中调用，当手机设置为“大号字体”时，可保持sp单位的字体大小不受影响。
     *
     * @param context 上下文
     */
    public static void fontScale(Context context) {
        Resources resource = context.getResources();
        Configuration configuration = resource.getConfiguration();
        configuration.fontScale = 1.0f;//设置字体的缩放比例
        resource.updateConfiguration(configuration, resource.getDisplayMetrics());
    }
}
