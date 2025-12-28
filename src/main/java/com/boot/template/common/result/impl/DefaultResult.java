package com.boot.template.common.result.impl;


import com.boot.template.common.result.IResultable;

public class DefaultResult implements IResultable {
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";
	public static final String MSG_SUCCESS = "操作成功";

	private Object data;
	private String code;
	private String status;
	private String message;
	private String location;

	public DefaultResult() {}
	
	public DefaultResult(Object data, String code, String status, String message, String location) {
		this.data = data;
		this.code = code;
		this.status = status;
		this.message = message;
		this.location = location;
	}


	public DefaultResult(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static DefaultResult success(Object data) {
		return new DefaultResult(SUCCESS, MSG_SUCCESS, data);
	}

	public static DefaultResult fail(String message) {
		return new DefaultResult(FAIL, message, null);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
