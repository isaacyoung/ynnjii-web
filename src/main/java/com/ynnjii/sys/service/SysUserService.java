package com.ynnjii.sys.service;

import com.ynnjii.base.BaseService;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysUser;

import java.util.Set;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public interface SysUserService extends BaseService<SysUser> {
    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    Set<SysMenu> findMenuByUsername(String username);
}