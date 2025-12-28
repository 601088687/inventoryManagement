package com.boot.template.common.constant.error;

/**
 * 查询码
 */
public enum StatusCode {

    SUCC("1","查询成功"),
    FAIL("0","查询失败");

    private String code;
    private String msg;

    StatusCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
