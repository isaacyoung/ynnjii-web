package com.ynnjii.sys.controller;


import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.constant.DelEnums;
import com.ynnjii.constant.StatusEnums;
import com.ynnjii.sys.domain.SysDepartment;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dto.DeptWithUser;
import com.ynnjii.sys.dto.SysUserDto;
import com.ynnjii.sys.service.SysDepartmentService;
import com.ynnjii.sys.service.SysMenuService;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.utils.ContainerUtils;
import com.ynnjii.utils.EncodePwdUtils;
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

/**
 * 项目名称：车到山前后台
 * 类描述：用户
 * 创建人：yzh
 * 创建时间：2017/6/2
 * 备注：
 */
@Controller
@RequestMapping("/admin/user")
public class AdminSysUserController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDepartmentService departmentService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("currentUser",sysUserService.getUser(getUserName()));
        model.addAttribute("leftMenuList",menuService.getMenuTree());
        return "admin/sys/userlist";
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(SysUser user,HttpServletRequest request) {
        try {
            user.setDelFlag(DelEnums.NORMAL.getCode());

            Page page = getPageByRequest(request);
            page.setOrderBy("id desc");
            Page<SysUserDto> result =  sysUserService.selectFullByPage(user,page);
            return ContainerUtils.changeToPage(result);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        SysDepartment department = new SysDepartment();
        department.setStatus((byte) StatusEnums.NORMAL.getCode());
        department.setDelFlag(DelEnums.NORMAL.getCode());
        List<SysDepartment> list = departmentService.select(department);
        model.addAttribute("departmentList",list);
        return "admin/sys/useradd";
    }

    @RequestMapping(value = "/edit")
    public String edit(@Param("id") Integer id, Model model) {
        if (id == null) {

        }
        SysDepartment department = new SysDepartment();
        department.setStatus((byte) StatusEnums.NORMAL.getCode());
        department.setDelFlag(DelEnums.NORMAL.getCode());
        List<SysDepartment> list = departmentService.select(department);
        model.addAttribute("departmentList",list);

        SysUser sysUser = sysUserService.selectById(id);
        model.addAttribute("sysUser",sysUser);
        return "admin/sys/useradd";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Object save(SysUser entry) {
        try {
            if (entry.getId() == null) {
                SysUser userParams = new SysUser();
                userParams.setLoginName(entry.getLoginName());
                List<SysUser> sysUsers = sysUserService.select(userParams);
                if (sysUsers != null && sysUsers.size() > 0) {
                    return ApiResult.newInstance().returnFailureResult("账号已被注册，请更换");
                }

                if (StringUtils.isNullOrEmpty(entry.getPassword())) {
                    return ApiResult.newInstance().returnFailureResult("密码不能为空");
                }
            } else {
                SysUser userParams = new SysUser();
                userParams.setLoginName(entry.getLoginName());
                List<SysUser> sysUsers = sysUserService.select(userParams);
                if (sysUsers != null && sysUsers.size() > 1) {
                    return ApiResult.newInstance().returnFailureResult("账号已被注册，请更换");
                } else if (sysUsers != null && sysUsers.size() == 1 && entry.getId().intValue() != sysUsers.get(0).getId()) {
                    return ApiResult.newInstance().returnFailureResult("账号已被注册，请更换");
                }
            }

            if (!StringUtils.isNullOrEmpty(entry.getPassword())) {
                String[] salt = EncodePwdUtils.getsaltAndPwd(entry.getLoginName(), entry.getPassword());
                entry.setPassword(salt[1]);
                entry.setSalt(salt[0]);
            }

            Integer userId = sysUserService.getUserId(getUserName());

            if (entry.getId() == null) {
                entry.setDelFlag(DelEnums.NORMAL.getCode());
                entry.setCreateBy(userId);
                entry.setCreateDate(new Date());
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                sysUserService.insert(entry);
            } else {
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                sysUserService.update(entry);
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
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setDelFlag(DelEnums.DELETED.getCode());
//            sysUserService.deleteById(id);
            sysUserService.update(sysUser);
            return ApiResult.newInstance().returnSuccessResult("删除成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }

    @ResponseBody
    @RequestMapping(value = "/tree")
    public Object getDataForTree(HttpServletRequest request,@RequestParam(value = "userIds",required = false)List<Integer> userIds) {
        try {
            List<DeptWithUser> result =  sysUserService.selectUserTree();
            return ContainerUtils.userChangeToTree(result,userIds);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @RequestMapping(value = "/detail")
    public String detail(Model model) {

        SysUser sysUser = sysUserService.getUser(getUserName());
        model.addAttribute("sysUser",sysUser);

        if (sysUser.getDepartmentId() != null) {
            SysDepartment department = departmentService.selectById(sysUser.getDepartmentId());
            model.addAttribute("department", department.getName());
        }

        return "admin/sys/userdetail";
    }

    @RequestMapping(value = "/password")
    public String password(Model model) {
        return "admin/sys/userpassword";
    }

    @ResponseBody
    @RequestMapping(value = "/savepwd")
    public Object savepwd(@Param("oldpwd") String oldpwd,@Param("newpwd") String newpwd) {
        if (StringUtils.isNullOrEmpty(oldpwd) || StringUtils.isNullOrEmpty(newpwd)) {
            return ApiResult.newInstance().returnFailureResult("密码不能为空");
        }
        try {
            // 判断帐号
            SysUser sysUser = sysUserService.getUser(getUserName());

            String oldEncodePwd = EncodePwdUtils.getEncryptPwd(oldpwd,sysUser.getSalt());
            if (!sysUser.getPassword().equals(oldEncodePwd)) {
                return ApiResult.newInstance().returnFailureResult("登录密码有误");
            }

            // 修改密码
            String[] pwds = EncodePwdUtils.getsaltAndPwd(sysUser.getLoginName(),newpwd);
            sysUser.setSalt(pwds[0]);
            sysUser.setPassword(pwds[1]);
            sysUser.setUpdateDate(new Date());
            sysUser.setUpdateBy(sysUser.getId());
            int sum = sysUserService.update(sysUser);
            if (sum > 0) {
                return ApiResult.newInstance().returnSuccessResult("修改成功");
            }
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }
}
