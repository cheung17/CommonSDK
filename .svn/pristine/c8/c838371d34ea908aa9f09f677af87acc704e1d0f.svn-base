package com.commonsdk.application;

/**
 * Buid中所存放的手机硬件信息。
 *
 * @author 小F君
 */
public class DeviceInfo {
    /**
     * 主板
     */
    public static final String BOARD = "主板";
    /**
     * 系统定制商
     */
    public static final String BRAND = "系统定制商";
    /**
     * CPU指令集
     */
    public static final String CPU_ABI = "CPU指令集1";
    /**
     * CPU指令集
     */
    public static final String CPU_ABI2 = "CPU指令集2";
    /**
     * 设备型号
     */
    public static final String DEVICE = "设备型号";
    /**
     * 显示屏参数
     */
    public static final String DISPLAY = "显示屏参数";
    /**
     * 手机唯一标识码
     */
    public static final String FINGERPRINT = "手机唯一标识码";
    /**   */
    // public static final String HOST = "HOST";
    /**
     * 修订版本列表
     */
    public static final String ID = "修订版本列表";
    /**
     * 硬件制造商
     */
    public static final String MANUFACTURER = "硬件制造商";
    /**
     * 手机型号
     */
    public static final String MODEL = "手机型号";
    /**
     * 手机制造商
     */
    public static final String PRODUCT = "手机制造商 ";
    /**
     * 描述build的标签
     */
    public static final String TAGS = "描述BUILD的标签 ";
    /**
     * 时间
     */
    public static final String TIME = "时间";
    /**
     * builder类型
     */
    public static final String TYPE = "BUILDER类型";
    /**  */
    // public static final String USER = "USER";
    /**
     * 系统引导程序版本号
     */
    public static final String BOOTLOADER = "系统引导程序版本号";
    /**
     * 未知的
     */
    public static final String UNKNOWN = "未知的";
    /**
     * 硬件名
     */
    public static final String HARDWARE = "硬件名";
    /** */
    // public static final String IS_DEBUGGABLE = "IS_DEBUGGABLE";
    /**
     * 收音机固件版本
     */
    public static final String RADIO = "收音机固件版本";
    /**
     * 硬件序列号
     */
    public static final String SERIAL = "硬件序列号";
    /**
     * SDK版本号
     */
    public static final String SDK = "SDK版本号";
    /**
     * 系统版本号
     */
    public static final String RELEASE = "系统版本号";

    public enum Param {
        /**
         * 硬件信息字段
         */
        BOARD, BRAND, CPU_ABI, CPU_ABI2, DEVICE, DISPLAY, FINGERPRINT, HOST, ID, MANUFACTURER, MODEL, PRODUCT, TAGS, TIME, TYPE, USER, BOOTLOADER, UNKNOWN, HARDWARE, IS_DEBUGGABLE, RADIO, SERIAL, SDK, RELEASE;

        /**
         * 获取字段对象的设备信息
         *
         * @param key 字段KEY
         * @return 设备信息
         */
        public static String SwitchValue4Key(String key) {
            Param param = null;
            try {
                param = valueOf(key);
            } catch (Exception e) {

            }
            if (param == null) {
                return null;
            }
            switch (param) {
                case BOARD:
                    return DeviceInfo.BOARD;
                case BRAND:
                    return DeviceInfo.BRAND;
                case CPU_ABI:
                    return DeviceInfo.CPU_ABI;
                case CPU_ABI2:
                    return DeviceInfo.CPU_ABI2;
                case DEVICE:
                    return DeviceInfo.DEVICE;
                case DISPLAY:
                    return DeviceInfo.DISPLAY;
                case FINGERPRINT:
                    return DeviceInfo.FINGERPRINT;
                case ID:
                    return DeviceInfo.ID;
                case MANUFACTURER:
                    return DeviceInfo.MANUFACTURER;
                case MODEL:
                    return DeviceInfo.MODEL;
                case PRODUCT:
                    return DeviceInfo.PRODUCT;
                case TAGS:
                    return DeviceInfo.TAGS;
                case TIME:
                    return DeviceInfo.TIME;
                case TYPE:
                    return DeviceInfo.TYPE;
                case BOOTLOADER:
                    return DeviceInfo.BOOTLOADER;
                case UNKNOWN:
                    return DeviceInfo.UNKNOWN;
                case HARDWARE:
                    return DeviceInfo.HARDWARE;
                case RADIO:
                    return DeviceInfo.RADIO;
                case SERIAL:
                    return DeviceInfo.SERIAL;
                case SDK:
                    return DeviceInfo.SDK;
                case RELEASE:
                    return DeviceInfo.RELEASE;
                default:
                    return key;
            }
        }
    }
}
