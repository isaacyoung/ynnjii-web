package com.ynnjii.sys.dto;


import com.ynnjii.sys.domain.SysDepartment;
import com.ynnjii.sys.domain.SysUser;

import java.util.List;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/13
 * 备注：
 */
public class DeptWithUser extends SysDepartment {
    List<SysUser> users;

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }
}
