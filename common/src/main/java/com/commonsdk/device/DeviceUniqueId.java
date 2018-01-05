package com.commonsdk.device;

import android.content.Context;
import android.text.TextUtils;

import com.commonsdk.sim.GaotongDoubleInfo;
import com.commonsdk.sim.GeneralSimUtil;
import com.commonsdk.sim.MtkDoubleInfo;
import com.commonsdk.sim.SimCheck;

/**
 * 得到一个设备的唯一标示码，先获取imei号，如果是双卡设备，则把两个imei号连接起来作为一个设备标识号，如果是单卡的，就直接使用imei号，
 * 如果无法获取到imei号，获取设备串号。（Mac地址可以作为备选项。目前未实现）
 *
 * @author ztx
 */
public class DeviceUniqueId {
    /**
     * 得到一个设备的唯一标示码，先获取imei号，如果是双卡设备，则把两个imei号连接起来作为一个设备标识号，如果是单卡的，就直接使用imei号，
     * 如果无法获取到imei号，获取设备串号。
     *
     * @param c 上下文
     * @return 唯一标示码
     */
    public static String getUniqueId(Context c) {
        String imei = "";
        Object o = SimCheck.isDoubleSim(c);
        if (o == null) {
            imei = GeneralSimUtil.getIMEI(c);
            // System.out.println("单卡");
        } else if (o instanceof GaotongDoubleInfo) {
            String imei_2 = ((GaotongDoubleInfo) o).getImei_2();
            String imei_1 = ((GaotongDoubleInfo) o).getImei_1();
            if (!TextUtils.isEmpty(imei_1) && !TextUtils.isEmpty(imei_2)) {
                imei = imei_1 + imei_2;
            }
            // System.out.println("高通双卡");
        } else if (o instanceof MtkDoubleInfo) {
            String imei_2 = ((MtkDoubleInfo) o).getImei_2();
            String imei_1 = ((MtkDoubleInfo) o).getImei_1();
            if (!TextUtils.isEmpty(imei_1) && !TextUtils.isEmpty(imei_2)) {
                imei = imei_1 + imei_2;
            }
            // System.out.println("mtk双卡");
        }
        if (imei == null || TextUtils.isEmpty(imei.trim())) {
            // 获取设备序列号。
            imei = GeneralSimUtil.getSerialNumber();
        }
        return imei;
    }
}
