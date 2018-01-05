package com.commonsdk.view;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 控制软键盘的显示或隐藏。
 *
 * @author 蛟
 */
public class ShowKeyboard2 {
    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        if (mEditText != null && mContext != null) {
            mEditText.setFocusableInTouchMode(true);
            mEditText.setFocusable(true);
            mEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 如果输入法打开则关闭，如果没打开则打开
     *
     * @param mContext 上下文
     */
    public static void toggleKeybord(Context mContext) {
        InputMethodManager m = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断软键盘是否已经打开。
     *
     * @param mContext 上下文
     * @return 软键盘是否已经打开。
     */
    public static boolean isOpenKeybord(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }
}
