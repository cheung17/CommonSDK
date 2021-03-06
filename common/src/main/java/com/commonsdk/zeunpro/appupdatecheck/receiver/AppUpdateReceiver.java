package com.commonsdk.zeunpro.appupdatecheck.receiver;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;

import com.commonsdk.application.BaseApplication;
import com.commonsdk.file.FileCompare;
import com.commonsdk.file.OpenEverTypeFile;
import com.commonsdk.sharedpreference.SharedPreferenceHelper;
import com.commonsdk.util.PackageUtils;
import com.commonsdk.util.ShowMyDialog;
import com.commonsdk.zeunpro.appupdatecheck.AppUpdateModel;
import com.zeunp.commansdk.R;

import java.io.File;
import java.util.HashSet;

/**
 * 接收应用有更新的广播
 *
 * @author ztx
 */
public class AppUpdateReceiver extends BroadcastReceiver {
    /**
     * 存储正在下载的列表
     */
    private static final String DOWNLOADLISTID = "DOWNLOADLISTPACKAGNAME";
    /**
     * 需要下载的内部广播
     */
    public static final String SHOW_NEW_VERSION_ACTION = "com.qimon.appupdatecheck.receiver.showupdatedialog";
    /**
     * 传递AppUpdateBean
     */
    public static final String DATA = "data";
    /**
     * 待升级应用的存放位置
     */
    public static final String DOWNLOADAPPPATH = Environment.getExternalStorageDirectory() + "/zeunpro/app/";
    /**
     * 用于显示更新内容
     */
    private ShowMyDialog mDialog;
    /**
     * 标记，dialog是否已经打开了。
     */
    private boolean dialogIsShow = false;
    /**
     * 系统自带的下载功能。
     */
    private DownloadManager downloadManager;
    /**
     * 数据存储工具类
     */
    private SharedPreferenceHelper shared;
    /**
     * 上下文
     */
    private BaseApplication baseApplication;
    /**
     * id+packageName组成的字符串列表。
     */
    private HashSet<String> mIdNameList;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Context application = context.getApplicationContext();
        if (intent != null && application instanceof BaseApplication) {
            baseApplication = (BaseApplication) application;
            if (mDialog == null) {
                mDialog = new ShowMyDialog(context);
            }
            if (downloadManager == null) {
                downloadManager = baseApplication.downloadManager;
            }
            shared = new SharedPreferenceHelper();
            mIdNameList = (HashSet<String>) shared.get(baseApplication, DOWNLOADLISTID, new HashSet<String>());
            System.out.println("任务 packageName id :" + mIdNameList.toString());
            if (SHOW_NEW_VERSION_ACTION.equals(action)) {
                AppUpdateModel updateModel = (AppUpdateModel) intent.getSerializableExtra(DATA);
                if (updateModel != null) {
                    showUpdateDialog(context, updateModel);
                }
            } else if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                // 在广播中取出下载任务的id
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                deletedOrOpen(context, action, id);
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action)) {
                // 在广播中取出下载任务的id
                long[] references = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
                if (references != null && references.length > 0) {
                    for (int i = 0; i < references.length; i++) {
                        long id = references[i];
                        deletedOrOpen(context, action, id);
                    }
                }
            }
        }
    }

    /**
     * 执行删除，或者打开操作
     *
     * @param context 上下文
     * @param action  动作
     * @param id      id
     */
    private void deletedOrOpen(Context context, String action, long id) {
        if (true) {
            //更新缓存信息
            mIdNameList.remove(returnObject(id + ""));
            shared.put(baseApplication, DOWNLOADLISTID, mIdNameList);
            System.out.println("删除任务：  " + mIdNameList.toString());
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);
            Cursor c = downloadManager.query(query);
            if (c.moveToFirst()) {
                if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action)) {
                    /**
                     *点击停止
                     */
                    downloadManager.remove(id);
                } else if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    /**
                     * 下载完成
                     */
                    // 获取文件下载路径
                    String filename = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                    // 如果文件名不为空，说明已经存在了，拿到文件名想干嘛都好
                    if (filename != null) {
                        System.out.println("下载完毕。");
                        OpenEverTypeFile.startActivityForOpenFile(context, filename);// 打开文件。
                    } else {
                        System.out.println("下载失败。");
                    }
                }
            }
            c.close();
        }
    }

    /**
     * 显示更新提醒框
     *
     * @param context    上下文
     * @param updateBean AppUpdateBean
     */
    @SuppressLint("NewApi")
    private void showUpdateDialog(Context context, AppUpdateModel updateBean) {
        if (context != null && updateBean != null) {
            System.out.println("updateBean :" + updateBean.toString());
            Builder builder = new Builder(context, R.style.update_sdk_blank_dialog);
            builder.setCancelable(false);// 屏蔽触摸关闭。
            builder.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                        // 屏蔽返回按钮。
                        return true;
                    }
                    return false;
                }
            });
            AlertDialog dialog = builder.create();
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.qimonsdk_bottom_dialog_anim); // 添加动画
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            View view = LayoutInflater.from(context).inflate(R.layout.zp_update_layout, null);
            initDialogLayout(context, view, dialog, updateBean);

        }
    }

    /**
     * 实例化弹出框的内容。
     *
     * @param context     上下文
     * @param viewContent 根布局
     * @param dialog      Dialog
     * @param updateBean  AppUpdateBean
     */
    private void initDialogLayout(final Context context, final View viewContent, final Dialog dialog, final AppUpdateModel updateBean) {
        if (updateBean != null) {
            if (!dialogIsShow) {
                dialogIsShow = true;
                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                dialog.show();
                dialog.addContentView(viewContent, lp);
            }
            Button ok = (Button) viewContent.findViewById(R.id.btn_update);
            Button cancel = viewContent.findViewById(R.id.btn_later);
            TextView view = viewContent.findViewById(R.id.tv_update_content);
            view.setText(updateBean.getUpdatecontent().replace("\\\\n", "\n"));
            // 判断是否已经下载到本地了。
            File dir = new File(DOWNLOADAPPPATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 下载按钮。
            final File app = new File(dir, context.getPackageName() + ".apk");
            if (app.exists()) {
                String md5 = FileCompare.getFileMD5(app).toLowerCase();
                if (!TextUtils.isEmpty(md5) && md5.equals(updateBean.getMd5().toLowerCase())) {
                    ok.setText("安装");
                    ok.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            PackageUtils.installNormal(context, app.getAbsolutePath());
                            // android.os.Process.killProcess(android.os.Process.myPid());
                            BaseApplication.baseApplication.exitSystem();
                        }
                    });
                } else {
                    app.delete();
                    ok.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            dialogIsShow = false;
                            try {
                                if (app.exists()) {
                                    // 删除本地数据，该数据可能是旧的apk，也可能是下载了一半的apk。
                                    app.delete();
                                }
                                String pk = context.getPackageName();
                                if (isDownloadingID(pk)) {
                                    //删除任务。
                                    String content = returnObject(pk);
                                    downloadManager.remove(Long.parseLong(content.substring(0, content.indexOf(","))));
                                    //清除缓存。
                                    mIdNameList.remove(returnObject(pk + ""));
                                    shared.put(baseApplication, DOWNLOADLISTID, mIdNameList);
                                }
                                if (false) { //以前是desk，现在直接走下面
                                    excuseDownload(context, downloadManager, context.getPackageName() + ".apk", DOWNLOADAPPPATH, updateBean.getApk_download_path(), "apk");
                                } else {
                                    excuseDownload(context, downloadManager, context.getPackageName() + ".apk", DOWNLOADAPPPATH, updateBean.getApk_download_path(), "apk");
                                }
                                if (updateBean.getIs_forced_update() == AppUpdateModel.ISFORCE_UPDATE_TRUE) {
//                                mDialog.ShowProgressBar("请稍侯", "正在为您请求数据...", true, true);
                                    BaseApplication.baseApplication.exitSystem();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else {
                ok.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialogIsShow = false;
                        try {
                            if (app.exists()) {
                                // 删除本地数据，
                                app.delete();
                            }
                            String pk = context.getPackageName();
                            if (isDownloadingID(pk)) {
                                //删除任务。
                                String content = returnObject(pk);
                                downloadManager.remove(Long.parseLong(content.substring(0, content.indexOf(","))));
                                //清除缓存。
                                mIdNameList.remove(returnObject(pk + ""));
                                shared.put(baseApplication, DOWNLOADLISTID, mIdNameList);
                            }
                            if (true) {
                                excuseDownload(context, downloadManager, context.getPackageName() + ".apk", DOWNLOADAPPPATH, updateBean.getApk_download_path(), "apk");
                            }
                            //下载
                            if (updateBean.getIs_forced_update() == AppUpdateModel.ISFORCE_UPDATE_TRUE) {
                                BaseApplication.baseApplication.exitSystem();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            // 取消按钮。
            if (updateBean.getIs_forced_update() == AppUpdateModel.ISFORCE_UPDATE_TRUE) {
                cancel.setText("退出");
                cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialogIsShow = false;
                        BaseApplication.baseApplication.exitSystem();
                    }
                });
            } else {
                cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialogIsShow = false;
                    }
                });
            }
        }
    }

    /**
     * 执行下载功能
     *
     * @param context         上下文
     * @param downloadManager 下载管理器
     * @param fileName        名字
     * @param filePath        存储的路径
     * @param downloadURL     下载的URL
     * @param fileType        文件的类别
     */
    @SuppressLint("NewApi")
    private void excuseDownload(Context context, DownloadManager downloadManager, String fileName, String filePath, String downloadURL, String fileType) {
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        Uri resource = Uri.parse(downloadURL);
        DownloadManager.Request request = new DownloadManager.Request(resource);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(true);
        // 设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(fileType);
        request.setMimeType(mimeString);
        // 在通知栏中显示
        request.setShowRunningNotification(true);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationUri(Uri.fromFile(new File(filePath, fileName)));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED | DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle(fileName);
        long id = downloadManager.enqueue(request);
        String packageName = fileName.substring(0, fileName.lastIndexOf("."));
        insertObject(id + "," + packageName);
        shared.put(baseApplication, DOWNLOADLISTID, mIdNameList);
        System.out.println("新任务：  " + mIdNameList.toString());
    }

    /**
     * 判断是否已经处于下载列表中了。
     *
     * @param id id
     * @return 是否已经处于下载列表中了。
     */
    private boolean isDownloadingID(String id) {
        if (mIdNameList != null) {
            for (String str : mIdNameList) {
                if (str.contains(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过id或者包名返回Set中的对象。
     *
     * @param check id或者包名
     * @return 字符串（id＋包名）
     */
    private String returnObject(String check) {
        if (mIdNameList != null) {
            for (String str : mIdNameList) {
                if (str.contains(check)) {
                    return str;
                }
            }
        }
        return "";
    }

    /**
     * 增加一个对象。
     *
     * @param content 对象
     */
    private void insertObject(String content) {
        if (mIdNameList != null) {
            mIdNameList.add(content);
        }
    }
}
