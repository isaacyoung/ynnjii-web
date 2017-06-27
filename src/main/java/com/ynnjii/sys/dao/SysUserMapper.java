package com.ynnjii.sys.dao;

import com.ynnjii.base.BaseMapper;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysRole;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dto.DeptWithUser;
import com.ynnjii.sys.dto.SysUserDto;

import java.util.List;
import java.util.Set;

public interface SysUserMapper extends BaseMapper<SysUser> {

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    Set<SysMenu> findMenuByUsername(String username);

    /**
     * 获取用户角色
     * @param userName
     * @return
     */
    Set<SysRole> findFullRoles(String userName);

    /**
     * 获取自定义数据权限
     * @param userName
     * @return
     */
    Set<Integer> findDataRight(String userName);

    List<DeptWithUser> selectUserTree();

    /**
     * 下级用户
     * @param userName
     * @return
     */
    Set<Integer> findUserRight(String userName);


    List<SysUserDto> selectFull(SysUser sysUser);
}