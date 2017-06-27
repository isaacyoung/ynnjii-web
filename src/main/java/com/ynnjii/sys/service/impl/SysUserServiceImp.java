package com.ynnjii.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ynnjii.base.BaseServiceImp;
import com.ynnjii.base.DataRightResult;
import com.ynnjii.constant.DataTypeEnums;
import com.ynnjii.constant.DelEnums;
import com.ynnjii.sys.dao.SysUserMapper;
import com.ynnjii.sys.domain.SysDepartment;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.domain.SysRole;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dto.DeptWithUser;
import com.ynnjii.sys.dto.SysUserDto;
import com.ynnjii.sys.service.SysDepartmentService;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
@Service
public class SysUserServiceImp extends BaseServiceImp<SysUser, SysUserMapper> implements SysUserService {
    @Autowired
    private SysDepartmentService departmentService;

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

    @Override
    public Integer getUserId(String username) {
        SysUser sysUser = getUser(username);
        if (sysUser != null) {
            return sysUser.getId();
        }
        return null;
    }

    @Override
    public SysUser getUser(String username) {
        if (StringUtils.isNullOrEmpty(username)) {
            return null;
        }

        SysUser user = new SysUser();
        user.setLoginName(username);
        user.setDelFlag(DelEnums.NORMAL.getCode());
        return selectOne(user);
    }

    @Override
    public List<DeptWithUser> selectUserTree() {
        return getMapper().selectUserTree();
    }

    @Override
    public DataRightResult findRight(String userName) {
        DataRightResult dataRightResult = new DataRightResult();
        Set<Integer> deptIds = new HashSet<>();
        Set<Integer> users = new HashSet<>();

        SysUser user = getUser(userName);
        Set<SysRole> roles = getMapper().findFullRoles(userName);
        if (roles == null) {
            return dataRightResult;
        }

        // 自定义部门
        Set<Integer> customRight = getMapper().findDataRight(userName);
        if (customRight != null) {
            deptIds.addAll(customRight);
        }

        roles = roles.stream().filter(role -> role.getDataType() != null).collect(Collectors.toSet());
        for (SysRole role : roles) {
            if (role.getDataType() == DataTypeEnums.ALL.getCode()) {
                DataRightResult result = new DataRightResult();
                result.setAllRights(true);
                return result;
            } else if (role.getDataType() == DataTypeEnums.DEPT_SUB.getCode()) {
                deptIds.addAll(findDeptAndSub(user.getId()));
            } else if (role.getDataType() == DataTypeEnums.SELF_SUB.getCode()) {
                users.add(user.getId());

//                Set<Integer> set = getMapper().findUserRight(userName);
//                if (set != null) {
//                    users.addAll(set);
//                }
            }
        }

        dataRightResult.setDeptIds(deptIds);
        dataRightResult.setUsers(users);
        if (dataRightResult.isEmpty()) {
            dataRightResult.setNoneRight(1);
        }
        return dataRightResult;
    }

    @Override
    public List<SysUserDto> selectFull(SysUser sysUser) {
        return getMapper().selectFull(sysUser);
    }

    @Override
    public Page<SysUserDto> selectFullByPage(SysUser sysUser, Page page) {
        Page<SysUserDto> p= PageHelper.startPage(page.getPageNum(),page.getPageSize());
        if (!StringUtils.isNullOrEmpty(page.getOrderBy())) {
            p.setOrderBy(page.getOrderBy());
        }
        selectFull(sysUser);
        return p;
    }

    private Set<Integer> findDeptAndSub(Integer userId) {
        SysUser sysUser = selectById(userId);
        if (sysUser == null) {
            return null;
        }

        List<SysDepartment> result = new ArrayList<>();
        List<SysDepartment> deptList = new ArrayList<>();
        if (sysUser.getDepartmentId() != null) {
            SysDepartment department = departmentService.selectById(sysUser.getDepartmentId());
            deptList.add(department);
            findSubDepts(result,deptList);
        }

        return result.stream().map(depart -> depart.getId()).collect(Collectors.toSet());
    }

    private void findSubDepts(List<SysDepartment> list,List<SysDepartment> deptList) {
        if (deptList == null || deptList.size() == 0) {
            return ;
        }

        list.addAll(deptList);
        for (SysDepartment depart : deptList) {
            SysDepartment  department = new SysDepartment();
            department.setParentId(depart.getId());
            List<SysDepartment> departments = departmentService.select(department);
            findSubDepts(list,departments);
        }
    }
}