package com.commonsdk.util;

import android.net.Uri;

/**
 * Created by zhangtx on 2017/11/30.
 */

public class ToolUtil {
    /**
     * 对连接地址进行编码
     *
     * @param url 邻接
     * @return 编码后地址
     */
    public static String encodeUrl(String url) {
        return Uri.encode(url, "-![.:/,%?&=]");

    }
}
