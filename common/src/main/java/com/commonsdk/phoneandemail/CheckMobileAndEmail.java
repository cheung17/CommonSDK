package com.commonsdk.phoneandemail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查电话号码和邮件地址是否正确
 *
 * @author ztx
 */
public class CheckMobileAndEmail {
    /**
     * 验证邮箱地址是否正确
     *
     * @param email @return
     * @return 是否正确
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)？\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles 手机号
     * @return 是否正确
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 纯数字校验
     *
     * @param number 字符串
     * @return 是否正确
     */
    public static boolean isNum(String number) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^[0-9]{5}$");
            Matcher m = p.matcher(number);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}