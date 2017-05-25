package com.ynnjii.sys.controller;

import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author yzh
 * Created on 2016/9/20.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminSysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/list")
    public String list(Model model) {
        return "admin/sysuser/list";
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(SysUser user) {
        try {
            List<SysUser> result =  sysUserService.select(user);
            return ApiResult.newInstance().returnSuccessResult(result);
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "admin/sysuser/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(@Param("id") Integer id, Model model) {
        if (id == null) {

        }
        SysUser sysUser = sysUserService.selectById(id);
        model.addAttribute("sysUser",sysUser);
        return "admin/sysuser/add";
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Object save(SysUser entry) {
        try {
            if (entry.getId() == null) {
                entry.setLoginFlag(0);
                entry.setDelFlag(0);
                entry.setCreateBy(1);
                entry.setCreateDate(new Date());
                entry.setUpdateBy(1);
                entry.setUpdateDate(new Date());
                sysUserService.insert(entry);
            } else {
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
            sysUserService.deleteById(id);
            return ApiResult.newInstance().returnSuccessResult("删除成功");
        } catch (Exception e) {
            logger.error("",e);
        }
        return ApiResult.newInstance().returnFailureResult();
    }
}
