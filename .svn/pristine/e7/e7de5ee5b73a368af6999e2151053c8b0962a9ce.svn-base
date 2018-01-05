package com.commonsdk.idcard;

import android.content.Context;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证信息验证工具类
 *
 * @author ztx
 */
public class IDCardCheck {
    /**
     * 用于验证的数组
     */
    private static final String[] VAL_CODE_ARR = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
    /**
     * 用于验证的数组
     */
    private static final String[] WI = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    /**
     * 上下文
     */
    private Context context;

    public IDCardCheck(Context context) {
        this.context = context;
    }

    /**
     * 是否为正确的身份证号
     *
     * @param value 待验证的身份证，号码。
     * @return 是否正确
     */
    public synchronized boolean isIDCode(String value) {
        Hashtable<String, String> hashtable = getAreaCode();
        if (value.length() == 18) {
            try {
                Long.parseLong(value.substring(0, 17));
            } catch (Exception e) {
                return false;
            }
            if (!hashtable.containsKey(value.substring(0, 2)) || !rightDate(value.substring(6, 14))) {
                // Toast.makeText(context, "身份证号码错误，疑为假证。", 1000).show();
                return false;
            } else {
                int TotalmulAiWi = 0;
                for (int i = 0; i < 17; i++) {
                    TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(value.charAt(i))) * Integer.parseInt(WI[i]);
                }
                int modValue = TotalmulAiWi % 11;
                String strVerifyCode = VAL_CODE_ARR[modValue];
//                if (strVerifyCode.equalsIgnoreCase((String) value.subSequence(17, value.length()))) {
//                    return true;
//                } else {
//                    // Toast.makeText(context, "身份证号码错误，疑为假证。", 1000).show();
//                    return false;
//                }
                return strVerifyCode.equalsIgnoreCase((String) value.subSequence(17, value.length()));
            }
        } else if (value.length() == 15) {
            try {
                Long.parseLong(value);
            } catch (Exception e) {
                return false;
            }
            if (!hashtable.containsKey(value.substring(0, 2)) || !rightDate("19" + value.substring(6, 12))) {
                // Toast.makeText(context, "身份证号码错误，疑为假证。", 1000).show();
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    /**
     * 日期正确性。19980228
     *
     * @param str 出生年月日
     * @return 正确／不正确
     */
    private boolean rightDate(String str) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
        Matcher matcher = pattern.matcher((CharSequence) str);
        boolean result = matcher.matches();
        return result;
    }

    /**
     * 构建城市和code的hashmap
     *
     * @return 构建城市和code的hashmap
     */
    private static Hashtable getAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
}
