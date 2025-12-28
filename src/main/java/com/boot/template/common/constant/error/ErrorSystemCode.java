package com.boot.template.common.constant.error;


/**
 * 系统错误码
 */
public enum ErrorSystemCode {
    SYSTEM_SUCC("1000","操作成功"),
    //系统内部错误
    SYSTEM_INSIDE("1001","系统内部错误"),
    SYSTEM_TIMEOUT("1002","操作超时"),
    SYSTEM_PARAMETER("1003","参数错误");

    private String code;
    private String msg;

    ErrorSystemCode() {
    }

    ErrorSystemCode(String code) {
        this.code = code;
    }

    ErrorSystemCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public String getCode() {
        return code;
    }
}
