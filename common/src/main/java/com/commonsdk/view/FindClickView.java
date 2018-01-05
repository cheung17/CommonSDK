package com.commonsdk.view;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 通过点击屏幕的坐标，返回被点击的view。
 *
 * @author ztx
 */
public class FindClickView {
    /**
     * 根据坐标获取相对应的子控件<br>
     * 在Activity使用
     *
     * @param context 上下文
     * @param x       坐标
     * @param y       坐标
     * @return 目标View
     */
    public static View getViewAtActivity(Context context, int x, int y) {
        // 从Activity里获取容器
        View root = null;
        if (context != null) {
            if (context instanceof Activity) {
                root = ((Activity) context).getWindow().getDecorView();
            } else if (context instanceof FragmentActivity) {
                root = ((FragmentActivity) context).getWindow().getDecorView();
            } else if (context instanceof TabActivity) {
                root = ((TabActivity) context).getWindow().getDecorView();
            }
        }
        if (root != null) {
            return findViewByXY(root, x, y);
        }
        return null;
    }

    /**
     * 根据坐标获取相对应的子控件<br>
     * 在重写ViewGroup使用
     *
     * @param x    坐标
     * @param y    坐标
     * @param root 跟控件
     * @return 目标View
     */
    public static View getViewAtViewGroup(View root, int x, int y) {
        if (root != null) {
            return findViewByXY(root, x, y);
        }
        return null;
    }

    /**
     * 根据坐标获取相对应的子控件<br>
     * 在重写ViewGroup使用
     *
     * @param view 跟控件
     * @param x    坐标
     * @param y    坐标
     * @return 目标View
     */
    private static View findViewByXY(View view, int x, int y) {
        View targetView = null;
        if (view instanceof ViewGroup) {
            // 父容器,遍历子控件
            ViewGroup v = (ViewGroup) view;
            for (int i = 0; i < v.getChildCount(); i++) {
                targetView = findViewByXY(v.getChildAt(i), x, y);
                if (targetView != null) {
                    break;
                }
            }
        } else {
            targetView = getTouchTarget(view, x, y);
        }
        return targetView;

    }

    /**
     * 根据坐标获取相对应的子控件<br>
     * 在重写ViewGroup使用
     *
     * @param view 跟控件
     * @param x    坐标
     * @param y    坐标
     * @return 目标View
     */
    private static View getTouchTarget(View view, int x, int y) {
        View targetView = null;
        // 判断view是否可以聚焦
        ArrayList<View> TouchableViews = view.getTouchables();
        for (View child : TouchableViews) {
            if (isTouchPointInView(child, x, y)) {
                targetView = child;
                break;
            }
        }
        return targetView;
    }

    /**
     * 判断点是否在view中
     *
     * @param view 控件
     * @param x    坐标
     * @param y    坐标
     * @return 是否在view中
     */
    private static boolean isTouchPointInView(View view, int x, int y) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        if (view.isClickable() && y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }
}
