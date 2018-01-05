package com.commonsdk.http.util;

import android.text.TextUtils;

import com.commonsdk.util.ShowMyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 获得网络请求的返回值后，进行第一层的数据解析
 *
 * @author ztx
 */
public class HttpDataFirstParse {
    /**
     * 将http请求的固定字段：code，msg，data解析到map中返回。
     *
     * @param mToast 数据错误，或访问失败时，显示msg字段中的信息，传null，则不显示。
     * @param result 网络请求的返回值
     * @return HashMap
     */
    public static HashMap<String, String> parseResult(ShowMyToast mToast, String result) {
        if (!TextUtils.isEmpty(result)) {
            JSONObject jsonObject;
            try {
                HashMap<String, String> map = new HashMap<String, String>();
                jsonObject = new JSONObject(result);
                int code = jsonObject.getInt(HttpSDKConstant.CODE);
                String data = jsonObject.getString(HttpSDKConstant.DATA);
                String msg = jsonObject.getString(HttpSDKConstant.MSG);
                map.put(HttpSDKConstant.CODE, code + "");
                map.put(HttpSDKConstant.MSG, msg);
                map.put(HttpSDKConstant.DATA, data);
                if (HttpSDKConstant.SUCCESS != code) {
                    showToast(mToast, msg);
                }
                return map;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            showToast(mToast, "服务器返回数据为空。");
            return null;
        }
    }

    /**
     * 显示提示信息
     *
     * @param mToast  ShowMyToast
     * @param content 内容
     */
    private static void showToast(ShowMyToast mToast, String content) {
        if (mToast != null) {
            mToast.ShowToastShort(content);
        }
    }
}
