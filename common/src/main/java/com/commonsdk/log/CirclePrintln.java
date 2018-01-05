package com.commonsdk.log;

/**
 * 循环打印log，工具类
 *
 * @author ztx
 */
public class CirclePrintln {
    /**
     * 循环打印，直到打印完毕。（system.out有最大输出限制）
     *
     * @param content 需要输出的内容
     */
    public static void printOut(String content) {
        String sub = "";

        int len = 1000;
        while (((sub = content.substring(0, len > content.length() ? content.length() : len)).length() > 0)) {
            System.out.println(sub);
            content = content.substring(len > content.length() ? content.length() : len);
        }
    }

    /**
     * 循环打印，直到打印完毕。（system.err有最大输出限制）
     *
     * @param content 需要输出的内容
     */
    public static void printError(String content) {
        String sub = "";
        int len = 1000;
        while (((sub = content.substring(0, len > content.length() ? content.length() : len)).length() > 0)) {
            System.err.println(sub);
            content = content.substring(len > content.length() ? content.length() : len);
        }
    }
}
