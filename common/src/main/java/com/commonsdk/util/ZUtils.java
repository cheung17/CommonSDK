package com.commonsdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;


import com.commonsdk.app.AppInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 不知道的功能放在这里
 *
 * @author zeffect
 *         <p>
 *         <ul>
 *         <li> {@link ZUtils#isPhoneNumber(String)} 是不是手机号</li>
 *         <li>  {@link ZUtils#isEmail(String)} 是不是邮箱</li>
 *         <li> {@link ZUtils#isNumber(String)} 是不是数字</li>
 *         <li> {@link ZUtils#getAllAppInfo(Context, int)} 获得手机应用</li>
 *         </ul>
 */
public class ZUtils {

    /**
     * 获得所有应用，封装成这个样子 [{ “appname”:”广告屏幕”, “apppackagename”:”com.adsafe”, “version”:”1.0”, “tag”:”0” }, { “appname”:”测试”， “apppackagename”:”com.test”, “version”:”1.0”, “tag”:”0” } ]”
     *
     * @param tag     0代表系统应用 1代表普通应用
     * @param context 上下文
     * @return 应用数据
     */
    public static String getAllAppInfo(Context context, int tag) {
        List<PackageInfo> packageInfos = AppInfo.getAllInstalledAppInfo(context);
        JSONArray n_JsonArray = new JSONArray();
        JSONArray s_JsonArray = new JSONArray();
        try {
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo temp = packageInfos.get(i);
                JSONObject tempJsonObject = new JSONObject();
                tempJsonObject.put("appname", temp.applicationInfo.loadLabel(context.getPackageManager()));
                tempJsonObject.put("apppackagename", temp.packageName);
                tempJsonObject.put("version", temp.versionCode);
                tempJsonObject.put("tag", temp.applicationInfo.flags);
                if ((temp.applicationInfo.flags & temp.applicationInfo.FLAG_SYSTEM) <= 0) {// 该值大于0时，表示获取的应用为系统预装的应用，反之则为手动安装的应用
                    // customs applications
                    n_JsonArray.put(tempJsonObject);// 普通应用
                } else {
                    s_JsonArray.put(tempJsonObject);// 系统应用
                }

            }
        } catch (Exception e) {

        }
        if (tag == 0) {
            return s_JsonArray.toString().trim();
        } else {
            return n_JsonArray.toString().trim();
        }
    }

    /**
     * 返回是不是手机号，目前有的手机段
     * <p>
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、182、187、188
     * <p>
     * 联通：130、131、132、152、155、156、185、186
     * <p>
     * 电信：133、153、180、189、（1349卫通）
     *
     * @param mobiles 电话
     * @return 是否是电话
     */
    public static boolean isPhoneNumber(String mobiles) {
        String check = "^((13[0-9])|(15[^4,\\D])|(18[0-2,5-9]))\\d{8}$";// "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 是不是邮箱
     *
     * @param email 邮箱
     * @return 是否
     */
    public static boolean isEmail(String email) {
        String check = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 判断字符串是否是整数
     *
     * @param value 值
     * @return 是不是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     *
     * @param value 值
     * @return 是否
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains(".")) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     *
     * @param value 值
     * @return 是不是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

//    /**
//     * 将隐式启动转换为显示启动，可能存两个Action一样的服务，能够确定包名的情况下不使用
//     * <p>
//     * 例子参考// http://blog.csdn.net/qs_csu/article/details/45114251
//     *
//     * @param context
//     * @param implicitIntent
//     * @return
//     */
//    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
//        PackageManager pm = context.getPackageManager();
//        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
//        if (resolveInfo == null || resolveInfo.size() != 1) {
//            return null;
//        }
//        ResolveInfo serviceInfo = resolveInfo.get(0);
//        String packageName = serviceInfo.serviceInfo.packageName;
//        String className = serviceInfo.serviceInfo.name;
//        ComponentName component = new ComponentName(packageName, className);
//        Intent explicitIntent = new Intent(implicitIntent);
//        explicitIntent.setComponent(component);
//        return explicitIntent;
//    }
}
