package com.commonsdk.application;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.commonsdk.view.FindClickView;
import com.commonsdk.view.ShowKeyboard2;
import com.commonsdk.zhy.http.okhttp.OkHttpUtils;

/**
 * Activity基类，请勿修改移除该类。（可自行增加必要功能）
 *
 * @author judy
 */
public class BaseActivity extends FragmentActivity {
    /**
     * 实时创建的对象id。
     */
    private String id = System.currentTimeMillis() + "";
    /**
     * 判断为滑动的最小距离
     */
    private static final int SCROLLLIMIT = 15;
    /**
     * 判断为长按的最小时间
     */
    private static final int LONGCLICKLIMIT = 500;
    /**
     * 是否为滑动操作。
     */
    private boolean isScroll;
    /**
     * 是否为多指触摸。
     */
    private boolean isMultFinger;
    /**
     * 是否为长按操作。
     */
    private boolean isLongClick;
    /**
     * 开始触摸的位置。
     */
    private PointF startPoint;
    /**
     * 开始触摸的时间。
     */
    private long startTime;
    /**
     * 当前获取焦点的输入框。
     */
    private View focusView;
    /**
     * 界面初始化时，需要使用输入框的EditView，在onresum中使用。
     */
    private EditText focusEdit;
    /**
     * 是否动态控制软键盘的显示和隐藏，默认不控制。
     */
    private boolean autoKeyboard = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getApplication() instanceof BaseApplication) {
            ((BaseApplication) getApplication()).pushApplication(id, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (focusEdit != null && autoKeyboard) {
            ShowKeyboard2.openKeybord(focusEdit, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getApplication() instanceof BaseApplication) {
            ((BaseApplication) getApplication()).pullApplication(id);
        }
        OkHttpUtils.getInstance().cancelTag(this);
    }

    /**
     * 单击非EditText，则隐藏软键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (autoKeyboard) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                focusView = getCurrentFocus();
                startPoint = new PointF(ev.getX(), ev.getY());
                startTime = System.currentTimeMillis();
                int num = ev.getPointerCount();
                if (num > 1) {
                    isMultFinger = true;
                } else {
                    isMultFinger = false;
                }
                isScroll = false;
                isLongClick = false;
//            return super.dispatchTouchEvent(ev);
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                if (BaseFragmentActivity.getDistance(new PointF(ev.getX(), ev.getY()), startPoint) > SCROLLLIMIT) {
                    isScroll = true;
                }
                if (System.currentTimeMillis() - startTime > LONGCLICKLIMIT) {
                    isLongClick = true;
                }
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                if (focusView != null && focusView instanceof EditText) {
                    if (!isScroll && !isMultFinger && !isLongClick) {
                        View v = FindClickView.getViewAtActivity(this, (int) ev.getX(), (int) ev.getY());
                        if (v == null || !(v instanceof EditText)) {
                            ShowKeyboard2.closeKeybord((EditText) focusView, this);
                        }
                    }
                }
            }
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (autoKeyboard) {
            /**
             * 如果有打开软键盘，就关闭它
             */
            focusView = getCurrentFocus();
            if (focusView != null && focusView instanceof EditText) {
                ShowKeyboard2.closeKeybord((EditText) focusView, this);
            }
        }
    }

    public EditText getFocusEdit() {
        return focusEdit;
    }

    /**
     * 设置需要使用软键盘的EditText，将被使用在onResume中。
     *
     * @param focusEdit 需要使用软键盘的EditText
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
