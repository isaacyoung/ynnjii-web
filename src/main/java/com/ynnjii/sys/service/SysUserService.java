package com.ynnjii.sys.service;

import com.github.pagehelper.Page;
import com.ynnjii.base.BaseService;
import com.ynnjii.base.DataRightResult;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dto.DeptWithUser;
import com.ynnjii.sys.dto.SysUserDto;

import java.util.List;
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

    Integer getUserId(String username);

    SysUser getUser(String username);

    List<DeptWithUser> selectUserTree();

    /**
     * 获取数据权限
     * @param userName
     * @return
     */
    DataRightResult findRight(String userName);

    List<SysUserDto> selectFull(SysUser sysUser);

    Page<SysUserDto> selectFullByPage(SysUser sysUser, Page page);
}