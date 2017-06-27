package com.ynnjii.sys.service;

import com.ynnjii.base.BaseService;
import com.ynnjii.sys.domain.SysRole;

import java.util.List;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 设置权限
     * @param roleId
     * @param dataType
     * @param menuIds
     * @param dataIds
     * @return
     */
    int savePermissions(Integer roleId, Integer dataType, List<Integer> menuIds, List<Integer> dataIds);

    /**
     * 分配用户
     * @param roleId
     * @param userIds
     * @return
     */
    int saveUsers(Integer roleId,List<Integer> userIds);

}