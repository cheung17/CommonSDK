package com.commonsdk.sdcard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * SD卡挂载，移除，插入等操作的广播（演示，可能在实际开发中无法使用，但是可以借鉴）。
 *
 * @author ztx
 */
public class SDReceiver extends BroadcastReceiver {
    /**
     * SDReceiver
     */
    private static SDReceiver receiver;

    private SDReceiver() {
    }

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        if (arg1 != null) {
            String action = arg1.getAction();
            if (action != null) {
                if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
                    // Toast.makeText(arg0, "安装。", 1000).show();
                    System.out.println("ztx-----------       sd卡已经挂载。");
                } else if (action.equals(Intent.ACTION_MEDIA_REMOVED)) {
                    // Toast.makeText(arg0, "移除。", 1000).show();
                    System.out.println("ztx-----------       sd卡被移除。");
                } else if (action.equals(Intent.ACTION_MEDIA_BAD_REMOVAL)) {
                    // Toast.makeText(arg0, "移除失败。", 1000).show();
                    System.out.println("ztx--    sd卡已经从sd卡插槽拔出，但是挂载点还没解除。");
                } else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
                    // Toast.makeText(arg0, "移除失败2。", 1000).show();
                    System.out.println("ztx-------      sd卡存在，但还没有挂载。");
                } else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {
                    // Toast.makeText(arg0, "移除失败2。", 1000).show();
                    System.out.println("ztx-----------       开始扫描。");
                } else if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) {
                    // Toast.makeText(arg0, "移除失败2。", 1000).show();
                    System.out.println("ztx-----------       扫描完成。");
                }
            }
        }
    }

    /**
     * 注册广播监听
     *
     * @param context 句柄
     */
    public static void registerTFReceiver(Context context) {
        if (receiver == null) {
            receiver = new SDReceiver();
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(1000);// 设置最高优先级
            intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);// sd卡已经挂载
            intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);// sd卡存在，但还没有挂载
            intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);// sd卡被移除
            // intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);// sd卡作为
            // USB大容量存储被共享，挂载被解除
            intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);// sd卡已经从sd卡插槽拔出，但是挂载点还没解除
            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);// 开始扫描
            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);// 扫描完成
            intentFilter.addDataScheme("file");
            context.registerReceiver(receiver, intentFilter);
        } catch (Exception e) {
            receiver = null;
        }
    }

    /**
     * 注销广播监听
     *
     * @param context 句柄
     */
    public static void unRegisterTFReceiver(Context context) {
        if (receiver != null) {
            context.unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
