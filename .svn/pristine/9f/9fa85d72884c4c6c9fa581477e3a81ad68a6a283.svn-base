package com.commonsdk.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author ztx
 */
public class ShowMyToast {
    /**
     * Toast
     */
    private Toast mToast;
    /**
     * 上下文
     */
    private Context mContext;

    public ShowMyToast(Context mContext) {

        this.mContext = mContext;
    }
    /**
     * 在主线程中，显示Toast。
     *
     * @param content 内容
     */
    public void showToastInMainThreadShort(final String content) {
        if (mContext instanceof Activity) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ShowToastShort(content);
                }
            });
        } else if (mContext instanceof FragmentActivity) {
            ((FragmentActivity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ShowToastShort(content);
                }
            });
        }
    }

    /**
     * 在主线程中，显示Toast。长时间
     *
     * @param content 内容
     */
    public void showToastInMainThreadLong(final String content) {
        if (mContext instanceof Activity) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ShowToastLong(content);
                }
            });
        } else if (mContext instanceof FragmentActivity) {
            ((FragmentActivity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ShowToastLong(content);
                }
            });
        }
    }

    /**
     * 在主线程中，显示Toast。自定义时间长度。
     *
     * @param content     内容
     * @param millisecond 显示时长。
     */
    public void showToastInMainThread(final String content, final int millisecond) {
        if (mContext instanceof Activity) {
            ((Activity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ShowToast(content, millisecond);
                }
            });
        } else if (mContext instanceof FragmentActivity) {
            ((FragmentActivity) mContext).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ShowToast(content, millisecond);
                }
            });
        }
    }

    /**
     * UI线程显示Toast。短时间。
     * @param content 内容
     */
    public void ShowToastShort(String content) {
        if (mContext != null) {
            if (mToast != null) {
                mToast.setText(content);
            } else {
                mToast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
            }
            mToast.show();
        }
    }

    /**
     * UI线程显示Toast。长时间。
     *
     * @param content 内容
     */
    public void ShowToastLong(String content) {
        if (mContext != null) {
            if (mToast != null) {
                mToast.setText(content);
            } else {
                mToast = Toast.makeText(mContext, content, Toast.LENGTH_LONG);
            }
            mToast.show();
        }
    }

    /**
     * UI线程显示Toast。自定义时间长度。
     * @param content     内容
     * @param millisecond 显示时长
     */
    public void ShowToast(String content, int millisecond) {
        if (mContext != null) {
            if (mToast != null) {
                mToast.setText(content);
            } else {
                mToast = Toast.makeText(mContext, content, millisecond);
            }
            mToast.show();
        }
    }
}
