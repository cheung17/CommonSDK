package com.commonsdk.http.util;//package com.qimon.http.util;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//
//import com.commonsdk.application.CrashHandler;
//import com.qimon.userdata.DataConstant;
//import com.qimon.userdata.UserData;
//
//import org.apache.http.HttpStatus;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * HttpURLConnection形式的网络请求工具类
// *
// * @author ztx
// */
//@Deprecated
//public class UrlQuest {
//    /** 学生登陆的url地址用于判断是否要保存cookies */
////	public static final String LOGIN_URL = "/platform_intf/qimon/v1/userLoginAct.action";
//    /**
//     * 编码模式
//     */
//    private String ENCODING = "UTF-8";
//    /**
//     * 数据读取超时时间
//     */
//    private int DEFAULT_READ_TIME_OUT = 60 * 1000;
//    /**
//     * 连接超时时间
//     */
//    private int CONNECT_TIME_OUT = 60 * 1000;
//    /** 标记是否为下载操作 */
//    // private boolean isDownloadFile = false;
//    /**
//     * 当前sdk调用者类型
//     */
//    private int currentUserType = -1;
//    /**
//     * 用户ID
//     */
//    private String userID = "";
//    /**
//     * 网络请求，回调
//     */
//    private Handler handler;
//
//    /**
//     * @param currentUserType 用户类型
//     *                        <li>DataConstant.student</li>
//     *                        <li>DataConstant.parent</li>
//     *                        <li>DataConstant.teacher</li>
//     */
//    public UrlQuest(int currentUserType, String userID) {
//        this.currentUserType = currentUserType;
//        this.userID = userID;
//    }
//
//    /**
//     * 去掉method，textcontent等固定参数，仅仅发送内容体的网络请求。
//     *
//     * @param currentUserType 用户类型
//     *                        <li>DataConstant.student</li>
//     *                        <li>DataConstant.parent</li>
//     *                        <li>DataConstant.teacher</li>
//     */
//    public UrlQuest(Handler handler, int currentUserType, String userID) {
//        this.handler = handler;
//        this.currentUserType = currentUserType;
//        this.userID = userID;
//    }
//
//    /**
//     * 设置编码
//     *
//     * @param encoding 编码
//     */
//    public void setEncoding(String encoding) {
//        ENCODING = encoding;
//    }
//
//    /**
//     * 设置读取数据超时时间
//     *
//     * @param default_read_time_out 读取数据超时时间
//     */
//    public void setDEFAULT_READ_TIME_OUT(int default_read_time_out) {
//        DEFAULT_READ_TIME_OUT = default_read_time_out;
//    }
//
//    /**
//     * 设置连接超时时间
//     *
//     * @param connect_time_out 连接超时时间
//     */
//    public void setCONNECT_TIME_OUT(int connect_time_out) {
//        CONNECT_TIME_OUT = connect_time_out;
//    }
//
//    /**
//     * 上传文件
//     *
//     * @param urlStr       上传地址
//     * @param contentData  参数
//     * @param context      上下文
//     * @param method       后台约定的方法名
//     * @param sendFilePath 待上传文件路径
//     * @return 结果
//     * @throws Exception
//     */
//    public String uploadFile(Context context, String urlStr, String method, String contentData, String sendFilePath) throws Exception {
//        if (TextUtils.isEmpty(urlStr)) {
//            throw new Exception("访问路径不能为空");
//        }
//        if (TextUtils.isEmpty(sendFilePath)) {
//            throw new Exception("文件保存路径不能为空");
//        }
//        File file = new File(sendFilePath);
//        if (!file.exists()) {
//            throw new Exception("文件不存在");
//        }
//        return HttpURLConnection(context, urlStr, method, contentData, sendFilePath, null, false);
//    }
//
//    /**
//     * 发送参数
//     *
//     * @param method      后台约定的方法名
//     * @param context     上下文
//     * @param urlStr      访问地址
//     * @param contentData 参数
//     * @return 结果
//     * @throws Exception
//     */
//    public String sendParam(Context context, String urlStr, String method, String contentData) throws Exception {
//        if (TextUtils.isEmpty(urlStr)) {
//            throw new Exception("访问路径不能为空");
//        }
//        return HttpURLConnection(context, urlStr, method, contentData, null, null, false);
//    }
//
//    /**
//     * 下载文件
//     *
//     * @param method       后台约定的方法名
//     * @param context      上下文
//     * @param urlStr       下载地址
//     * @param contentData  参数
//     * @param saveFilePath 下载文件保存的地址
//     * @return 返回文件存储的路径
//     * @throws Exception
//     */
//    public String downloadFile(Context context, String urlStr, String method, String contentData, String saveFilePath) throws Exception {
//        if (TextUtils.isEmpty(urlStr)) {
//            throw new Exception("访问路径不能为空");
//        }
//        if (TextUtils.isEmpty(saveFilePath)) {
//            throw new Exception("文件保存路径不能为空");
//        }
//        return HttpURLConnection(context, urlStr, method, contentData, null, saveFilePath, true);
//    }
//
//    /**
//     * 发起网络请求的函数
//     *
//     * @param method         后台约定的方法名
//     * @param context        上下文
//     * @param urlStr         地址
//     * @param contentData    发送的参数
//     * @param sendFilePath   发送的文件路径
//     * @param saveFilePath   下载的文件保存路径
//     * @param isDownloadFile 标记是否为下载操作
//     * @return 结果
//     * @throws Exception
//     */
//    private String HttpURLConnection(Context context, String urlStr, String method, String contentData, String sendFilePath,
//                                     String saveFilePath, boolean isDownloadFile) throws Exception {
//        // 网络请求
//        HttpURLConnection httpConn = null;
//
//        String BOUNDARY = java.util.UUID.randomUUID().toString();
//        String PREFIX = "--";
//        String LINEND = "\r\n";
//        String MULTIPART_FROM_DATA = "multipart/form-data";
//
//        // 设置method值
//        urlStr += "?" + HttpSDKConstant.METHOD + "=" + method;
//        // 发送参数
//        HashMap<String, String> m_params = BuildHttpBaseData.buidData(context, currentUserType);
//        for (Map.Entry<String, String> entry : m_params.entrySet()) {
//            urlStr += "&" + entry.getKey() + "=" + entry.getValue();
//        }
//
//        URL url = new URL(urlStr);
//        // 设置属性
//        httpConn = (HttpURLConnection) url.openConnection();
//
//        httpConn.setConnectTimeout(CONNECT_TIME_OUT);
//        httpConn.setReadTimeout(DEFAULT_READ_TIME_OUT);
//        httpConn.setDoOutput(true);
//        httpConn.setDoInput(true);
//        httpConn.setUseCaches(false);
//        httpConn.setChunkedStreamingMode(0);
//        httpConn.setRequestMethod("POST");
//        httpConn.setRequestProperty("connection", "keep-alive");
//        httpConn.setRequestProperty("Charset", ENCODING);
//        httpConn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
//
//        InputStream is = null;
//        if (!TextUtils.isEmpty(sendFilePath) && !TextUtils.isEmpty(getFileName(sendFilePath)) && new File(sendFilePath).exists()) {
//            is = new FileInputStream(sendFilePath);
//            /*
//             * (1).在已知文件大小，需要上传大文件时，应该设置下面的属性，即文件长度
//			 * 当文件较小时，可以设置头信息即conn.setRequestProperty("content-length",
//			 * "文件字节长度大小"); (2).在文件大小不可知时，使用setChunkedStreamingMode();
//			 */
//            httpConn.setFixedLengthStreamingMode(is.available());
//        }
//        // 设置cookie值
//        // UserData userdata = new UserData();
//        String cookie = "";
//        cookie = UserData.getCookiesValue(context, currentUserType, userID);
//        if (!TextUtils.isEmpty(cookie)) {
//            httpConn.setRequestProperty("Cookie", cookie);
//        }
//
//        DataOutputStream outStream = new DataOutputStream(httpConn.getOutputStream());
//        StringBuilder sb = new StringBuilder();
//        // *****************************************后台filter里面去不到相关参数，废弃下面的代码，使用url后面拼接的方式解决。
//        // 设置method
//        // if (!TextUtils.isEmpty(method)) {
//        // sb.append(PREFIX);
//        // sb.append(BOUNDARY);
//        // sb.append(LINEND);
//        // sb.append("Content-Disposition: form-data; name=\"" +
//        // HttpSDKConstant.METHOD + "\"" + LINEND);
//        // sb.append("Content-Type:text/plain;charset=" + ENCODING + LINEND);
//        // sb.append("Content-Transfer-Encoding:8bit" + LINEND);
//        // sb.append(LINEND);
//        // sb.append(method);
//        // sb.append(LINEND);
//        // outStream.write(sb.toString().getBytes());
//        // // add by cbf log 0411
//        // // log("发送消息：" + sb.toString());
//        // }
//        // 发送参数
//        // HashMap<String, String> m_params =
//        // BuildHttpBaseData.buidData(context);
//        // for (Map.Entry<String, String> entry : m_params.entrySet()) {
//        // sb = new StringBuilder();
//        // sb.append(PREFIX);
//        // sb.append(BOUNDARY);
//        // sb.append(LINEND);
//        // sb.append("Content-Disposition: form-data; name=\"" + entry.getKey()
//        // + "\"" + LINEND);
//        // sb.append("Content-Type:text/plain;charset=" + ENCODING + LINEND);
//        // sb.append("Content-Transfer-Encoding:8bit" + LINEND);
//        // sb.append(LINEND);
//        // sb.append(entry.getValue());
//        // sb.append(LINEND);
//        // }
//        // outStream.write(sb.toString().getBytes());
//        // *****************************************后台filter里面去不到相关参数，废弃上面的代码，使用url后面拼接的方式解决。
//        // 发送数据
//        if (!TextUtils.isEmpty(contentData)) {
//            sb = new StringBuilder();
//            sb.append(PREFIX);
//            sb.append(BOUNDARY);
//            sb.append(LINEND);
//            sb.append("Content-Disposition: form-data; name=\"" + HttpSDKConstant.TXTCONTENT + "\"" + LINEND);
//            sb.append("Content-Type:text/plain;charset=" + ENCODING + LINEND);
//            sb.append("Content-Transfer-Encoding:8bit" + LINEND);
//            sb.append(LINEND);
//
//            sb.append(contentData);
//            sb.append(LINEND);
//            outStream.write(sb.toString().getBytes());
//            // add by cbf log 0411
//            // log("发送消息：" + sb.toString());
//        }
//        // if (!TextUtils.isEmpty(sendFilePath) &&
//        // !TextUtils.isEmpty(getFileName(sendFilePath)) && new
//        // File(sendFilePath).exists()) {
//        if (is != null) {
//            String fileDescribe = "Content-Disposition: form-data; name=\"";
//            fileDescribe += HttpSDKConstant.FILE + "\"";
//            fileDescribe += "; filename=\"";
//            fileDescribe += getFileName(sendFilePath);
//            fileDescribe += "\"" + LINEND;
//
//            StringBuilder sb1 = new StringBuilder();
//            sb1.append(PREFIX);
//            sb1.append(BOUNDARY);
//            sb1.append(LINEND);
//            sb1.append(fileDescribe);
//            sb1.append("Content-Type:application/octet-stream;charset=" + ENCODING + LINEND);
//            // sb1.append("Content-Length:application/octet-stream;charset=" +
//            // ENCODING + LINEND);
//            sb1.append(LINEND);
//            outStream.write(sb1.toString().getBytes());
//
//            // InputStream is = new FileInputStream(sendFilePath);
//            byte[] bufferFile = new byte[1024];
//            int len = 0;
//            while ((len = is.read(bufferFile)) != -1) {
//                outStream.write(bufferFile, 0, len);
//            }
//            is.close();
//            outStream.write(LINEND.getBytes());
//        } else {
//            if (!TextUtils.isEmpty(sendFilePath)) {
//                System.err.println("发送文件失败，获取文件名称失败。文件路径为：" + sendFilePath);
//
//            }
//        }
//        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
//        outStream.write(end_data);
//        outStream.flush();
//        outStream.close();
//        InputStream in = httpConn.getInputStream();
//
//        if (isDownloadFile) {
//            // 下载文件。
//            BufferedInputStream bis = new BufferedInputStream(in);
//            File saveFile = new File(saveFilePath);
//            if (!saveFile.exists()) {
//                saveFile.createNewFile();
//            }
//            if (!saveFile.canWrite()) {
//                throw new Exception("文件没有写权限");
//            } else {
//                FileOutputStream outFileStream = new FileOutputStream(saveFile);
//                try {
//                    byte[] buffer = new byte[10240];
//                    while (true) {
//                        int len = bis.read(buffer, 0, 10240);
//                        if (len < 0) {
//                            break;
//                        }
//                        outFileStream.write(buffer, 0, len);
//                        buffer = new byte[10240];
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    bis.close();
//                    outFileStream.close();
//                }
//                return saveFilePath;
//            }
//        } else {
//            // 获取请求的返回值
//            String strResultData = "";
//            BufferedReader br = new BufferedReader(new InputStreamReader(in, ENCODING), 1024 * 10);
//            char[] bufferChar = new char[1024 * 10];
//            int nlenmsg = 0;
//            while ((nlenmsg = br.read(bufferChar, 0, 1024 * 10)) != -1) {
//                strResultData += new String(bufferChar, 0, nlenmsg);
//                bufferChar = new char[1024 * 10];
//            }
//            br.close();
//            /**
//             * 只在登录的时候保存cookies。
//             */
//            cookie = "";
//            cookie = httpConn.getHeaderField("set-cookie");
//            if (urlStr.contains(OkHttpUtil.LOGIN_URL) && cookie != null && cookie.length() > 0) {
//                saveCookies(context, strResultData, cookie);
//            }
//            return strResultData;
//        }
//    }
//
//    /**
//     * 获取带有后缀的文件名
//     *
//     * @param pathandname 文件路径
//     * @return 不带后缀的文件名
//     */
//    public String getFileName(String pathandname) {
//        int start = pathandname.lastIndexOf("/");
//        if (start != -1) {
//            return pathandname.substring(start + 1);
//        }
//        return "";
//    }
//
//    /**
//     * 获取不带后缀的文件名
//     *
//     * @param pathandname 文件路径
//     * @return 不带后缀的文件名
//     */
//    public String getName(String pathandname) {
//        int start = pathandname.lastIndexOf("/");
//        int end = pathandname.lastIndexOf(".");
//        if (start != -1) {
//            if (end <= 0) {
//                return pathandname.substring(start + 1);
//            } else if (end > start + 1) {
//                return pathandname.substring(start + 1, end);
//            }
//        }
//        return "";
//    }
//
//    // ************************************************************去掉method，textcontent等限制，直接发送业务数据流的网络请求代码。
//
//    /**
//     * 去掉method，textcontent等限制，直接发送业务数据流的网络请求代码。（需要用到handler）
//     *
//     * @param context     上下文
//     * @param urlStr      地址
//     * @param contentData 待发送的Json数据
//     * @return 网络请求，返回值
//     */
//    public ResultData HttpURLConnectionParams(Context context, String urlStr, String contentData) {
//        return HttpURLConnectionParams(context, urlStr, contentData, true);
//    }
//
//    /**
//     * 去掉method，textcontent等限制，直接发送业务数据流的网络请求代码。（需要用到handler）
//     *
//     * @param context     上下文
//     * @param urlStr      地址
//     * @param contentData 待发送的Json数据
//     * @param isPost      请求方式（POST/GET）
//     * @return 网络请求，返回值
//     */
//    public ResultData HttpURLConnectionParams(Context context, String urlStr, String contentData, boolean isPost) {
//        ResultData resultData = null;
//        if (TextUtils.isEmpty(urlStr)) {
//            Message msg = new Message();
//            msg.what = HttpSDKConstant.CODEERROR;
//            msg.obj = "url is null .";
//            if (msg != null && handler != null) {
//                handler.sendMessage(msg);
//            }
//            resultData = new ResultData();
//            resultData.setResultType(HttpSDKConstant.CODEERROR);
//            resultData.setMsg("url is null .");
//            return resultData;
//        }
//        String MULTIPART_FROM_DATA = "application/json";
//        // 网络请求
//        HttpURLConnection httpConn = null;
//        DataOutputStream outStream = null;
//        BufferedReader br = null;
//        try {
//            URL url = new URL(urlStr);// 设置属性
//            httpConn = (HttpURLConnection) url.openConnection();
//            httpConn.setConnectTimeout(CONNECT_TIME_OUT);
//            httpConn.setReadTimeout(DEFAULT_READ_TIME_OUT);
//            httpConn.setDoOutput(true);
//            httpConn.setDoInput(true);
//            httpConn.setUseCaches(false);
//            httpConn.setChunkedStreamingMode(0);
//            if (isPost) {
//                httpConn.setRequestMethod("POST");
//            } else {
//                httpConn.setRequestMethod("GET");
//            }
//            httpConn.setRequestProperty("connection", "keep-alive");
//            httpConn.setRequestProperty("Charset", ENCODING);
//            httpConn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA);
//
//            String cookie = UserData.getCookiesValue(context, currentUserType, userID);
//            if (!TextUtils.isEmpty(cookie)) {
//                httpConn.setRequestProperty("Cookie", cookie);
//            }
//            outStream = new DataOutputStream(httpConn.getOutputStream());
//            if (!TextUtils.isEmpty(contentData)) {
//                outStream.write(contentData.getBytes());
//            }
//            Message msg = null;
//            int responseCode = httpConn.getResponseCode();
//            if (responseCode == HttpStatus.SC_OK) {
//                String strResultData = "";
//                br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), ENCODING), 1024 * 10);
//
//                char[] bufferChar = new char[1024 * 10];
//                int nlenmsg = 0;
//                while ((nlenmsg = br.read(bufferChar, 0, 1024 * 10)) != -1) {
//                    strResultData += new String(bufferChar, 0, nlenmsg);
//                    bufferChar = new char[1024 * 10];
//                }
//                msg = new Message();
//                msg.what = HttpSDKConstant.SUCCESS;
//                msg.obj = strResultData;
//                if (msg != null && handler != null) {
//                    handler.sendMessage(msg);
//                }
//                resultData = new ResultData();
//                resultData.setResultType(HttpSDKConstant.SUCCESS);
//                resultData.setMsg(strResultData);
//                /**
//                 * 只在登录的时候保存cookies。
//                 */
//                cookie = "";
//                cookie = httpConn.getHeaderField("set-cookie");
//                if (urlStr.contains(OkHttpUtil.LOGIN_URL) && cookie != null && cookie.length() > 0) {
//                    saveCookies(context, strResultData, cookie);
//                }
//                return resultData;
//            } else {
//                msg = new Message();
//                msg.what = HttpSDKConstant.FAIL;
//                msg.arg1 = responseCode;
//                msg.obj = ClientQuest.switchErrorCode(responseCode);
//                if (msg != null && handler != null) {
//                    handler.sendMessage(msg);
//                }
//                resultData = new ResultData();
//                resultData.setResultType(HttpSDKConstant.FAIL);
//                resultData.setCode(responseCode);
//                resultData.setMsg(ClientQuest.switchErrorCode(responseCode));
//                return resultData;
//            }
//        } catch (Exception e) {
//
//            if (e != null) {
//                e.printStackTrace();
//                // 代码报错
//                Message msg = new Message();
//                msg.what = HttpSDKConstant.CODEERROR;
//                msg.obj = CrashHandler.CollectExecpitonInfo(e);
//                if (msg != null && handler != null) {
//                    handler.sendMessage(msg);
//                }
//                resultData = new ResultData();
//                resultData.setResultType(HttpSDKConstant.CODEERROR);
//                resultData.setMsg(e.toString());
//                return resultData;
//            }
//        } finally {
//            if (outStream != null) {
//                try {
//                    outStream.flush();
//                    outStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return resultData;
//    }
//
//    /**
//     * 保存cookies，只在登录的时候执行。
//     *
//     * @param context 上下文
//     * @param result  登录成功后的返回信息。
//     * @param cookies 服务器返回的cookies。
//     */
//    private void saveCookies(Context context, String result, String cookies) {
//        if (!TextUtils.isEmpty(result)) {
//            HttpDataFirstParse dataFirstParse = new HttpDataFirstParse();
//            HashMap<String, String> map = dataFirstParse.parseResult(null, result);
//            if (map != null && HttpSDKConstant.StrSUCCESS.equals(map.get(HttpSDKConstant.CODE))) {
//                String data = map.get(HttpSDKConstant.DATA);
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(data);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (object != null) {
//                    String id = null;
//                    try {
//                        id = object.getString(DataConstant.currentUserInfoContent.ID);
//                        if (!TextUtils.isEmpty(id)) {
//                            // 存cookies
//                            boolean success = UserData.setCookiesValue(context, currentUserType, id, cookies);
//                            System.out.println("cookie存储操作结果：" + success);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//}
