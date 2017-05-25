package com.ynnjii.sys.service.impl;

import com.ynnjii.base.BaseServiceImp;
import com.ynnjii.sys.dao.SysUserMapper;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
@Service
public class SysUserServiceImp extends BaseServiceImp<SysUser, SysUserMapper> implements SysUserService {
    @Override
    public Set<String> findRoles(String username) {
        return getMapper().findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return getMapper().findPermissions(username);
    }

    @Override
    public Set<SysMenu> findMenuByUsername(String username) {
        return getMapper().findMenuByUsername(username);
    }
}