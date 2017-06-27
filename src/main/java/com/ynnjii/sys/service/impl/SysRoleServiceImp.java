package com.ynnjii.sys.service.impl;

import com.ynnjii.base.BaseServiceImp;
import com.ynnjii.sys.dao.SysRoleMapper;
import com.ynnjii.sys.domain.SysRole;
import com.ynnjii.sys.domain.SysRoleDepartment;
import com.ynnjii.sys.domain.SysRoleMenu;
import com.ynnjii.sys.domain.SysUserRole;
import com.ynnjii.sys.service.SysRoleDepartmentService;
import com.ynnjii.sys.service.SysRoleMenuService;
import com.ynnjii.sys.service.SysRoleService;
import com.ynnjii.sys.service.SysUserRoleService;
import com.ynnjii.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
@Service
public class SysRoleServiceImp extends BaseServiceImp<SysRole, SysRoleMapper> implements SysRoleService {

    @Autowired
    private SysRoleMenuService roleMenuService;
    @Autowired
    private SysRoleDepartmentService roleDepartmentService;
    @Autowired
    private SysUserRoleService userRoleService;

    @Transactional
    @Override
    public int savePermissions(Integer roleId, Integer dataType, List<Integer> menuIds, List<Integer> dataIds) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setDataType(dataType.byteValue());
        int sum = update(sysRole);

        // delete
        SysRoleMenu oldRoleMenu = new SysRoleMenu();
        oldRoleMenu.setRoleId(roleId);
        roleMenuService.delete(oldRoleMenu);

        SysRoleDepartment oldRoleDept = new SysRoleDepartment();
        oldRoleDept.setRoleId(roleId);
        roleDepartmentService.delete(oldRoleDept);

        // save
        if (!StringUtils.isNullOrEmpty(menuIds)) {
            for (Integer menuId : menuIds) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuService.insert(roleMenu);
            }
        }

        if (!StringUtils.isNullOrEmpty(dataIds)) {
            for (Integer dataId : dataIds) {
                SysRoleDepartment roleDepartment = new SysRoleDepartment();
                roleDepartment.setRoleId(roleId);
                roleDepartment.setDepartmentId(dataId);
                roleDepartmentService.insert(roleDepartment);
            }
        }

        return sum;
    }

    @Override
    public int saveUsers(Integer roleId, List<Integer> userIds) {
        // delete
        SysUserRole oldUserRole = new SysUserRole();
        oldUserRole.setRoleId(roleId);

        if (roleId == 1) { // 保留管理员权限
            List<SysUserRole> list = userRoleService.select(oldUserRole);
            if (!StringUtils.isNullOrEmpty(list)) {
                for (SysUserRole userRole : list) {
                    if (userRole.getUserId() != 1) {
                        userRoleService.delete(userRole);
                    }
                }
            }
        } else {
            userRoleService.delete(oldUserRole);
        }

        // save
        if (!StringUtils.isNullOrEmpty(userIds)) {
            for (Integer userId : userIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                userRoleService.insert(userRole);
            }
        }
        return userIds.size();
    }

}