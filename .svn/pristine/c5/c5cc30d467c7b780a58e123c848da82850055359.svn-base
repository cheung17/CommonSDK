package com.commonsdk.view;

import android.content.Context;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 控制软键盘的显示或隐藏。
 *
 * @author 蛟
 */
public class ShowKeyboard {
    /**
     * edit.inputType的默认参数。
     */
    private int defaultType = -1;

    /**
     * 显示或隐藏软键盘。
     *
     * @param context        上下文
     * @param isShowKeyboard 显示/隐藏
     * @param edit           该界面的EditView
     */
    public void showKeyboard(Context context, boolean isShowKeyboard, EditText edit) {
        // private int defaultType = -1;//defaultType：全局变量，用于存储默认的inputType。
        InputMethodManager mInputMethodManager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        if (!isShowKeyboard) {
            mInputMethodManager.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            edit.clearFocus();
            if (defaultType == -1) {
                defaultType = edit.getInputType();
            }
            edit.setInputType(InputType.TYPE_NULL);
        } else {
            edit.setFocusable(true);
            edit.setFocusableInTouchMode(true);
            edit.requestFocus();
            if (defaultType != -1) {
                edit.setInputType(defaultType);
            }
            mInputMethodManager.showSoftInput(edit, 0);
        }
    }
}
