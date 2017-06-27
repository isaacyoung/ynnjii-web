package com.ynnjii.sys.controller;


import com.github.pagehelper.Page;
import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.constant.DelEnums;
import com.ynnjii.constant.StatusEnums;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.service.SysMenuService;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.utils.ContainerUtils;
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
 * 类描述：菜单
 * 创建人：yzh
 * 创建时间：2017/6/2
 * 备注：
 */
@Controller
@RequestMapping("/admin/menu")
public class AdminSysMenuController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("currentUser",sysUserService.getUser(getUserName()));
        model.addAttribute("leftMenuList",menuService.getMenuTree());
        return "admin/sys/menulist";
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(SysMenu menu, HttpServletRequest request) {
        try {
            menu.setDelFlag(DelEnums.NORMAL.getCode());

            Page page = getPageByRequest(request);
            page.setOrderBy("id desc");
            page.setPageSize(10000);
            Page<SysMenu> result =  menuService.selectByPage(menu,page);
            return ContainerUtils.changeToPage(result);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/tree")
    public Object getDataForTree(HttpServletRequest request,@RequestParam(value = "menuIds",required = false)List<Integer> menuIds) {
        try {
            SysMenu menu = new SysMenu();
            menu.setDelFlag(DelEnums.NORMAL.getCode());

            Page page = getPageByRequest(request);
            page.setPageSize(10000);
            page.setOrderBy("sort,id");
            List<SysMenu> result =  menuService.selectByPage(menu,page);
            return ContainerUtils.menuChangeToTree(result,menuIds);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public String add(Integer id,Model model) {
        if (id != null) {
            SysMenu menu = menuService.selectById(id);
            model.addAttribute("parentMenu", menu);
        }
        return "admin/sys/menuadd";
    }

    @RequestMapping(value = "/edit")
    public String edit(@Param("id") Integer id, Model model) {
        if (id == null) {

        }

        SysMenu sysMenu = menuService.selectById(id);
        model.addAttribute("sysMenu",sysMenu);
        if (sysMenu.getParentId() != null && sysMenu.getParentId() != 0) {
            SysMenu menu = menuService.selectById(sysMenu.getParentId());
            model.addAttribute("parentMenu", menu);
        }
        return "admin/sys/menuadd";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Object save(SysMenu entry) {
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
                menuService.insert(entry);
            } else {
                entry.setUpdateBy(userId);
                entry.setUpdateDate(new Date());
                menuService.update(entry);
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
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(id);
            sysMenu.setDelFlag(DelEnums.DELETED.getCode());
//            menuService.deleteById(id);
            menuService.update(sysMenu);
            return ApiResult.newInstance().returnSuccessResult("删除成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }
}
