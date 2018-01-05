package com.commonsdk.application;

import android.app.Activity;
import android.app.Application;
import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.commonsdk.log.QLog;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.L;
import com.commonsdk.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Application基类
 *
 * @author ztx
 */
public class BaseApplication extends Application {
    /**
     * 百度消息，通道编号
     */
    public String ChannelID = "";
    /**
     * 系统自带的下载功能。
     */
    public DownloadManager downloadManager;
    /**
     * 本地广播（只能在应用内部传播）
     */
    private LocalBroadcastManager lbm;

    //******************************************以上是自检更新功能所需变量
    /**
     * TAG
     */
    private static String tag = "QimonApp";
    /**
     * 启动的界面列表
     */
    public HashMap<String, Activity> activitieList = new HashMap<>();
    /**
     * Application基类
     */
    public static BaseApplication baseApplication;
    /**
     * 加载图片连接超时
     **/
    int ImageConnetTimeOut = 10 * 1000;
    /**
     * 加载图片读取时间
     **/
    int ImageReadTimeOut = 60 * 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        baseApplication = this;
        // 注册自定义的崩溃异常处理函数
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        //日志数据库
        try {
            QLog.init(this.getApplicationInfo().packageName);
        } catch (Exception e) {
            QLog.init();
        }
        init();
    }

    /**
     * 初始化
     */
    protected void init() {
        // 初始化图片加载
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).memoryCache(new WeakMemoryCache()).defaultDisplayImageOptions(defaultOptions).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().tasksProcessingOrder(QueueProcessingType.LIFO).imageDownloader(new BaseImageDownloader(this, ImageConnetTimeOut, ImageReadTimeOut)).build();
        ImageLoader.getInstance().init(config);
        //关闭ImageLoader Log
        L.disableLogging();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("OkHttpUtils"))
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * activity压栈
     *
     * @param id       id
     * @param activity 界面对象
     */
    public void pushApplication(String id, Activity activity) {
        activitieList.put(id, activity);
//        System.out.println(tag + "::::::::activity入栈       " + "id-->" + id + "--->" + activity.getClass().getName());
    }

    /**
     * 返回列表长度
     *
     * @return 列表长度
     */
    public int getcount() {
        return activitieList.size();
    }

    /**
     * activity出栈
     *
     * @param id id
     */
    public void pullApplication(String id) {
        if (activitieList.containsKey(id)) {
//            System.out.println(tag + "::::::::activity出栈       " + "id-->" + id + "--->" + activitieList.get(id).getClass().getName());
            activitieList.remove(id);
        }
    }

    /**
     * 退出系统
     */
    public void exitSystem() {
        String packageName = getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return;
        }
        // 退出系统数据处理
        // Provider.clearSystemCache();
        exitAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    /**
     * 逆时间弹出所有activity
     */
    public void exitAllActivity() {
        ArrayList<String> ids = new ArrayList<>();
        for (String id : activitieList.keySet()) {
            long key = Long.parseLong(id);
            if (ids.size() == 0) {
                ids.add(id);
            } else {
                for (int i = 0; i < ids.size(); i++) {
                    long l = Long.parseLong(ids.get(i));
                    if (key > l) {
                        ids.add(i, id);
                        break;
                    }
                    if (i == ids.size() - 1) {
                        ids.add(id);
                        break;
                    }
                }
            }
        }
        for (String id : ids) {
            activitieList.get(id).finish();
//            System.out.println(tag + "::::::::activity出栈       " + "id-->" + id + "--->" + activitieList.get(id).getClass().getName());
        }

    }

    /**
     * 获取实例化BaseApplication对象
     *
     * @return BaseApplication对象
     */
    public static BaseApplication getInstance() {
        return baseApplication;
    }
}
