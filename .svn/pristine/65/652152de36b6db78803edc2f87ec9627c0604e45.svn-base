package com.commonsdk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LWL  on 2016/7/27.
 *         输入法工具类
 */
public class EntryUtils {
    /**
     * 去除字符串中的空格\t、回车\n、换行符\r、制表符\t
     *
     * @param str 需要处理的字符串
     * @return 清理之后的字符串
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
