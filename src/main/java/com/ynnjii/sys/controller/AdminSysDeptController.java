package com.ynnjii.sys.controller;

import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.constant.DelEnums;
import com.ynnjii.constant.StatusEnums;
import com.ynnjii.sys.domain.SysDepartment;
import com.ynnjii.sys.service.SysDepartmentService;
import com.ynnjii.sys.service.SysMenuService;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.utils.ContainerUtils;
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
 * 类描述：部门
 * 创建人：yzh
 * 创建时间：2017/6/5
 * 备注：
 */
@Controller
@RequestMapping("/admin/department")
public class AdminSysDeptController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysDepartmentService departmentService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("currentUser",sysUserService.getUser(getUserName()));
        model.addAttribute("leftMenuList",menuService.getMenuTree());
        return "admin/sys/departmentlist";
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(SysDepartment department, HttpServletRequest request) {
        try {
            department.setDelFlag(0);

            Page page = getPageByRequest(request);
            page.setOrderBy("id desc");
            Page<SysDepartment> result =  departmentService.selectByPage(department,page);
            return ContainerUtils.changeToPage(result);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/tree")
    public Object getDataForTree(HttpServletRequest request,@RequestParam(value = "departmentIds",required = false)List<Integer> departmentIds) {
        try {
            SysDepartment menu = new SysDepartment();
            menu.setDelFlag(DelEnums.NORMAL.getCode());

            Page page = getPageByRequest(request);
            page.setPageSize(10000);
            page.setOrderBy("sort,id");
            List<SysDepartment> result =  departmentService.selectByPage(menu,page);
            return ContainerUtils.departmentChangeToTree(result,departmentIds);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public String add(Integer id,Model model) {
        if (id != null) {
            SysDepartment menu = departmentService.selectById(id);
            model.addAttribute("parentDepartment", menu);
        }
        return "admin/sys/departmentadd";
    }

    @RequestMapping(value = "/edit")
    public String edit(@Param("id") Integer id, Model model) {
        if (id == null) {

        }
        SysDepartment sysDepartment = departmentService.selectById(id);
        model.addAttribute("sysDepartment",sysDepartment);
        if (sysDepartment.getParentId() != null && sysDepartment.getParentId() != 0) {
            SysDepartment menu = departmentService.selectById(sysDepartment.getParentId());
            model.addAttribute("parentDepartment", menu);
        }
        return "admin/sys/departmentadd";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Object save(SysDepartment entry) {
        try {
            Integer userId = sysUserService.getUserId(getUserName());

            if (entry.getParentId() == null) {
                entry.setParentId(0);
            }
            if (entry.getId() == null) {
                entry.setStatus((byte) StatusEnums.NORMAL.getCode());
                entry.setDelFlag(DelEnums.NORMAL.getCode());
                entry.setCreateBy(userId);
                entry.setCreateDate(new Date());
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                departmentService.insert(entry);
            } else {
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                departmentService.update(entry);
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
            SysDepartment sysDepartment = new SysDepartment();
            sysDepartment.setId(id);
            sysDepartment.setDelFlag(DelEnums.DELETED.getCode());
//            roleService.deleteById(id);
            departmentService.update(sysDepartment);
            return ApiResult.newInstance().returnSuccessResult("删除成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }
}
