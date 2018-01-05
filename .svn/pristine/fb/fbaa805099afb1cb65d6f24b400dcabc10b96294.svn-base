package com.commonsdk.baidu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 判断系统是不是miui，flyme，emui
 */
public class OSUtils {
	private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
	private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
	private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
	private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

	private static boolean isPropertiesExist(String... keys) {
		try {
			BuildProperties prop = BuildProperties.newInstance();
			for (String key : keys) {
				String str = prop.getProperty(key);
				if (str == null)
					return false;
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public static boolean isMIUI() {
		try {
			final BuildProperties prop = BuildProperties.newInstance();
			return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
					|| prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
					|| prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
		} catch (final IOException e) {
			return false;
		}
	}
	public static boolean isEMUI() {
		return isPropertiesExist(KEY_EMUI_VERSION_CODE);
	}

	// public static boolean isMIUI() {
	// return isPropertiesExist(KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME,
	// KEY_MIUI_INTERNAL_STORAGE);
	// }

	public static boolean isFlyme() {
		try {
			final Method method = Build.class.getMethod("hasSmartBar");
			return method != null;
		} catch (final Exception e) {
			return false;
		}
	}

	public final static String SERVICE_ACTION = "com.baidu.android.pushservice.action.PUSH_SERVICE";

	public static String getHighPriorityPackage(Context context) {
		Intent i = new Intent(SERVICE_ACTION);
		List<ResolveInfo> localList = context.getPackageManager().queryIntentServices(i, 0);
		String myPkgName = context.getPackageName();
		String pkgName = "";
		long pkgPriority = 0;
		String appNameString = "";
		for (ResolveInfo info : localList) {
			if (!info.serviceInfo.exported) {
				continue;
			}
			String pkg = info.serviceInfo.packageName;
			if (!info.serviceInfo.exported) {
				continue;
			}
			String appName = info.loadLabel(context.getPackageManager()).toString();
			Context context1;
			try {
				context1 = context.createPackageContext(pkg, Context.CONTEXT_IGNORE_SECURITY);
			} catch (NameNotFoundException e) {
				continue;
			}
			String spName = new StringBuilder().append(pkg).append(".push_sync").toString();
			SharedPreferences sp = context1.getSharedPreferences(spName, Context.MODE_WORLD_READABLE);
			long priority = sp.getLong("priority2", 0L);
			if (priority > pkgPriority || (myPkgName.equals(pkg) && priority >= pkgPriority)) {
				pkgPriority = priority;
				pkgName = pkg;
				appNameString = appName;
			}
		}
		return appNameString;
	}
	public static String getHighPriorityPackageName(Context context) {
		Intent i = new Intent(SERVICE_ACTION);
		List<ResolveInfo> localList = context.getPackageManager().queryIntentServices(i, 0);
		String myPkgName = context.getPackageName();
		String pkgName = "";
		long pkgPriority = 0;
		String appNameString = "";
		for (ResolveInfo info : localList) {
			if (!info.serviceInfo.exported) {
				continue;
			}
			String pkg = info.serviceInfo.packageName;
			if (!info.serviceInfo.exported) {
				continue;
			}
			String appName = info.loadLabel(context.getPackageManager()).toString();
			Context context1;
			try {
				context1 = context.createPackageContext(pkg, Context.CONTEXT_IGNORE_SECURITY);
			} catch (NameNotFoundException e) {
				continue;
			}
			String spName = new StringBuilder().append(pkg).append(".push_sync").toString();
			SharedPreferences sp = context1.getSharedPreferences(spName, Context.MODE_WORLD_READABLE);
			long priority = sp.getLong("priority2", 0L);
			if (priority > pkgPriority || (myPkgName.equals(pkg) && priority >= pkgPriority)) {
				pkgPriority = priority;
				pkgName = pkg;
				appNameString = appName;
			}
		}
		return pkgName;
	}
}