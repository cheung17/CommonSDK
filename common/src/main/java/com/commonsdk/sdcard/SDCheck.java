package com.commonsdk.sdcard;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 判断是否存在外置SD卡的工具类
 *
 * @author ztx
 */
public class SDCheck {
    /**
     * 某些设备独有的sdcard路径。用于判断外置sd卡是否存在。(可对应修改)
     */
    public static final String SD_FILEPATH = "sdcard/external_sd/Android";

    /**
     * 判断是否有外置tf卡(现在手机大多数会有一个内置的存储空间，该函数主要用于判断外置tf卡)。
     *
     * @return
     */
    public static boolean CheckHasOuterTF(Context cxt) {
        boolean hasSDCard;
        // 判断是否存在外置sd卡
        if (android.os.Build.MODEL.toLowerCase().contains("XXX")) {
            //某些设备，特殊判断。(“XXX”需要对应修改)
            File file = new File(SD_FILEPATH);
            if (file.exists()) {
                hasSDCard = true;
            } else {
                try {
                    if (file.mkdirs()) {
                        hasSDCard = true;
                    } else {
                        hasSDCard = false;
                    }
                } catch (Exception e) {
                    hasSDCard = false;
                }
            }
        } else {
            // 常规机判断。
            List<String> storagePaths = getStoragePaths(cxt);
            // 判断文件路径个数。
            if (storagePaths != null && storagePaths.size() >= 2) {
                hasSDCard = true;
            } else {
                hasSDCard = false;
            }
        }
        return hasSDCard;
    }

    /**
     * 判断内置存储空间是否存在。
     *
     * @return 内置存储空间是否存在
     */
    public static boolean CheckHasTF() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 通过获取所挂机的存储路径个数，来判断是否挂接了SD卡。
     * <p/>
     * [/storage/emulated/0(内置SD卡路径), /storage/sdcard1(外挂SD卡路径)]。
     *
     * @param cxt 句柄
     * @return 路径列表
     */
    public static List<String> getStoragePaths(Context cxt) {
        List<String> pathsList = new ArrayList<String>();
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.GINGERBREAD_MR1) {
            File externalFolder = Environment.getExternalStorageDirectory();
            if (externalFolder != null) {
                pathsList.add(externalFolder.getAbsolutePath());
                pathsList.add(externalFolder.getAbsolutePath());
            }
        } else {
            StorageManager storageManager = (StorageManager) cxt.getSystemService(Context.STORAGE_SERVICE);
            try {
                Method method = StorageManager.class.getDeclaredMethod("getVolumePaths");
                method.setAccessible(true);
                Object result = method.invoke(storageManager);
                if (result != null && result instanceof String[]) {
                    String[] pathes = (String[]) result;
                    StatFs statFs;
                    for (String path : pathes) {
                        if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                            statFs = new StatFs(path);
                            if (statFs.getBlockCount() * statFs.getBlockSize() != 0) {
                                pathsList.add(path);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                File externalFolder = Environment.getExternalStorageDirectory();
                if (externalFolder != null) {
                    pathsList.add(externalFolder.getAbsolutePath());
                }
            }
        }
        return pathsList;
    }
}
