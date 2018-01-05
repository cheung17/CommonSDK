package com.commonsdk.zhy.http.okhttp.callback;

import android.content.Context;
import android.text.TextUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import okhttp3.Response;

/**
 * 网络接口回调
 * Created by ztx
 */
public abstract class MyCallback extends Callback<String> {


    /**
     * 尽量不使用，推荐使用
     *
     * @see MyCallback(Context)
     * 方便检测登录超时
     */
    @Deprecated
    public MyCallback() {
    }

    public MyCallback(Context pContext) {
    }

    @Override
    public String parseNetworkResponse(Response response, int i) throws Exception {
        String tempBody = response.body().string();
        return tempBody;
    }

}
