package com.ynnjii.sys.dao;

import com.ynnjii.base.BaseMapper;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysUser;

import java.util.Set;

public interface SysUserMapper extends BaseMapper<SysUser> {

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    Set<SysMenu> findMenuByUsername(String username);
}