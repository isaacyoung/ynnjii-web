package com.ynnjii.sys.controller;

import com.ynnjii.base.BaseController;
import com.ynnjii.sys.service.SysMenuService;
import com.ynnjii.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页
 * @author yzh
 * Created on 2016/8/29.
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/index")
    public String login(Model model) {
        model.addAttribute("currentUser",sysUserService.getUser(getUserName()));
        model.addAttribute("leftMenuList",menuService.getMenuTree());
        return "admin/index";
    }
}
