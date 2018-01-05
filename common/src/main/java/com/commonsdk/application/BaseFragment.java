package com.commonsdk.application;

import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.commonsdk.view.ShowKeyboard2;
import com.commonsdk.zhy.http.okhttp.OkHttpUtils;

/**
 * 请勿修改移除该类。（可自行增加必要功能）
 *
 * @author judy
 */
public class BaseFragment extends Fragment {
    /**
     * 界面初始化时，需要使用输入框的EditView，在onresum中使用。
     */
    private EditText focusEdit;
    /**
     * 是否动态控制软键盘的显示和隐藏，默认不控制。
     */
    private boolean autoKeyboard = false;

    @Override
    public void onResume() {
        super.onResume();
        if (focusEdit != null && autoKeyboard) {
            ShowKeyboard2.openKeybord(focusEdit, getActivity());
        }
    }

    @Override
    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(getContext());
        super.onDestroy();
    }

    public EditText getFocusEdit() {
        return focusEdit;
    }

    /**
     * 设置需要使用软键盘的EditText，将被使用在onResume中。
     *
     * @param focusEdit 使用软键盘的EditText
     */
    public void setFocusEdit(EditText focusEdit) {
        this.focusEdit = focusEdit;
    }

    public boolean isAutoKeyboard() {
        return autoKeyboard;
    }

    public void setAutoKeyboard(boolean autoKeyboard) {
        this.autoKeyboard = autoKeyboard;
    }
}
