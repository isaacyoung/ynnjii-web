package com.ynnjii.sys.domain;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public class SysRoleMenu {
    /** 角色id */
    private Integer roleId;

    /** 菜单id */
    private Integer menuId;

    /** 获得角色id */
    public Integer getRoleId() {
        return roleId;
    }

    /** 设置角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获得菜单id */
    public Integer getMenuId() {
        return menuId;
    }

    /** 设置菜单id */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}