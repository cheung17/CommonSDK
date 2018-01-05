package com.commonsdk.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

/**
 * dialog工具类
 *
 * @author ztx
 */
public class ShowMyDialog {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * Dialog
     */
    private ProgressDialog progressDialog, buildDataDialog;
    /**
     * Dialog
     */
    private AlertDialog mAlertDialog;

    public ShowMyDialog(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 显示旋转进度，弹出框
     *
     * @param title 标题
     * @param msg   内容
     */
    public void ShowProgressBar(String title, String msg) {
        ShowProgressBar(title, msg, false, true);
    }

    /**
     * 显示旋转进度，弹出框
     *
     * @param title        标题
     * @param msg          内容
     * @param systemDialog 是否为系统类别。
     * @param UnInvasible  是否不可取消。
     */
    public void ShowProgressBar(String title, String msg, boolean systemDialog, boolean UnInvasible) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
        }
        if (systemDialog) {
            progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        if (UnInvasible) {
            progressDialog.setCancelable(false);
            progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return true;
                }
            });
        }
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(true);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 关闭旋转进度框。
     */
    public void CloseDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    /**
     * 对话框形式的通知信息。（不包含任何业务功能）
     *
     * @param title   标题
     * @param msg     内容
     * @param context 上下文
     */
    public void ShowMessageDialog(String title, String msg, Context context) {
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

    /**
     * 显示询问框。
     *
     * @param title  标题
     * @param msg    内容
     * @param cancel 点击取消按钮的回调
     * @param sure   点击确定按钮的回调
     */
    public void ShowConfirmDialog(String title, String msg, OnClickListener cancel, OnClickListener sure) {
        Builder builder = new Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("取消", cancel);
        builder.setPositiveButton("确定", sure);
        builder.create().show();
    }

    /**
     * 显示关闭Activity或fragment的对话框。
     *
     * @param msg     内容
     * @param context 上下文
     */
    public static void ShowFinishDialog(String msg, final Context context) {
        Builder bu = new Builder(context);
        bu.setTitle("提示：");
        bu.setMessage("\u3000\u3000" + msg);
        bu.setCancelable(false);
        bu.setPositiveButton("确定", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                } else if (context instanceof FragmentActivity) {
                    ((FragmentActivity) context).finish();
                }
            }
        });
        bu.show();
    }
}
