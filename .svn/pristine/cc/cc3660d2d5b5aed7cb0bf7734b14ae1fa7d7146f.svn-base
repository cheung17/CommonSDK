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
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LauncherAppsCompatVL extends LauncherAppsCompat {

    private PackageManager mPm;
    private LauncherApps mLauncherApps;
    private Context mContext;

    private Map<OnAppsChangedCallbackCompat, WrappedCallback> mCallbacks
            = new HashMap<OnAppsChangedCallbackCompat, WrappedCallback>();

    LauncherAppsCompatVL(Context context) {
        super();
        mPm = context.getPackageManager();
        mLauncherApps = (LauncherApps) context.getSystemService("launcherapps");
        this.mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public List<LauncherActivityInfoCompat> getActivityList(String packageName, UserHandleCompat user) {
        List<LauncherActivityInfo> list = mLauncherApps.getActivityList(packageName, user.getUser());
        if (list.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        ArrayList<LauncherActivityInfoCompat> compatList = new ArrayList<>(list.size());
        for (LauncherActivityInfo info : list) {
            /**
             * 不显示自己的图标在应用列表中。
             */
            if (mContext.getPackageName().equals(info.getApplicationInfo().packageName)) {
//                System.out.println("VL app desk dismiss packageName== !" + info.getApplicationInfo().packageName + "  " + mContext.getPackageName());
            } else {
//                System.out.println("packageName== !" + info.getApplicationInfo().packageName + "  " + mContext.getPackageName());
                compatList.add(new LauncherActivityInfoCompatVL(info));
            }
        }
        return compatList;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LauncherActivityInfoCompat resolveActivity(Intent intent, UserHandleCompat user) {
        LauncherActivityInfo activity = mLauncherApps.resolveActivity(intent, user.getUser());
        if (activity != null) {
            return new LauncherActivityInfoCompatVL(activity);
        } else {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void startActivityForProfile(ComponentName component, UserHandleCompat user,
                                        Rect sourceBounds, Bundle opts) {
        mLauncherApps.startMainActivity(component, user.getUser(), sourceBounds, opts);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showAppDetailsForProfile(ComponentName component, UserHandleCompat user) {
        mLauncherApps.startAppDetailsActivity(component, user.getUser(), null, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void addOnAppsChangedCallback(OnAppsChangedCallbackCompat callback) {
        WrappedCallback wrappedCallback = new WrappedCallback(callback);
        synchronized (mCallbacks) {
            mCallbacks.put(callback, wrappedCallback);
        }
        mLauncherApps.registerCallback(wrappedCallback);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void removeOnAppsChangedCallback(
            OnAppsChangedCallbackCompat callback) {
        WrappedCallback wrappedCallback = null;
        synchronized (mCallbacks) {
            wrappedCallback = mCallbacks.remove(callback);
        }
        if (wrappedCallback != null) {
            mLauncherApps.unregisterCallback(wrappedCallback);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean isPackageEnabledForProfile(String packageName, UserHandleCompat user) {
        return mLauncherApps.isPackageEnabled(packageName, user.getUser());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean isActivityEnabledForProfile(ComponentName component, UserHandleCompat user) {
        return mLauncherApps.isActivityEnabled(component, user.getUser());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static class WrappedCallback extends LauncherApps.Callback {
        private OnAppsChangedCallbackCompat mCallback;

        public WrappedCallback(OnAppsChangedCallbackCompat callback) {
            mCallback = callback;
        }

        public void onPackageRemoved(String packageName, UserHandle user) {
            mCallback.onPackageRemoved(packageName);
        }

        public void onPackageAdded(String packageName, UserHandle user) {
            mCallback.onPackageAdded(packageName);
        }

        public void onPackageChanged(String packageName, UserHandle user) {
            mCallback.onPackageChanged(packageName);
        }

        public void onPackagesAvailable(String[] packageNames, UserHandle user, boolean replacing) {
            mCallback.onPackagesAvailable(packageNames, replacing);
        }

        public void onPackagesUnavailable(String[] packageNames, UserHandle user,
                                          boolean replacing) {
            mCallback.onPackagesUnavailable(packageNames,
                    replacing);
        }
    }
}

