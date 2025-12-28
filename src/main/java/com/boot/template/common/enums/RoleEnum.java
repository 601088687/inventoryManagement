package com.boot.template.common.enums;

/**
 * @author yishuai
 * @date 2022-07-28 15:51
 **/
public enum RoleEnum {
    SYSTEM_ADMIN("00", "sysadmin", "2", "超级管理员"),
    EDU_ADMIN("01", "eduadmin", "2", "校管理员"),
    TC_ADMIN("02", "tcadmin", "1", "教学负责人"),
    COLLEGE_ADMIN("03", "collegeadmin", "2", "单位管理员"),
    TB_ADMIN("04", "tbadmin", "1", "出题系统负责人"),
    TEACHER("05", "teacher", "1", "教师"),
    STUDENT("06", "student", "0", "学生"),
    EDU_LEADER("09", "eduleader", "2", "学校领导"),

    ;
    /**
     * 角色code
     */
    private String code;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 角色类型
     * 0 学生 1 老师 2 管理员
     */
    private String roleType;

    /**
     * 中文名称
     */
    private String cnName;

    public String getCode() {
        return code;
    }

    public String getEnName() {
        return enName;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getCnName() {
        return cnName;
    }

    RoleEnum(String code, String enName, String roleType, String cnName) {
        this.code = code;
        this.enName = enName;
        this.roleType = roleType;
        this.cnName = cnName;
    }
}
