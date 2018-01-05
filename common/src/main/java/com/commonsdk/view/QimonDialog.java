package com.commonsdk.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;


import com.commonsdk.view.sweetdialog.SweetAlertDialog;
import com.zeunp.commansdk.R;


/**
 * 青檬的弹出窗  项目地址： https://github.com/pedant/sweet-alert-dialog
 *
 * @author zeffect
 */
public class QimonDialog {
    /**
     * 上下文件
     **/
    private Context mContext;
    /**
     * 控件
     **/
    private SweetAlertDialog dialog;

    public SweetAlertDialog getDialog() {
        return dialog;
    }

    /**
     * 进度弹出窗
     **/
    private ProgressDialog progressDialog;

    public QimonDialog(Context context) {
        this.mContext = context;
    }

    /**
     * 对话框是否正在显示
     *
     * @return
     */
    public boolean isDialogShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    /**
     * 显示旋转的进度条
     */
    public void showLoadingDialog() {
//        closeDialog();
//        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setTitleText("");
//        dialog.setContentText(mContext.getResources().getString(R.string.LOADING));
//        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setCancelable(false);
//        dialog.show();
        showLoadingDialog("", true);
    }

    /**
     * 显示旋转的进度条
     *
     * @param title        显示的标题
     * @param isCancelable 能否关闭这个框
     */
    public void showLoadingDialog(String title, boolean isCancelable) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("");
        if (!TextUtils.isEmpty(title)) {
            dialog.setContentText(title);
        } else {
            dialog.setContentText(mContext.getResources().getString(R.string.LOADING));
        }
        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                closeDialog();
            }
        });
        dialog.show();
    }

    /**
     * 显示旋转的进度条
     *
     * @param isCancelable 能否关闭这个框
     */
    public void showLoadingDialog(boolean isCancelable) {
//        closeDialog();
//        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setTitleText("");
//        dialog.setContentText(mContext.getResources().getString(R.string.LOADING));
//        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setCancelable(isCancelable);
//        dialog.show();
        showLoadingDialog("", isCancelable);
    }

    /**
     * 显示旋转的进度条
     *
     * @param title 显示的标题
     */
    public void showLoadingDialog(String title) {
//        closeDialog();
//        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setTitleText("");
//        if (!TextUtils.isEmpty(title)) {
//            dialog.setContentText(title);
//        } else {
//            dialog.setContentText(mContext.getResources().getString(R.string.LOADING));
//        }
//        dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//        dialog.setCancelable(false);
//        dialog.show();
        showLoadingDialog(title, true);
    }

    /**
     * 关闭弹出窗
     */
    public void closeDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        dialog = null;
    }

    /**
     * 显示普通提示
     *
     * @param title 标题
     * @param msg   内容
     */
    public void showNormalDialog(String title, String msg) {
        showNormalDialog(title, msg, true, true, false);
    }

    /**
     * 显示一个可以关闭，只有一个确定按钮的提示框
     *
     * @param title 标题
     * @param msg   内容
     */
    public void showCancelAbleNormalDialog(String title, String msg) {
        showNormalDialog(title, msg, false, true, true);
    }

    /**
     * 显示普通提示
     *
     * @param title         标题
     * @param msg           内容
     * @param canccelButton 是否有取消按钮
     * @param confirmButton 是否有确定按钮
     * @param cancelAble    点击空白，是否可以关闭
     */
    public void showNormalDialog(String title, String msg, boolean canccelButton, boolean confirmButton, boolean cancelAble) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
        dialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        dialog.setCancelable(cancelAble);
        dialog.setCanceledOnTouchOutside(cancelAble);
        dialog.setTitleText(title);
        dialog.setContentText(msg);
        if (confirmButton) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
            dialog.setConfirmClickListener(null);
        }
        if (canccelButton) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
            dialog.setCancelClickListener(null);
        }
        dialog.show();
    }

    /**
     * 显示普通提示
     *
     * @param title         标题
     * @param msg           内容
     * @param canccelButton 是否有取消按钮
     * @param confirmButton 是否有确定按钮
     * @param cancelAble    点击空白，是否可以关闭
     */
    public void showNormalDialog(String title, String msg, boolean canccelButton, boolean confirmButton, boolean cancelAble, SweetAlertDialog.OnSweetClickListener cacelListener, SweetAlertDialog.OnSweetClickListener confirmListener) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
        dialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        dialog.setCancelable(cancelAble);
        dialog.setCanceledOnTouchOutside(cancelAble);
        dialog.setTitleText(title);
        dialog.setContentText(msg);
        if (confirmButton && confirmListener != null) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
            dialog.setConfirmClickListener(confirmListener);
        }
        if (canccelButton) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
            dialog.setCancelClickListener(cacelListener);
        }
        dialog.show();
    }

    /**
     * 显示失败对话框
     *
     * @param title  标题
     * @param msg    内容
     * @param sure   确定回调
     * @param cancel 取消回调
     */
    public void showFaileDialog(String title, String msg, SweetAlertDialog.OnSweetClickListener sure, SweetAlertDialog.OnSweetClickListener cancel) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText(title);
        dialog.setContentText(msg);
        if (sure != null) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
        }
        if (cancel != null) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
        }
        dialog.setCancelClickListener(cancel);
        dialog.setConfirmClickListener(sure);
        dialog.show();
    }

    /**
     * 显示成功对话框
     *
     * @param title  标题
     * @param msg    内容
     * @param sure   确定回调
     * @param cancel 取消回调
     */
    public void showSuccessDialog(String title, String msg, SweetAlertDialog.OnSweetClickListener sure, SweetAlertDialog.OnSweetClickListener cancel) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
        dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText(title);
        dialog.setContentText(msg);
        if (sure != null) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
        }
        if (cancel != null) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
        }
        dialog.setCancelClickListener(cancel);
        dialog.setConfirmClickListener(sure);
        dialog.show();
    }

    /**
     * 显示警告对话框
     *
     * @param title  标题
     * @param msg    内容
     * @param sure   确定回调
     * @param cancel 取消回调
     */
    public void showWarnDialog(String title, String msg, SweetAlertDialog.OnSweetClickListener sure, SweetAlertDialog.OnSweetClickListener cancel) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
        dialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
        dialog.setCancelable(false);
        dialog.setTitleText(title);
        dialog.setContentText(msg);
        if (sure != null) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
        }
        if (cancel != null) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
        }
        dialog.setCancelClickListener(cancel);
        dialog.setConfirmClickListener(sure);
        dialog.show();
    }

    /**
     * 显示警告对话框
     * 只有一个按钮的警告对话框，点击关闭即关闭。
     *
     * @param msg 内容
     */
    public void showWarnDialog(String msg) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext,
                SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog
                                                sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
        dialog.setContentText(msg);
        dialog.show();
    }

    /**
     * 显示警告对话框
     * 只有一个按钮的警告对话框，点击关闭即关闭。
     *
     * @param msg 内容
     */
    public void showWarnDialogAndFinish(String msg, final Activity activity) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext,
                SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                        activity.finish();
                    }
                });
        dialog.setContentText(msg);
        dialog.show();
    }

    /**
     * 显示警告对话框
     * 只有一个按钮的警告对话框，点击关闭即关闭。
     *
     * @param msg 内容
     */
    public void showWarnDialog(String msg, final SweetAlertDialog.OnDismissListener onDismiss) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext,
                SweetAlertDialog.WARNING_TYPE)
                .setTitleText("通知")
                .setConfirmText("退出")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        onDismiss.onDismiss(dialog);
                    }
                });
        dialog.setContentText(msg);
        dialog.show();
    }

    /**
     * 显示普通提示
     * <p>
     * 带回调的
     *
     * @param title  标题
     * @param msg    内容
     * @param sure   确定回调
     * @param cancel 取消回调
     */
    public void showNormalDialog(String title, String msg, SweetAlertDialog.OnSweetClickListener sure, SweetAlertDialog.OnSweetClickListener cancel) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
        dialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        dialog.setCancelable(true);
        dialog.setTitleText(title);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentText(msg);
        if (sure != null) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
        }
        if (cancel != null) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
        }
        dialog.setCancelClickListener(cancel);
        dialog.setConfirmClickListener(sure);
        dialog.show();
    }

    /**
     * 显示普通提示
     * <p>
     * 带回调的
     *
     * @param title  标题
     * @param msg    内容
     * @param sure   确定回调
     * @param cancel 取消回调
     */
    public void showNormalDialog(String title, String msg, SweetAlertDialog.OnSweetClickListener sure, SweetAlertDialog.OnSweetClickListener cancel, boolean cancelable) {
        closeDialog();
        dialog = new SweetAlertDialog(mContext, SweetAlertDialog.NORMAL_TYPE);
        dialog.changeAlertType(SweetAlertDialog.NORMAL_TYPE);
        dialog.setCancelable(true);
        dialog.setTitleText(title);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentText(msg);
        if (sure != null) {
            dialog.setConfirmText(mContext.getResources().getString(R.string.sdk_dialog_sure));
        }
        if (cancel != null) {
            dialog.setCancelText(mContext.getResources().getString(R.string.sdk_dialog_cancel));
        }
        dialog.setCancelClickListener(cancel);
        dialog.setConfirmClickListener(sure);
        dialog.show();
    }

    /**
     * @param title 标题
     * @param msg   内容
     * @param l     点击 回调
     */
    public void showProgressDialog(String title, String msg, ProgressDialog.OnClickListener l) {
        closeProgressDialog();
        progressDialog = new ProgressDialog(mContext);
        //设置进度条风格，风格为圆形，旋转的
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置ProgressDialog 标题
        progressDialog.setTitle(title);
        //设置ProgressDialog 提示信息
        progressDialog.setMessage(msg);
        //设置ProgressDialog 标题图标
        progressDialog.setIcon(android.R.drawable.ic_popup_reminder);
        //设置ProgressDialog的最大进度
        progressDialog.setMax(100);
        //设置ProgressDialog 的一个Button
        //progressDialog.setButton("取消", l);
        //设置ProgressDialog 是否可以按退回按键取消
        progressDialog.setCancelable(false);
        //显示
        progressDialog.show();
        //设置ProgressDialog的当前进度
        progressDialog.setProgress(0);
    }

    /***
     * 设置进度
     *
     * @param pos 进度
     * @param msg 内容
     */
    public void setProgress(int pos, String msg) {
        if (progressDialog == null) {
            return;
        }
        progressDialog.setProgress(pos);
        progressDialog.setMessage(msg);
    }

    /***
     * 显示内容
     * @param show 显示内容
     */
    public void changeContent(String show) {
        if (show == null) show = "";
        if (dialog != null && dialog.isShowing()) dialog.setContentText(show);
    }

    /**
     * 关闭进度框
     */
    public void closeProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
        progressDialog = null;
    }
}
