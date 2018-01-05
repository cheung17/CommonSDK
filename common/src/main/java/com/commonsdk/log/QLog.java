package com.commonsdk.log;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;

/**
 * 《强烈推荐使用该工具类》
 * 针对orhanobut包进行封装，相比直接使用orhanobut包的好处是，日后可以很方便的扩展功能，比如：存入文件。
 * <p/>
 * 该log工具类，能够打印日志所在线程名称，还能通过控制台跳转到该日志的代码位置（该功能很方便）。
 *
 * @author ztx
 */
public class QLog {
    /**
     * 初始化（写在Application中）。
     */
    public static void init() {
        Logger.init();
    }

    /**
     * 初始化并指定一个标示符（写在Application中）。
     *
     * @param tag 自定义的标示符号
     */
    public static void init(String tag) {
        Logger.init(tag);
    }

    public static void v(String message, Object... args) {
        Logger.v(message, args);
    }

    public static void d(Object args) {
        Logger.d(args);
    }

    public static void d(String message, Object... args) {
        Logger.d(message, args);
    }

    public static void e(String message, Object... args) {
        Logger.e(message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        Logger.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        Logger.i(message, args);
    }

    public static Printer t(int methodCount) {
        return Logger.t(methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return Logger.t(tag, methodCount);
    }

    public static Printer t(String tag) {
        return Logger.t(tag);
    }

    public static void w(String message, Object... args) {
        Logger.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        Logger.wtf(message, args);
    }

    public static void xml(String message) {
        Logger.xml(message);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

    public static void resetSettings() {
        Logger.resetSettings();
    }
}
