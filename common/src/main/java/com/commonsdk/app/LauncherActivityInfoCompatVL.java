/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.commonsdk.app;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class LauncherActivityInfoCompatVL extends LauncherActivityInfoCompat {
    private LauncherActivityInfo mLauncherActivityInfo;

    //  新增一下三个字段，用于显示自定义Action的应用图标。
    private ActivityInfo mActivityInfo;
    private ComponentName mComponentName;
    private PackageManager mPm;

    LauncherActivityInfoCompatVL(LauncherActivityInfo launcherActivityInfo) {
        super();
        mLauncherActivityInfo = launcherActivityInfo;
    }

    /**
     * 应用不遵循Android规定，不出现在普通桌面时，使用该构造函数。
     *
     * @param context
     * @param info
     */
    LauncherActivityInfoCompatVL(Context context, ResolveInfo info) {
        this.mActivityInfo = info.activityInfo;
        mComponentName = new ComponentName(mActivityInfo.packageName, mActivityInfo.name);
        mPm = context.getPackageManager();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ComponentName getComponentName() {
        if (mComponentName != null) {
            return mComponentName;
        }
        return mLauncherActivityInfo.getComponentName();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CharSequence getLabel() {
        if (mActivityInfo != null) {
            return mActivityInfo.loadLabel(mPm);
        }
        return mLauncherActivityInfo.getLabel();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Drawable getIcon(int density) {
        if (mActivityInfo != null) {
            Drawable d = null;
            if (mActivityInfo.getIconResource() != 0) {
                Resources resources;
                try {
                    resources = mPm.getResourcesForApplication(mActivityInfo.packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    resources = null;
                }
                if (resources != null) {
                    try {
                        d = resources.getDrawableForDensity(mActivityInfo.getIconResource(), density);
                    } catch (Resources.NotFoundException e) {
                        // Return default icon below.
                    }
                }
            }
            if (d == null) {
                Resources resources = Resources.getSystem();
                d = resources.getDrawableForDensity(android.R.mipmap.sym_def_app_icon, density);
            }
            return d;
        }
        return mLauncherActivityInfo.getIcon(density);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ApplicationInfo getApplicationInfo() {
        if (mActivityInfo != null) {
            return mActivityInfo.applicationInfo;
        }
        return mLauncherActivityInfo.getApplicationInfo();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public long getFirstInstallTime() {
        if (mPm != null) {
            try {
                PackageInfo info = mPm.getPackageInfo(mActivityInfo.packageName, 0);
                return info != null ? info.firstInstallTime : 0;
            } catch (PackageManager.NameNotFoundException e) {
                return 0;
            }
        }
        return mLauncherActivityInfo.getFirstInstallTime();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Drawable getBadgedIcon(int density) {
        if (mLauncherActivityInfo != null) {
            return mLauncherActivityInfo.getBadgedIcon(density);
        }
        return getIcon(density);
    }

    public String getName() {
        return mActivityInfo.name;
    }
}
