package com.boot.template.common.constant.error;


/**
 * 移动端错误码
 */
public enum ErrorMobileCode {

    ILLEGAL_TOKEN("1201","非法的token"),
    ILLEGAL_TOKEN_INVALID("1202","token已过期");

    private String code;
    private String msg;

    ErrorMobileCode() {
    }

    ErrorMobileCode(String code) {
        this.code = code;
    }

    ErrorMobileCode(String code, String msg) {
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

    public static String fromTypeMsg(String name) {
        for (ErrorMobileCode type : ErrorMobileCode.values()) {
            if (type.name().equals(name)) {
                return type.getMsg();
            }
        }
        return null;
    }
}
