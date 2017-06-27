package com.ynnjii.sys.dto;


import com.ynnjii.sys.domain.SysUser;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/22
 * 备注：
 */
public class SysUserDto extends SysUser {

    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
