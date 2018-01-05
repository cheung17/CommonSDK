package com.commonsdk.view;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

/**
 * 动态测量控件占用空间大小的工具类
 * <pre>
 * // *********************************************************************示例代码。
 * // measureView(headView);
 * // headContentHeight = headView.getMeasuredHeight();
 * // headContentWidth = headView.getMeasuredWidth();
 * // *********************************************************************示例代码。
 *</pre>
 * @author ztx
 */
public class AutoMeasureView {
    // *********************************************************************示例代码。
    // measureView(headView);
    // headContentHeight = headView.getMeasuredHeight();
    // headContentWidth = headView.getMeasuredWidth();
    // *********************************************************************示例代码。

    /**
     * 测量child的width以及height
     *
     * @param child 待测量的View.
     */
    public static void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

}
