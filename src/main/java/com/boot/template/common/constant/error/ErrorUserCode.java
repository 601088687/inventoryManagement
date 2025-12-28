package com.boot.template.common.constant.error;


/**
 * 用户错误码
 */
public enum ErrorUserCode {

    USER_NOT_EXIST("5001","账号不存在"),
    USER_ACCOUNT_FAIL("5002","账号或密码错误"),
    USER_PWD_FAIL("5003","密码错误"),
    USER_VERIFY_CODE_FAIL("5004","验证码错误"),
    USER_VERIFY_CODE_INVALID("5005","验证码已失效"),
    USER_ACCOUNT_LOCK("5010","账号被锁定"),
    USER_NOT_VISIT("5101","无访问权限"),
    USER_NOT_OPERATE("5102","无操作权限")
    ;

    private String code;
    private String msg;

    ErrorUserCode() {
    }

    ErrorUserCode(String code) {
        this.code = code;
    }

    ErrorUserCode(String code, String msg) {
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
        for (ErrorUserCode type : ErrorUserCode.values()) {
            if (type.name().equals(name)) {
                return type.getMsg();
            }
        }
        return null;
    }
}
