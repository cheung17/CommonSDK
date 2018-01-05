package com.commonsdk.http.util;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.commonsdk.file.FileCompare;
import com.commonsdk.http.okhttputils.OkHttpCallback;
import com.commonsdk.log.QLog;
import com.commonsdk.network.NetUtils;
import com.commonsdk.view.QimonDialog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeunp.commansdk.R;
import com.commonsdk.zhy.http.okhttp.OkHttpUtils;
import com.commonsdk.zhy.http.okhttp.callback.FileCallBack;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 网络请求接口
 *
 * @author zeffect
 *         1、使用方法参考：https://github.com/hongyangAndroid/okhttputils
 *         2、Callback一定要使用QimonCallback,方法以后做统一处理
 *         3、无需开启异步线程，已经封装在内部了。
 *         4、callBack不为null时，Handler不会接收到返回信息
 */
public class OkHttpUtil {
    /**
     * 判断是否允许使用网络《禁用所有网络》。
     */
    public static final boolean CloseNetWork = false;
    /**
     * 学生登陆的url地址用于判断是否要保存cookies
     */
    public static final String LOGIN_URL = "/platform_intf/qimon/v1/userLoginAct.action";
    /**
     * 失败返回
     **/
    public static final int Faile = -1;
    /**
     * 成功返回
     **/
    public static final int Success = 1;
    /**
     * 取值
     **/
    private static final String COOKIE_KEY = "cookie";
    public static final String MESSAGE_SUCCESS = "0x8000";
    /**
     * Cookie失效的Code
     **/
    public static final int COOKIE_LOSE_KEY = 10086;
    /**
     * 后台约定返回的键值
     */
    public static final String CODE_KEY = "code";
    /**
     * 后台约定返回的键值
     */
    public static final String MSG_KEY = "msg";
    /**
     * 后台约定返回的键值
     */
    public static final String DATA_KEY = "data";
    /**
     * 返回进度
     **/
    public static final int PROGRESS = 20160407;
    /***
     * 学生端Cookie失效
     */
    public static final String COOKIE_LOSE_EFFICACY = "org_qimon_launcher6_cookie_lose_efficacy";
    /***
     * 学生端Cookie失效
     */
    public static final String TEACHER_COOKIE_LOSE_EFFICACY = "com.qimon.teacher_cookie_lose_efficacy";


    // //////////////////****20160407,准备重做所有网络接口************************************************************************************

    /**
     * post请求带Json字符串
     *
     * @param context       x
     * @param userType      用户类型
     * @param returnHandler 返回数据的handle
     * @param jsonString    参数
     * @param httpUrl       地址
     * @return
     */
    public static void postJson(final Context context, int userType, final Handler returnHandler, String jsonString, String httpUrl) {
        postJson(context, userType, returnHandler, jsonString, httpUrl, true, "加载中……", true, null);
    }

    /**
     * post请求带Json字符串
     *
     * @param context       x
     * @param userType      用户类型
     * @param returnHandler 返回数据的handle
     * @param jsonString    参数
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     * @return
     */
    public static void postJson(final Context context, int userType, final Handler returnHandler, String jsonString, String httpUrl, boolean isShowDialog) {
        postJson(context, userType, returnHandler, jsonString, httpUrl, isShowDialog, "加载中……", true, null);
    }

    /**
     * post发送Json格式数据
     *
     * @param context       x
     * @param userType      用户类型
     * @param returnHandler 返回数据的handle
     * @param param         参数对象
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     */
    public static void postJson(final Context context, int userType, final Handler returnHandler, Object param, String httpUrl, boolean isShowDialog) {
        if (param != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(param);
                postJson(context, userType, returnHandler, json, httpUrl, isShowDialog, "加载中……", true, null);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * post发送Json格式数据
     *
     * @param context       x
     * @param userType      用户类型
     * @param returnHandler 返回数据的handle，callback不为null时，Handler不会接收到消息
     * @param jsonString    参数对象Json字符串
     * @param httpUrl       地址
     * @param callback      自定义的回调
     */
    public static void postJson(final Context context, int userType, final Handler returnHandler, String jsonString, String httpUrl, OkHttpCallback callback) {
        postJson(context, userType, returnHandler, jsonString, httpUrl, false, "加载中……", true, callback);
    }

    /**
     * post发送Json格式数据
     *
     * @param context  x
     * @param userType 用户类型
     * @param param    参数对象
     * @param httpUrl  地址
     * @param callback 自定义的回调
     */
    public static void postJson(final Context context, int userType, Object param, String httpUrl, OkHttpCallback callback) {
        if (param != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = null;
                if (param instanceof String) {
                    json = (String) param;
                } else {
                    json = mapper.writeValueAsString(param);
                }
                postJson(context, userType, null, json, httpUrl, false, "加载中……", true, callback);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * post发送Json格式数据
     *
     * @param context       x
     * @param userType      用户类型
     * @param returnHandler 返回数据的handle
     * @param param         参数对象
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     * @param callback      自定义的回调
     */
    public static void postJson(final Context context, int userType, final Handler returnHandler, Object param, String httpUrl, boolean isShowDialog, OkHttpCallback callback) {
        if (param != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(param);
                postJson(context, userType, returnHandler, json, httpUrl, isShowDialog, "加载中……", true, callback);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * post请求带Json字符串
     *
     * @param context       上下文
     * @param userType      用户角色
     * @param returnHandler 返回
     * @param jsonString    JSON数据
     * @param httpUrl       网络请求路径，必须是全路径
     * @param isCancelable  能否取消这个框
     * @param dialogString  弹窗显示文字
     * @param isDialogShow  是否显示弹窗
     * @return
     */
    public static void postJson(Context context, int userType, final Handler returnHandler, String jsonString, String httpUrl, final boolean isDialogShow, final String dialogString,
                                final boolean isCancelable, OkHttpCallback callback) {
        if (!CloseNetWork) {
            final WeakReference<Context> tempContextWeakReference = new WeakReference<Context>(context);
            if (!NetUtils.isConnected(context)) {
//            Toast.makeText(context, R.string.qimonsdk_no_network, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(httpUrl)) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(jsonString)) {
                jsonString = "{\"author\":\"zeffect\"}";
            }
            if (callback != null) {
                OkHttpUtils.postString()
                        .tag(context)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .content(jsonString)
                        .url(httpUrl)
                        .build()
                        .execute(callback);
            } else {
                OkHttpUtils.postString()
                        .tag(context)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .content(jsonString)
                        .url(httpUrl)
                        .build()
                        .execute(new OkHttpCallback(context) {
                            private QimonDialog dialog = new QimonDialog(tempContextWeakReference.get());

                            @Override
                            public void onBefore(Request request, int id) {
                                if (isDialogShow && !TextUtils.isEmpty(dialogString)) {
                                    dialog.showLoadingDialog(dialogString, isCancelable);
                                }
                            }

                            @Override
                            public void onAfter(int id) {
                                if (isDialogShow) {
                                    dialog.closeDialog();
                                }
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if (returnHandler != null && tempContextWeakReference.get() != null) {
                                    Message msg = new Message();
                                    msg.what = Faile;
                                    msg.obj = "service error";
                                    returnHandler.sendMessage(msg);
                                }
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if (checkCookieLose(response)) {
                                    if (returnHandler != null && tempContextWeakReference.get() != null) {
                                        Message msg = new Message();
                                        msg.what = Faile;
                                        msg.obj = "cookie lose";
                                        returnHandler.sendMessage(msg);
                                    }
                                    return;
                                }
                                try {
                                    JSONObject retuJsonObject = new JSONObject(response);
                                    boolean isNullCode = retuJsonObject.isNull(CODE_KEY);
                                    if (!isNullCode) {
                                        int code = retuJsonObject.getInt(CODE_KEY);
                                        if (code == COOKIE_LOSE_KEY) {
                                            // Cookie失效操作，发个广播出去最简单
                                            sendCookieLoseBroadCast(tempContextWeakReference.get());
                                        } else {
                                            if (returnHandler != null && tempContextWeakReference.get() != null) {
                                                Message msg = new Message();
                                                msg.what = Success;
                                                msg.obj = response;
                                                returnHandler.sendMessage(msg);
                                            }
                                        }
                                    } else {
                                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                                            Message msg = new Message();
                                            msg.what = Success;
                                            msg.obj = response;
                                            returnHandler.sendMessage(msg);
                                        }
                                    }
                                } catch (Exception e) {
                                    if (returnHandler != null && tempContextWeakReference.get() != null) {
                                        Message msg = new Message();
                                        msg.what = Success;
                                        msg.obj = response;
                                        returnHandler.sendMessage(msg);
                                    }
                                }
                            }
                        });
            }
        } else {
            Toast.makeText(context, "该应用为离线体验版本。如需正式版本，请联系开发商。", Toast.LENGTH_SHORT).show();
            Message msg = new Message();
            msg.what = Faile;
            msg.obj = "service error";
            if (returnHandler != null) {
                returnHandler.sendMessage(msg);
            }
        }
    }

    /**
     * post请求带参数parameter
     * <p>
     * 服务器收到请求可以用getParameter得到参数
     *
     * @param context       上下文
     * @param returnHandler 返回信息的Handler
     * @param parma         参数
     * @param httpUrl       网络请求路径，必须是全路径
     */
    public static void postParam(final Context context, final Handler returnHandler, Map<String, String> parma, String httpUrl) {
        postParam(context, returnHandler, parma, httpUrl, true, "加载中……", true, null);
    }

    /**
     * * post请求带参数parameter
     * <p>
     * 服务器收到请求可以用getParameter得到参数
     *
     * @param context       x
     * @param returnHandler 返回数据的handle
     * @param param         参数对象
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     * @return
     */
    public static void postParam(final Context context, final Handler returnHandler, Map<String, String> param, String httpUrl, boolean isShowDialog) {
        postParam(context, returnHandler, param, httpUrl, isShowDialog, "加载中……", true, null);
    }

    /**
     * post发送Param格式数据
     *
     * @param context       x
     * @param returnHandler 返回数据的handle
     * @param param         参数对象
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     * @param dialogString  Dialog内容
     */
    public static void postParam(final Context context, final Handler returnHandler, Map<String, String> param, String httpUrl, final boolean isShowDialog, final String dialogString) {
        postParam(context, returnHandler, param, httpUrl, isShowDialog, dialogString, true, null);
    }

    /**
     * post发送Param格式数据
     *
     * @param context   x
     * @param parma     参数对象
     * @param httpUrl   地址
     * @param pCallBack 自定义的OKHttp回调
     */
    public static void postParam(final Context context, Map<String, String> parma, String httpUrl, OkHttpCallback pCallBack) {
        postParam(context, null, parma, httpUrl, false, "加载中……", true, pCallBack);
    }

    /**
     * post发送Param格式数据
     *
     * @param context       x
     * @param returnHandler 返回数据的handle
     * @param param         参数对象
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     * @param dialogString  Dialog内容
     * @param isCancelable  Dialog是否可以被手动关闭
     */
    public static void postParam(final Context context, final Handler returnHandler, Map<String, String> param, String httpUrl, final boolean isShowDialog, final String
            dialogString, final boolean isCancelable) {
        postParam(context, returnHandler, param, httpUrl, isShowDialog, dialogString, isCancelable, null);
    }

    /**
     * post请求带参数parameter
     * <p>
     * 服务器收到请求可以用getParameter得到参数
     *
     * @param context       上下文
     * @param returnHandler 返回信息的Handler
     * @param parma         参数
     * @param httpUrl       网络请求路径，必须是全路径
     * @param isDialogShow  是否显示Dialog
     * @param dialogString  Dialog内容
     * @param isCancelable  Dialog是否可以被手动关闭
     * @param pCallBack     指定回调
     */
    public static void postParam(Context context, final Handler returnHandler, Map<String, String> parma, String httpUrl, final boolean isDialogShow, final String
            dialogString, final boolean isCancelable, OkHttpCallback pCallBack) {
        if (!CloseNetWork) {
            if (context == null) {
                if (returnHandler != null) {
                    Message tempMessage = returnHandler.obtainMessage();
                    tempMessage.what = OkHttpUtil.Faile;
                    tempMessage.obj = "context is null";
                    returnHandler.sendMessage(tempMessage);
                }
                return;
            }
            final WeakReference<Context> tempContextWeakReference = new WeakReference<Context>(context);
            if (!NetUtils.isConnected(context)) {
                if (returnHandler != null) {
                    Message tempMessage = returnHandler.obtainMessage();
                    tempMessage.what = OkHttpUtil.Faile;
                    tempMessage.obj = "bad net";
                    returnHandler.sendMessage(tempMessage);
                }
                Toast.makeText(context, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(httpUrl)) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            if (parma == null) {
                parma = new HashMap<>();
                parma.put("author", "zeffect");
            }
            if (parma.size() < 1) {
                parma.put("author", "zeffect");
            }
            if (pCallBack != null) {
                OkHttpUtils.post().tag(context).params(parma).url(httpUrl).build().execute(pCallBack);
                //request = new OkHttpRequest.Builder().addHeader(COOKIE_KEY, UserData.getCookiesValue(context, userType, userid)).params(parma).url(httpUrl).post(pCallBack);
            } else {
                OkHttpUtils.post().tag(context).params(parma).url(httpUrl).build().execute(new OkHttpCallback(context) {
                    private QimonDialog dialog = new QimonDialog(tempContextWeakReference.get());

                    @Override
                    public void onBefore(Request request, int id) {
                        if (isDialogShow && !TextUtils.isEmpty(dialogString)) {
                            dialog.showLoadingDialog(dialogString, isCancelable);
                        }
                        super.onBefore(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        if (isDialogShow) {
                            dialog.closeDialog();
                        }
                        super.onAfter(id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //Toast.makeText(context, R.string.qimonsdk_net_work_error, Toast.LENGTH_SHORT).show();
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = Faile;
                            try {
                                msg.obj = e.getMessage();
                            } catch (Exception e1) {
                                msg.obj = "service error";
                            }
                            returnHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (checkCookieLose(response)) {
                            if (returnHandler != null && tempContextWeakReference.get() != null) {
                                Message msg = new Message();
                                msg.what = Faile;
                                msg.obj = "cookie lose";
                                returnHandler.sendMessage(msg);
                            }
                            return;
                        }
                        try {
                            JSONObject retuJsonObject = new JSONObject(response);
                            if (returnHandler != null && tempContextWeakReference.get() != null) {
                                Message msg = new Message();
                                msg.what = Success;
                                msg.obj = response;
                                returnHandler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            //Toast.makeText(context, R.string.qimonsdk_net_work_error, Toast.LENGTH_SHORT).show();
                            if (returnHandler != null && tempContextWeakReference.get() != null) {
                                Message msg = new Message();
                                msg.what = Success;
                                msg.obj = response;
                                returnHandler.sendMessage(msg);
                            }
                        }
                    }
                });
            }
        } else {
            Toast.makeText(context, "该应用为离线体验版本。如需正式版本，请联系开发商。", Toast.LENGTH_SHORT).show();
            Message msg = new Message();
            msg.what = Faile;
            msg.obj = "service error";
            if (returnHandler != null) {
                returnHandler.sendMessage(msg);
            }
        }
    }

    /**
     * * get请求带参数parameter
     * <p>
     * 服务器收到请求可以用getParameter得到参数
     *
     * @param context       x
     * @param returnHandler 返回数据的handle
     * @param param         参数对象
     * @param httpUrl       地址
     * @param isShowDialog  是否显示等待框
     */
    public static void getParam(final Context context, final Handler returnHandler, Map<String, String> param, String httpUrl, boolean isShowDialog) {
        getParam(context, returnHandler, param, httpUrl, isShowDialog, "加载中……", false, null);
    }

    /**
     * * get请求带参数parameter
     * <p>
     * 服务器收到请求可以用getParameter得到参数
     *
     * @param context   x
     * @param parma     参数对象
     * @param httpUrl   地址
     * @param pCallBack 用户自定义的OkHttp回调
     */
    public static void getParam(final Context context, Map<String, String> parma, String httpUrl, OkHttpCallback pCallBack) {
        getParam(context, null, parma, httpUrl, false, "加载中……", false, pCallBack);
    }

    /**
     * get请求带参数parameter
     * <p>
     * 服务器收到请求可以用getParameter得到参数
     *
     * @param context       上下文
     * @param returnHandler 返回信息的Handler
     * @param parma         参数
     * @param httpUrl       网络请求路径，必须是全路径
     * @param isDialogShow  是否显示Dialog
     * @param dialogString  Dialog内容
     * @param isCancelable  Dialog是否可以被手动关闭
     * @param pCallBack     指定回调
     */
    public static void getParam(Context context, final Handler returnHandler, Map<String, String> parma, String httpUrl, final boolean isDialogShow, final String
            dialogString, final boolean isCancelable, OkHttpCallback pCallBack) {
        if (!CloseNetWork) {
            final WeakReference<Context> tempContextWeakReference = new WeakReference<Context>(context);
            if (!NetUtils.isConnected(context)) {
//            Toast.makeText(context, R.string.qimonsdk_no_network, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(httpUrl)) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            if (parma == null) {
                parma = new HashMap<>();
                parma.put("author", "zeffect");
            }
            if (parma.size() < 1) {
                parma.put("author", "zeffect");
            }

            if (pCallBack != null) {
                OkHttpUtils.get().tag(context).params(parma).url(httpUrl).build().execute(pCallBack);
            } else {
                OkHttpUtils.get().tag(context).params(parma).url(httpUrl).build().execute(new OkHttpCallback(context) {
                    private QimonDialog dialog = new QimonDialog(tempContextWeakReference.get());

                    @Override
                    public void onBefore(Request request, int id) {
                        if (isDialogShow && !TextUtils.isEmpty(dialogString)) {
                            dialog.showLoadingDialog(dialogString, isCancelable);
                        }
                        super.onBefore(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        if (isDialogShow) {
                            dialog.closeDialog();
                        }
                        super.onAfter(id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //Toast.makeText(context, R.string.qimonsdk_net_work_error, Toast.LENGTH_SHORT).show();
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = Faile;
                            try {
                                msg.obj = e.getMessage();
                            } catch (Exception e1) {
                                msg.obj = "service error";
                            }
                            returnHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (checkCookieLose(response)) {
                            if (returnHandler != null && tempContextWeakReference.get() != null) {
                                Message msg = new Message();
                                msg.what = Faile;
                                msg.obj = "cookie lose";
                                returnHandler.sendMessage(msg);
                            }
                            return;
                        }
                        try {
                            JSONObject retuJsonObject = new JSONObject(response);
                            boolean isNullCode = retuJsonObject.isNull(CODE_KEY);
                            if (!isNullCode) {
                                int code = retuJsonObject.getInt(CODE_KEY);
                                if (code == COOKIE_LOSE_KEY) {
                                    // Cookie失效操作，发个广播出去最简单
                                    sendCookieLoseBroadCast(tempContextWeakReference.get());
                                } else {
                                    if (returnHandler != null && tempContextWeakReference.get() != null) {
                                        Message msg = new Message();
                                        msg.what = Success;
                                        msg.obj = response;
                                        returnHandler.sendMessage(msg);
                                    }
                                }
                            } else {
                                if (returnHandler != null && tempContextWeakReference.get() != null) {
                                    Message msg = new Message();
                                    msg.what = Success;
                                    msg.obj = response;
                                    returnHandler.sendMessage(msg);
                                }
                            }
                        } catch (Exception e) {
                            //Toast.makeText(context, R.string.qimonsdk_net_work_error, Toast.LENGTH_SHORT).show();
                            if (returnHandler != null && tempContextWeakReference.get() != null) {
                                Message msg = new Message();
                                msg.what = Success;
                                msg.obj = response;
                                returnHandler.sendMessage(msg);
                            }
                        }
                    }
                });
            }
        } else {
            Message msg = new Message();
            msg.what = Faile;
            msg.obj = "service error";
            if (returnHandler != null) {
                returnHandler.sendMessage(msg);
            }
        }
    }

    /**
     * 下载附件的网络请求
     *
     * @param url      附件url地址
     * @param FileDir  保存的文件路径
     * @param fileName 保存的文件名称
     * @param handler  结果返回
     * @throws Exception
     */
    public static void downloadExtraFile(String url, String FileDir, String fileName, final Handler handler) throws
            Exception {
        if (!CloseNetWork) {
            if (TextUtils.isEmpty(FileDir)) {
                throw new Exception("FileDir is null !!!");
            }
            if (TextUtils.isEmpty(fileName)) {
                throw new Exception("fileName is null !!!");
            }
            if (TextUtils.isEmpty(url)) {
                throw new Exception("url is null !!!");
            }
            System.out.println("download path :" + url);
            OkHttpUtils.get().tag(url).url(url).build().execute(new FileCallBack(FileDir, fileName) {
                @Override
                public void onError(Call call, Exception e, int i) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = Faile;
                    msg.obj = "service error";
                    if (handler != null) {
                        handler.sendMessage(msg);
                    }
                }

                @Override
                public void onResponse(File file, int i) {
                    Message msg = new Message();
                    msg.what = Success;
                    msg.obj = file.getAbsolutePath();
                    if (handler != null) {
                        handler.sendMessage(msg);
                    }
                }
            });
        } else {
            Message msg = new Message();
            msg.what = Faile;
            msg.obj = "service error";
            if (handler != null) {
                handler.sendMessage(msg);
            }
        }
    }

    /**
     * 下载一个文件
     *
     * @param context       上下文
     * @param httpUrl       下载路径,全路径
     * @param fileDir       保存在本地的哪个文件夹
     * @param fileName      保存文件的名字
     * @param returnHandler 通过handle将数据返回到界面。
     */
    public static void downloadFile(final Context context, final Handler returnHandler, String httpUrl, String fileDir, String fileName) {
        downloadFile(context, returnHandler, httpUrl, fileDir, fileName, true, null);
    }

    /**
     * 下载一个文件
     *
     * @param context       上下文
     * @param httpUrl       下载路径,全路径
     * @param fileDir       保存在本地的哪个文件夹
     * @param fileName      保存文件的名字
     * @param returnHandler 通过handle将数据返回到界面。
     */
    public static void downloadFileNoDialog(final Context context, final Handler returnHandler, String httpUrl, String fileDir, String fileName) {
        downloadFile(context, returnHandler, httpUrl, fileDir, fileName, false, null);
    }

    /**
     * 下载一个文件
     *
     * @param context       上下文
     * @param httpUrl       下载路径,全路径
     * @param fileDir       保存在本地的哪个文件夹
     * @param fileName      保存文件的名字
     * @param isShowDialog  是否显示这个框
     * @param returnHandler 通过handle将数据返回到界面。
     */
    public static void downloadFile(final Context context, final Handler returnHandler, String httpUrl, String fileDir, String fileName, final boolean isShowDialog) {
        downloadFile(context, returnHandler, httpUrl, fileDir, fileName, isShowDialog, null);
    }

    /**
     * 下载一个文件
     *
     * @param context  上下文
     * @param httpUrl  下载路径,全路径
     * @param callBack 自定义的回调，该值不为空，则handler不触发。
     */
    public static void downloadFile(final Context context, String httpUrl, FileCallBack callBack) {
        downloadFile(context, null, httpUrl, "", "", false, callBack);
    }

    /**
     * 下载一个文件
     *
     * @param context       上下文
     * @param httpUrl       下载路径,全路径
     * @param fileDir       保存在本地的哪个文件夹
     * @param fileName      保存文件的名字
     * @param isShowDialog  是否显示这个框
     * @param returnHandler 通过handle将数据返回到界面。
     * @param callBack      自定义的回调，该值不为空，则handler不触发。
     */
    public static void downloadFile(Context context, final Handler returnHandler, String httpUrl, String fileDir, String fileName, final boolean isShowDialog, FileCallBack callBack) {
        if (!CloseNetWork) {
            final WeakReference<Context> tempContextWeakReference = new WeakReference<Context>(context);
            if (!NetUtils.isConnected(context)) {
//            Toast.makeText(context, R.string.qimonsdk_no_network, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(httpUrl)) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(fileDir)) {// 文件路径不存在
                fileDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            }
            File tempFileDir = new File(fileDir);
            if (!tempFileDir.exists()) {
                tempFileDir.mkdirs();
            }
            if (TextUtils.isEmpty(fileName)) {// 文件名为空
                fileName = System.currentTimeMillis() + "";
            }

            if (callBack != null) {
                OkHttpUtils.get().tag(context).url(httpUrl).build().execute(callBack);
            } else {
                OkHttpUtils.get().tag(context).url(httpUrl).build().execute(new FileCallBack(tempFileDir.getAbsolutePath(), fileName) {
                    private QimonDialog dialog = new QimonDialog(tempContextWeakReference.get());

                    @Override
                    public void onBefore(Request request, int id) {
                        if (isShowDialog) {
                            dialog.showLoadingDialog(tempContextWeakReference.get().getResources().getString(R.string.sdk_loading));
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        dialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = Faile;
                            if (null != e && e.getMessage() != null) {
                                msg.obj = e.getMessage();
                            } else {
                                msg.obj = "service error";
                            }
                            returnHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onResponse(File file, int i) {

                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = Success;
                            msg.obj = file.getAbsolutePath();
                            returnHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        if (isShowDialog) {
                            dialog.changeContent("下载进度:" + (int) progress);
                        }
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = PROGRESS;
                            msg.obj = progress;
                            returnHandler.sendMessage(msg);
                        }
                    }
                });
            }
        } else {
            Toast.makeText(context, "该应用为离线体验版本。如需正式版本，请联系开发商。", Toast.LENGTH_SHORT).show();
            Message msg = new Message();
            msg.what = Faile;
            msg.obj = "service error";
            if (returnHandler != null) {
                returnHandler.sendMessage(msg);
            }
        }
    }

    /**
     * 上传一个文件
     * <p>
     * 服务器指定接收文件的Key：file
     *
     * @param context       上下文
     * @param returnHandler 返回
     * @param httpUrl       服务接收上传文件的地址
     * @param uploadFile    等待上传文件的本地地址
     */
    public static void uploadFile(final Context context, final Handler returnHandler, String httpUrl, File uploadFile) {
        uploadFile(context, returnHandler, httpUrl, uploadFile, null);
    }

    /**
     * 上传一个文件
     * <p>
     * 服务器指定接收文件的Key：file
     *
     * @param context       上下文
     * @param returnHandler 返回
     * @param httpUrl       服务接收上传文件的地址
     * @param uploadFile    等待上传文件的本地地址
     * @param callback      自定义回调函数。该参数不为空时，returnHandler不触发。
     * @return
     */
    public static void uploadFile(Context context, final Handler returnHandler, String httpUrl, File uploadFile, OkHttpCallback callback) {
        if (!CloseNetWork) {
            final WeakReference<Context> tempContextWeakReference = new WeakReference<Context>(context);
            if (!NetUtils.isConnected(context)) {
//            Toast.makeText(context, R.string.qimonsdk_no_network, Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(httpUrl)) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            if (uploadFile == null) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!uploadFile.exists()) {
                Toast.makeText(context, R.string.qimonsdk_http_request_error, Toast.LENGTH_SHORT).show();
                return;
            }
            String md5 = FileCompare.getFileMD5(uploadFile);

            if (callback != null) {
                OkHttpUtils.post()//
                        .tag(context)
                        .addFile("file", uploadFile.getName(), uploadFile)//
                        .url(httpUrl)
                        .addParams("md5", md5)
                        .build().writeTimeOut(60 * 1000).execute(callback);
            } else {
                OkHttpUtils.post()//
                        .tag(context)
                        .addFile("file", uploadFile.getName(), uploadFile)//
                        .url(httpUrl)
                        .addParams("md5", md5)
                        .build().writeTimeOut(60 * 1000).readTimeOut(15 * 1000).execute(new OkHttpCallback(context) {
                    private QimonDialog dialog = new QimonDialog(tempContextWeakReference.get());

                    @Override
                    public void onBefore(Request request, int id) {
                        dialog.showLoadingDialog();
                    }

                    @Override
                    public void onAfter(int id) {
                        dialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = Faile;
                            msg.obj = "service error";
                            returnHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onResponse(String s, int i) {
//                        if (checkCookieLose(s)){
//                            return;
//                        }
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = Success;
                            msg.obj = s;
                            returnHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        dialog.changeContent("上传进度:" + (int) progress);
                        if (returnHandler != null && tempContextWeakReference.get() != null) {
                            Message msg = new Message();
                            msg.what = PROGRESS;
                            msg.obj = progress;
                            returnHandler.sendMessage(msg);
                        }
                    }
                });
            }
        } else {
            Toast.makeText(context, "该应用为离线体验版本。如需正式版本，请联系开发商。", Toast.LENGTH_SHORT).show();
            Message msg = new Message();
            msg.what = Faile;
            msg.obj = "service error";
            if (returnHandler != null) {
                returnHandler.sendMessage(msg);
            }
        }
    }

    /**
     * 上传文件。
     *
     * @param cookies    cookies
     * @param httpUrl    上传路径
     * @param uploadFile 需要上传的文件
     * @param callback   回调
     * @throws Exception 参数错误抛出。
     */
    public static void uploadFile(String cookies, String httpUrl, File uploadFile, OkHttpCallback callback) throws Exception {
        if (!CloseNetWork) {
            if (TextUtils.isEmpty(httpUrl)) {
                throw new Exception("httpUrl is null .");
            }
            if (uploadFile == null) {
                throw new Exception("uploadFile is null .");
            }
            if (!uploadFile.exists()) {
                throw new Exception("uploadFile is not exist .");
            }
            if (callback == null) {
                throw new Exception("callback is null .");
            }
            String md5 = FileCompare.getFileMD5(uploadFile);
            OkHttpUtils.post()//
                    .tag(httpUrl)
                    .addFile("file", uploadFile.getName(), uploadFile)//
                    .url(httpUrl)
                    .addHeader(COOKIE_KEY, cookies)
                    .addParams("md5", md5)
                    .build().writeTimeOut(60 * 1000).execute(callback);

        } else {
            QLog.i("该应用为离线体验版本。如需正式版本，请联系开发商。");
        }
    }

    /**
     * 发送Cookie失效的广播
     * <p>
     * 发送应用内广播应该比较好
     * <p>
     * 发送的广播是包名加上一个常量
     *
     * @param context 上下文
     */
    public static void sendCookieLoseBroadCast(Context context) {
        sendCookieLoseBroadCast(context, "");
    }

    /**
     * 发送Cookie失效的广播
     * <p>
     * 发送应用内广播应该比较好
     * <p>
     * 发送的广播是包名加上一个常量
     *
     * @param pContext 上下文
     * @param message  显示提示
     */
    public static void sendCookieLoseBroadCast(Context pContext, String message) {
        if (pContext != null) {
            if (TextUtils.isEmpty(message)) {
                message = "登录超时，请重新登录";
            }
            Intent intent = new Intent(COOKIE_LOSE_EFFICACY);
            pContext.sendBroadcast(intent);
//            Toast.makeText(pContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发送Cookie失效的广播
     * <p>
     * 发送应用内广播应该比较好
     * <p>
     * 发送的广播是包名加上一个常量
     *
     * @param pContext 上下文
     */
    public static void sendTeacherCookieLoseBroadCast(Context pContext) {
        if (pContext != null) {
            Intent intent = new Intent(TEACHER_COOKIE_LOSE_EFFICACY);
            pContext.sendBroadcast(intent);
//            Toast.makeText(pContext, message, Toast.LENGTH_SHORT).show();
        }
    }


    //************************再次重写部分接口********************************************************************************************************

    /**
     * 网络请求接口，去掉无用数据，所有数据自己传入
     *
     * @param pHeaders 头部数据
     * @param pParams  参数
     * @param url      地址
     * @param pHandler 回调
     */
    public static void postParams(Map<String, String> pHeaders, Map<String, String> pParams, String url, final Handler pHandler) {
        if (!CloseNetWork) {
            if (TextUtils.isEmpty(url)) {
                throw new NullPointerException("url null");
            }
            if (pHeaders == null) {
                pHeaders = new HashMap<>();
            }
            if (pParams == null) {
                pParams = new HashMap<>();
            }
            OkHttpUtils.post().tag(url).headers(pHeaders).params(pParams).url(url).build().execute(new OkHttpCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {
                    if (pHandler != null) {
                        Message msg = new Message();
                        msg.what = Faile;
                        msg.obj = "something wrong";
                        pHandler.sendMessage(msg);
                    }
                }

                @Override
                public void onResponse(String s, int i) {
                    if (checkCookieLose(s)) {
                        if (pHandler != null) {
                            Message msg = new Message();
                            msg.what = Faile;
                            msg.obj = "cookie lose";
                            pHandler.sendMessage(msg);
                        }
                        return;
                    }
                    if (pHandler != null) {
                        Message msg = new Message();
                        msg.what = Success;
                        msg.obj = s;
                        pHandler.sendMessage(msg);
                    }
                }
            });
        } else {
            if (pHandler != null) {
                Message msg = new Message();
                msg.what = Faile;
                msg.obj = "something wrong";
                pHandler.sendMessage(msg);
            }
        }
    }

    /**
     * 网络请求接口，去掉无用数据，所有数据自己传入
     *
     * @param pHeaders  头部数据
     * @param pParams   参数
     * @param url       地址
     * @param pCallback 回调
     */
    public static void postParams(Map<String, String> pHeaders, Map<String, String> pParams, String url, OkHttpCallback pCallback) {
        if (!CloseNetWork) {
            if (TextUtils.isEmpty(url)) {
                throw new NullPointerException("url null");
            }
            if (pHeaders == null) {
                pHeaders = new HashMap<>();
            }
            if (pParams == null) {
                pParams = new HashMap<>();
            }
            OkHttpUtils.post().tag(url).headers(pHeaders).params(pParams).url(url).build().execute(pCallback);
        } else {
            if (pCallback != null) {
                Exception e = new Exception("该应用为离线体验版本。如需正式版本，请联系开发商。");
                pCallback.onError(null, e, 1);
            }
        }
    }

    /***
     * cookie丢失，直接返回，不往下走
     *
     * @param response 返回数据
     */
    private static boolean checkCookieLose(String response) {
        if (response == null) {
            return true;
        }
        return false;
    }

}
