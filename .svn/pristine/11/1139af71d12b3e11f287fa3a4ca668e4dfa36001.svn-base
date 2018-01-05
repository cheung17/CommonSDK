package com.commonsdk.sdcard;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 内置存储卡，外置存储卡路径工具类
 *
 * @author ztx
 */
public class StorageUtils {
    /**
     * 是否显示debug信息。
     */
    private static final boolean debug = false;

    private static final String TAG = StorageUtils.class.getSimpleName();

    /**
     * 存储对象列表，（顺序为：内置存储卡，外置存储卡1号，外置存储卡2号，外置存储卡3号，）
     *
     * @param pContext 上下文
     * @return 返回存储对象列表
     */
    public static ArrayList<StorageBean> getStorageData(Context pContext) {
        final StorageManager storageManager = (StorageManager) pContext.getSystemService(Context.STORAGE_SERVICE);
        try {
            //得到StorageManager中的getVolumeList()方法的对象
            final Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");
            //---------------------------------------------------------------------

            //得到StorageVolume类的对象
            final Class<?> storageValumeClazz = Class.forName("android.os.storage.StorageVolume");
            //---------------------------------------------------------------------
            //获得StorageVolume中的一些方法
            final Method getPath = storageValumeClazz.getMethod("getPath");
            Method isRemovable = storageValumeClazz.getMethod("isRemovable");

            Method mGetState = null;
            //getState 方法是在4.4_r1之后的版本加的，之前版本（含4.4_r1）没有
            // （http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/4.4_r1/android/os/Environment.java/）
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                try {
                    mGetState = storageValumeClazz.getMethod("getState");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            //---------------------------------------------------------------------

            //调用getVolumeList方法，参数为：“谁”中调用这个方法
            final Object invokeVolumeList = getVolumeList.invoke(storageManager);
            //---------------------------------------------------------------------
            final int length = Array.getLength(invokeVolumeList);
            ArrayList<StorageBean> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                final Object storageValume = Array.get(invokeVolumeList, i);//得到StorageVolume对象
                final String path = (String) getPath.invoke(storageValume);
                final boolean removable = (Boolean) isRemovable.invoke(storageValume);
                String state = null;
                if (mGetState != null) {
                    state = (String) mGetState.invoke(storageValume);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        state = Environment.getStorageState(new File(path));
                    } else {
                        if (removable) {
                            state = EnvironmentCompat.getStorageState(new File(path));
                        } else {
                            //不能移除的存储介质，一直是mounted
                            state = Environment.MEDIA_MOUNTED;
                        }
                        final File externalStorageDirectory = Environment.getExternalStorageDirectory();
//                        Log.e(TAG, "externalStorageDirectory==" + externalStorageDirectory);
                    }
                }
                long totalSize = 0;
                long availaleSize = 0;
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    totalSize = StorageUtils.getTotalSize(path);
                    availaleSize = StorageUtils.getAvailableSize(path);
                }
                if (totalSize <= 0 || availaleSize <= 0) {
                    // 没有存储空间的，直接跳过。
                    continue;
                }
//                if (debug) {
//                    final String msg = "path==" + path
//                            + " ,removable==" + removable
//                            + ",state==" + state
//                            + ",total size==" + totalSize + "(" + StorageUtils.fmtSpace(totalSize) + ")"
//                            + ",availale size==" + availaleSize + "(" + StorageUtils.fmtSpace(availaleSize) + ")";
//                    Log.e(TAG, msg);
//                }
                StorageBean storageBean = new StorageBean();
                storageBean.setAvailableSize(availaleSize);
                storageBean.setTotalSize(totalSize);
                storageBean.setMounted(state);
                storageBean.setPath(path);
                storageBean.setRemovable(removable);
                list.add(storageBean);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回占用的空间大小 单位byte
     *
     * @param path 文件路径
     * @return 大小值
     */
    public static long getTotalSize(String path) {
        try {
            final StatFs statFs = new StatFs(path);
            long blockSize = 0;
            long blockCountLong = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = statFs.getBlockSizeLong();
                blockCountLong = statFs.getBlockCountLong();
            } else {
                blockSize = statFs.getBlockSize();
                blockCountLong = statFs.getBlockCount();
            }
            return blockSize * blockCountLong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 返回可用空间大小 单位byte
     *
     * @param path 文件路径
     * @return 大小值
     */
    public static long getAvailableSize(String path) {
        try {
            final StatFs statFs = new StatFs(path);
            long blockSize = 0;
            long availableBlocks = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                availableBlocks = statFs.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static final long A_GB = 1073741824;
    public static final long A_MB = 1048576;
    public static final int A_KB = 1024;

    /**
     * 格式化Byte数值
     *
     * @param space Byte数值
     * @return 格式化后的字符串
     */
    public static String fmtSpace(long space) {
        if (space <= 0) {
            return "0";
        }
        double gbValue = (double) space / A_GB;
        if (gbValue >= 1) {
            return String.format("%.2fGB", gbValue);
        } else {
            double mbValue = (double) space / A_MB;
            Log.e("GB", "gbvalue=" + mbValue);
            if (mbValue >= 1) {
                return String.format("%.2fMB", mbValue);
            } else {
                final double kbValue = space / A_KB;
                return String.format("%.2fKB", kbValue);
            }
        }
    }

    /***
     * 查找这个目录，在多个存储卡上是否存在
     * @param path    路径
     * @return string[0]代表在不在，string[1]这个应该是最后一个在的目录，文件大小要大于1.
     */
    public static String[] fileExist(Context pContext, String path) {
        String[] paths = new String[]{"0", ""};
        ArrayList<StorageBean> storages = StorageUtils.getStorageData(pContext);
        for (int i = 0; i < storages.size(); i++) {
            StorageBean storage = storages.get(i);
            if (storage.getMounted().equals(Environment.MEDIA_MOUNTED) || storage.getMounted().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
                //正常使用或只读
                File tempFile = new File(storage.getPath(), path);
                if (!tempFile.exists() || tempFile.length() < 1) {
                    continue;
                }
                paths[0] = "1";
                paths[1] = tempFile.getAbsolutePath();
            }
        }
        return paths;
    }

}
