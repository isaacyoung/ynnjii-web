package com.ynnjii.constant;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/2
 * 备注：
 */
public enum DelEnums {
    NORMAL(0,"正常"),
    DELETED(1,"删除");

    private int code;
    private String desc;

    DelEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
