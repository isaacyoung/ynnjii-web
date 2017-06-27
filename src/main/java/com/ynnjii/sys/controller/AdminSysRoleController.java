package com.ynnjii.sys.controller;

import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.constant.DelEnums;
import com.ynnjii.constant.StatusEnums;
import com.ynnjii.sys.domain.*;
import com.ynnjii.sys.service.*;
import com.ynnjii.utils.ContainerUtils;
import com.ynnjii.utils.StringUtils;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目名称：车到山前后台
 * 类描述：角色
 * 创建人：yzh
 * 创建时间：2017/6/2
 * 备注：
 */
@Controller
@RequestMapping("/admin/role")
public class AdminSysRoleController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService roleMenuService;
    @Autowired
    private SysRoleDepartmentService roleDepartmentService;
    @Autowired
    private SysUserRoleService userRoleService;


    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("currentUser",sysUserService.getUser(getUserName()));
        model.addAttribute("leftMenuList",menuService.getMenuTree());
        return "admin/sys/rolelist";
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(SysRole role, HttpServletRequest request) {
        try {
            role.setDelFlag(DelEnums.NORMAL.getCode());

            Page page = getPageByRequest(request);
            page.setOrderBy("id desc");
            Page<SysRole> result =  roleService.selectByPage(role,page);
            return ContainerUtils.changeToPage(result);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "admin/sys/roleadd";
    }

    @RequestMapping(value = "/edit")
    public String edit(@Param("id") Integer id, Model model) {
        if (id == null) {

        }
        SysRole sysRole = roleService.selectById(id);
        model.addAttribute("sysRole",sysRole);
        return "admin/sys/roleadd";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Object save(SysRole entry) {
        try {
            Integer userId = sysUserService.getUserId(getUserName());

            if (entry.getId() == null) {
                entry.setStatus((byte) StatusEnums.NORMAL.getCode());
                entry.setDelFlag(DelEnums.NORMAL.getCode());
                entry.setCreateBy(userId);
                entry.setCreateDate(new Date());
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                roleService.insert(entry);
            } else {
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                roleService.update(entry);
            }
            return ApiResult.newInstance().returnSuccessResult("保存成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public Object del(@Param("id") Integer id) {
        try {
            SysRole sysRole = new SysRole();
            sysRole.setId(id);
            sysRole.setDelFlag(DelEnums.DELETED.getCode());
//            roleService.deleteById(id);
            roleService.update(sysRole);
            return ApiResult.newInstance().returnSuccessResult("删除成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }

    @RequestMapping(value = "/permissions")
    public String permissions(@Param("id") Integer id, Model model) {
        if (id == null) {

        }
        SysRole sysRole = roleService.selectById(id);
        model.addAttribute("sysRole",sysRole);

        SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(id);
        List<SysRoleMenu> roleMenus = roleMenuService.select(roleMenu);
        if (!StringUtils.isNullOrEmpty(roleMenus)) {
            List<Integer> menuIds = roleMenus.stream().map(x -> x.getMenuId()).collect(Collectors.toList());
            model.addAttribute("menuIds",org.apache.commons.lang.StringUtils.join(menuIds.toArray(),","));
        }

        SysRoleDepartment roleDepartment = new SysRoleDepartment();
        roleDepartment.setRoleId(id);
        List<SysRoleDepartment> roleDepartments = roleDepartmentService.select(roleDepartment);
        if (!StringUtils.isNullOrEmpty(roleDepartments)) {
            List<Integer> departmentIds = roleDepartments.stream().map(x -> x.getDepartmentId()).collect(Collectors.toList());
            model.addAttribute("departmentIds",org.apache.commons.lang.StringUtils.join(departmentIds.toArray(),","));
        }

        return "admin/sys/rolepermissions";
    }

    /**
     * 设置权限
     * @param roleId
     * @param dataType
     * @param menuIds
     * @param dataIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savepermissions")
    public Object savepermissions(Integer roleId, Integer dataType,
                                  @RequestParam(value = "menuIds",required = false)List<Integer> menuIds,
                                  @RequestParam(value = "dataIds",required = false)List<Integer> dataIds) {
        if (roleId == null) {
            return ApiResult.newInstance().returnFailureResult("参数异常");
        }
        try {
            roleService.savePermissions(roleId,dataType,menuIds,dataIds);
            return ApiResult.newInstance().returnSuccessResult("设置成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }

    @RequestMapping(value = "/users")
    public String users(@Param("id") Integer id, Model model) {
        if (id == null) {

        }
        SysRole sysRole = roleService.selectById(id);
        model.addAttribute("sysRole",sysRole);

        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(id);
        List<SysUserRole> userRoles = userRoleService.select(userRole);
        if (!StringUtils.isNullOrEmpty(userRoles)) {
            List<Integer> userIds = userRoles.stream().map(x -> x.getUserId()).collect(Collectors.toList());
            model.addAttribute("userIds",org.apache.commons.lang.StringUtils.join(userIds.toArray(),","));
        }
        return "admin/sys/roleusers";
    }

    /**
     * 分配用户
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveusers")
    public Object saveusers(Integer roleId,@RequestParam(value = "userIds",required = false)List<Integer> userIds) {
        if (roleId == null) {
            return ApiResult.newInstance().returnFailureResult("参数异常");
        }
        try {
            roleService.saveUsers(roleId,userIds);
            return ApiResult.newInstance().returnSuccessResult("设置成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }
}
