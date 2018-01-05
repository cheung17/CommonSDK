package com.commonsdk.updownloader.dao;

/**
 * 编辑界面，选中多选框的回调，用于更新界面
 *
 * @author fanjiao
 */
public interface ViewChangeListener {
    /**
     * 设置被选中的数量
     *
     * @param value 选中的多选框数量。
     */
    void setViewValue(String value);
}
