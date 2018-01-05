package com.commonsdk.password;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 弱密码检测。
 *
 * @author ztx
 */
public class PasswordCheck {
    /**
     * 提示信息
     */
    private static String CHECKMSG = "";

    /**
     * 检查是否为弱密码
     *
     * @param pwd 密码
     * @return 是否为弱密码
     */
    public static boolean isSimplePwdCheck(String pwd) {
        return PwdCheck(pwd);
        // return simplePwd.contains(pwd);"很抱歉，您输入的密码较弱请重新输入。"
    }

    /**
     * 检查是否为弱密码
     *
     * @param pwd 密码
     * @return 是否为弱密码
     */
    private static boolean PwdCheck(String pwd) {
        if (!TextUtils.isEmpty(pwd)) {
            if (pwd.length() != 8) {
                CHECKMSG = "密码只能为8位，且首尾不能是空格。";
                return true;
            }
            String match = ".*\\d+.*";
            Pattern pattern = Pattern.compile(match);
            Matcher mat = pattern.matcher(pwd);
            boolean cotaintNumber = mat.matches();
            if (!cotaintNumber) {
                CHECKMSG = "密码必须由数字，字母，符号组成。";
                return true;
            }
            match = ".*[a-zA-Z]+.*";
            boolean containtABC = pwd.matches(match);
            if (!containtABC) {
                CHECKMSG = "密码必须由数字，字母，符号组成。";
                return true;
            }
            match = ".*[^a-zA-Z0-9]+.*";
            boolean containt = pwd.matches(match);
            if (!containt) {
                CHECKMSG = "密码必须由数字，字母，符号组成。";
                return true;
            }
            return false;
        } else {
            CHECKMSG = "密码不能为空。";
            return true;
        }
    }

    /**
     * 简单密码
     */
    public static final ArrayList<String> simplePwd = new ArrayList<String>() {
        {
            add("111111");
            add("222222");
            add("333333");
            add("444444");
            add("555555");
            add("666666");
            add("777777");
            add("888888");
            add("999999");
            add("000000");
            add("123456");
            add("654321");
            add("098765");
            add("567890");
            add("987654");
            add("456789");
        }
    };

    public static String getCHECKMSG() {
        return CHECKMSG;
    }
}
