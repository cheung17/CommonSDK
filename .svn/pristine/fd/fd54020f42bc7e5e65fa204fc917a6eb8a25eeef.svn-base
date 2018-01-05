package com.commonsdk.http.okhttputils;

import android.content.Context;
import android.text.TextUtils;

import com.commonsdk.http.util.OkHttpUtil;
import com.commonsdk.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import okhttp3.Response;

/**
 * 网络接口回调
 * Created by zeffect on 2016/8/15.
 */
public abstract class OkHttpCallback extends Callback<String> {
    /***
     * 登录超时的字符串
     */
    public static final String COOKIE_LOSE_STRING = "qimoncallback_cookie_lose_string";
    private WeakReference<Context> mContextWeakReference;

    /**
     * 尽量不使用，推荐使用
     *
     * @see OkHttpCallback (Context)
     * 方便检测登录超时
     */
    @Deprecated
    public OkHttpCallback() {
    }

    public OkHttpCallback(Context pContext) {
        mContextWeakReference = new WeakReference<Context>(pContext);
    }

    @Override
    public String parseNetworkResponse(Response response, int i) throws Exception {
        String tempBody = response.body().string();
        boolean isCookieLost = false;
        try {
            if (mContextWeakReference != null) {
                if (mContextWeakReference.get() != null) {
                    String json = tempBody.toString();
                    if (!TextUtils.isEmpty(json)) {
                        JSONObject dataJson = new JSONObject(json);
                        if (!dataJson.isNull(OkHttpUtil.CODE_KEY)) {
                            int code = dataJson.getInt(OkHttpUtil.CODE_KEY);
                            if (code == OkHttpUtil.COOKIE_LOSE_KEY) {
                                isCookieLost = true;
                                String showMessage = "";
                                if (!dataJson.isNull(OkHttpUtil.MSG_KEY)) {
                                    showMessage = dataJson.getString(OkHttpUtil.MSG_KEY);
                                }
                                if (mContextWeakReference.get().getPackageName().equals("com.qimon.teacher")) {
                                    //教师端Cookie失效
                                    OkHttpUtil.sendTeacherCookieLoseBroadCast(mContextWeakReference.get());
                                } else {
                                    //其它都算学生端失效
                                    OkHttpUtil.sendCookieLoseBroadCast(mContextWeakReference.get(), showMessage);
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException pE) {
            isCookieLost = false;
        } finally {
            if (isCookieLost) {
                return COOKIE_LOSE_STRING;
            } else {
                return tempBody.toString();
            }
        }
    }

}
