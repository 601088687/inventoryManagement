package com.boot.template.common.constant.error;


/**
 * 应用错误码
 */
public enum ErrorApplyCode {

    APPLY_NOT_OPEN("2001","应用未开通"),
    APPLY_EXPIRE("2002","应用已到期");

    private String code;
    private String msg;

    ErrorApplyCode() {
    }

    ErrorApplyCode(String code) {
        this.code = code;
    }

    ErrorApplyCode(String code, String msg) {
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
        for (ErrorApplyCode type : ErrorApplyCode.values()) {
            if (type.name().equals(name)) {
                return type.getMsg();
            }
        }
        return null;
    }
}
