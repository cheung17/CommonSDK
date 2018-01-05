package com.commonsdk.sim;

/**
 * MTK平台双卡设备手机信息
 *
 * @author ztx
 */
public class MtkDoubleInfo {
    /**
     * 获取卡1的simId键值key
     */
    private int SimId_1;
    /**
     * 获取卡2的simId键值key
     */
    private int SimId_2;
    /**
     * sim卡1编号
     */
    private String Imsi_1;
    /**
     * sim卡2编号
     */
    private String Imsi_2;
    /**
     *
     */
    private String DefaultImsi;
    /**
     * imei卡1编号
     */
    private String Imei_1 = "";
    /**
     * imei卡2编号
     */
    private String Imei_2 = "";
    /**
     *
     */
    private int PhoneType_1;
    /**
     *
     */
    private int PhoneType_2;
    /**
     * 表示，是否为mtk平台双卡手机。
     */
    private boolean MtkDoubleSim;

    public int getSimId_1() {
        return SimId_1;
    }

    public void setSimId_1(int simId_1) {
        SimId_1 = simId_1;
    }

    public int getSimId_2() {
        return SimId_2;
    }

    public void setSimId_2(int simId_2) {
        SimId_2 = simId_2;
    }

    public String getImsi_1() {
        return Imsi_1;
    }

    public void setImsi_1(String imsi_1) {
        Imsi_1 = imsi_1;
    }

    public String getImsi_2() {
        return Imsi_2;
    }

    public void setImsi_2(String imsi_2) {
        Imsi_2 = imsi_2;
    }

    public String getImei_1() {
        return Imei_1;
    }

    public void setImei_1(String imei_1) {
        Imei_1 = imei_1;
    }

    public String getImei_2() {
        return Imei_2;
    }

    public void setImei_2(String imei_2) {
        Imei_2 = imei_2;
    }

    public int getPhoneType_1() {
        return PhoneType_1;
    }

    public void setPhoneType_1(int phoneType_1) {
        PhoneType_1 = phoneType_1;
    }

    public int getPhoneType_2() {
        return PhoneType_2;
    }

    public void setPhoneType_2(int phoneType_2) {
        PhoneType_2 = phoneType_2;
    }

    public String getDefaultImsi() {
        return DefaultImsi;
    }

    public void setDefaultImsi(String defaultImsi) {
        DefaultImsi = defaultImsi;
    }

    public boolean isMtkDoubleSim() {
        return MtkDoubleSim;
    }

    public void setMtkDoubleSim(boolean mtkDoubleSim) {
        MtkDoubleSim = mtkDoubleSim;
    }

}
