package com.commonsdk.http.util;

/**
 * 自己写的网络请求，工具类的返回值。
 * @author ztx
 */
public class ResultData {
	/** 访问的结果，－1:代表服务器访问失败；0:代表代码级别报错；1:代表服务器访问成功 */
	private int resultType = -1;
	/** 网络请求的错误code（如：404），当resultType=-1时，有意义。 */
	private int code = -1;
	/** 报错信息，或成功求情后，返回的内容 */
	private String msg = "";

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResultData [resultType=" + resultType + ", code=" + code + ", msg=" + msg + "]";
	}

}
