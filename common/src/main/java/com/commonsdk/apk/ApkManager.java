package com.commonsdk.apk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * APK信息管理类
 *
 * @author ztx
 */
public class ApkManager {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 包管理类
     */
    private PackageManager pm;
    /**
     * 应用信息类
     */
    private ApplicationInfo info;
    /**
     * 包信息
     */
    private PackageInfo packinfo;

    /**
     * 获取已经安装了的apk信息。
     *
     * @param packageName apk包名，缺省则获取自身apk包名。
     * @param context     上下文
     * @throws NameNotFoundException
     */
    public ApkManager(String packageName, Context context) throws NameNotFoundException {
        this.context = context;
        pm = context.getPackageManager();
        if (!TextUtils.isEmpty(packageName)) {
            info = pm.getApplicationInfo(packageName, 0);
            packinfo = pm.getPackageInfo(packageName, 0);
        } else {
            info = pm.getApplicationInfo(context.getPackageName(), 0);
            packinfo = pm.getPackageInfo(context.getPackageName(), 0);
        }
    }

    /**
     * 获取apk文件的信息
     *
     * @param context 上下文
     * @param apkPath apk文件路径。
     * @throws Exception
     * @throws NameNotFoundException
     */
    public ApkManager(Context context, String apkPath) throws Exception {
        if (TextUtils.isEmpty(apkPath)) {
            throw new NullPointerException("apk路径不能为空。");
        }
        if (!new File(apkPath).exists()) {
            throw new FileNotFoundException("apk不存在。");
        }
        try {
            pm = context.getPackageManager();
            packinfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            info = packinfo.applicationInfo;
            info.sourceDir = apkPath;
            info.publicSourceDir = apkPath;
        } catch (Exception e) {
            throw new Exception("apk文件解析失败。");
        }
    }

    /**
     * 获取包名
     *
     * @return 包名
     * @throws Exception
     */
    public String getAppPackage() throws Exception {
        String appPackage = null;
        if (packinfo != null) {
            appPackage = packinfo.packageName;
        } else {
            throw new Exception("PackageInfo is null !");
        }
        return appPackage;
    }

    /**
     * 获取程序 图标
     *
     * @return 图标
     */
    public Drawable getAppIcon() throws Exception {
        if (info != null) {
            return info.loadIcon(pm);
        } else {
            throw new Exception("ApplicationInfo is null !");
        }
    }

    /**
     * 获取程序的版本号
     *
     * @return 字符串版本号
     */
    public String getAppVersion() throws Exception {
        if (packinfo != null) {
            return packinfo.versionName;
        } else {
            throw new Exception("PackageInfo is null !");
        }
    }

    /**
     * 获取程序的版本号
     *
     * @return 数值版本号
     */
    public int getAppVersionCode() throws Exception {
        if (packinfo != null) {
            return packinfo.versionCode;
        } else {
            throw new Exception("PackageInfo is null !");
        }
    }

    /**
     * 获取程序的名字
     *
     * @return 程序的名字
     */
    public String getAppName() throws Exception {
        if (info != null) {
            return info.loadLabel(pm).toString();
        } else {
            throw new Exception("ApplicationInfo is null !");
        }
    }

    /**
     * 获取程序的权限
     *
     * @return 权限数组
     */
    public String[] getAppPremission() throws Exception {
        if (packinfo != null) {
            // 获取到所有的权限
            return packinfo.requestedPermissions;
        } else {
            throw new Exception("PackageInfo is null !");
        }
    }

    /**
     * 获取程序的签名
     *
     * @return 签名特征字符串
     */
    public String getAppSignature() throws Exception {
        if (packinfo != null) {
            // 获取到所有的权限
            return packinfo.signatures[0].toCharsString();
        } else {
            throw new Exception("PackageInfo is null !");
        }
    }
}
