package com.commonsdk.http.util;

/**
 * 网络请求，常量类
 * @author ztx
 */
public class HttpSDKConstant {
	/** http固定的请求参数字段 */
	public static final String METHOD = "method";
	/** http固定的请求参数字段 */
	public static final String TXTCONTENT = "textcontent";
	/** http固定的返回参数字段 －1:代表失败，1代表成功，其他的自己协定 */
	public static final String CODE = "code";
	/** http固定的返回参数字段 */
	public static final String MSG = "msg";
	/** http固定的返回参数字段 */
	public static final String DATA = "data";
	/** http请求失败(int型) */
	public static final int FAIL = -1;
	/** http请求代码报错(int型) */
	public static final int CODEERROR = 0;
	/** http请求成功(int型) */
	public static final int SUCCESS = 1;
	/** http请求失败(String型) */
	public static final String StrFAIL = "-1";
	/** http请求成功(String型) */
	public static final String StrSUCCESS = "1";
	/** 设备唯一编号 */
	public static final String MACHINEID = "machineid";
	/** 用户UUID号 */
	public static final String USERID = "userid";
	/** 空字符串 */
	public static final String NULL = "null";
	/** 发送文件的参数 */
	public static final String FILE = "file";
	/** 用户的cookie信息 */
	public static final String COOKIE = "cookie";

}
