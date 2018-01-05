package com.commonsdk.zeunpro.orm;

import android.content.Context;
import android.text.TextUtils;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

/**
 * 需要 extends BaseApplication的才能使用
 *
 * @author ztx
 */

public class ZeunproOrm {
    /**
     * 数据库操作对象
     */
    private static LiteOrm liteOrm;

    /***
     * 取得当前数据库操作对象
     * @return liteorm
     */
    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }

    /***
     * 默认初始化
     *
     * @param pContext 上下文
     */
    public static void defaultInit(Context pContext) {
        defaultInit(pContext, "");
    }

    public static void defaultInit(Context pContext, boolean single) {
        defaultInit(pContext, "", single);
    }

    /***
     * 默认初始化
     *
     * @param pContext 上下文
     * @param dbName db名(可以是路径)
     */
    public static void defaultInit(Context pContext, String dbName) {
        defaultInit(pContext, dbName, true);
    }

    /***
     * 默认初始化
     *
     * @param pContext 上下文
     * @param dbName db名(可以是路径)
     */
    public static void defaultInit(Context pContext, String dbName, boolean single) {
        if (pContext != null) {
            if (TextUtils.isEmpty(dbName)) dbName = pContext.getPackageName();
            DataBaseConfig defaultConfig = new DataBaseConfig(pContext.getApplicationContext(), dbName);
            liteOrm = single ? LiteOrm.newSingleInstance(defaultConfig) : LiteOrm.newCascadeInstance(defaultConfig);
        }
    }

}
