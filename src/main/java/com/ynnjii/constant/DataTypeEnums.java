package com.ynnjii.constant;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/13
 * 备注：
 */
public enum DataTypeEnums {
    ALL(0,"全公司"),
    DEPT_SUB(1,"所在部门及下级部门"),
    SELF_SUB(2,"自己及直接下级"),
    CUSTOM_DEPT(3,"特定部门");

    private int code;
    private String desc;

    DataTypeEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
