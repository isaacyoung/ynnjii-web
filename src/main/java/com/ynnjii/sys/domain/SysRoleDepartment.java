package com.ynnjii.sys.domain;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public class SysRoleDepartment {
    /** 角色id */
    private Integer roleId;

    /** 部门id */
    private Integer departmentId;

    /** 获得角色id */
    public Integer getRoleId() {
        return roleId;
    }

    /** 设置角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获得部门id */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /** 设置部门id */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}