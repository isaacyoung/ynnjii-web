package com.ynnjii.sys.domain;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public class SysUserRole {
    /** 用户id */
    private Integer userId;

    /** 角色id */
    private Integer roleId;

    /** 获得用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获得角色id */
    public Integer getRoleId() {
        return roleId;
    }

    /** 设置角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}