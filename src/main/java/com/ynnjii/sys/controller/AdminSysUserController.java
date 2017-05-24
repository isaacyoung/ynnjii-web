package com.ynnjii.sys.controller;

import com.ynnjii.base.BaseController;
import com.ynnjii.base.PageResult;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.sys.dto.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public PageResult getData(SysUserVo vo) {
        try {
            PageResult result =  sysUserService.selectList(vo);
            return result;
        } catch (Exception e) {
            logger.error("",e);
        }
        return new PageResult();
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
    public Map save(SysUser entry) {
        Map map = new HashMap<String,Object>();
        try {
            if (entry.getId() == null) {
                entry.setLoginFlag(0);
                entry.setDelFlag(0);
                entry.setCreateBy(1);
                entry.setCreateDate(new Date());
                entry.setUpdateBy(1);
                entry.setUpdateDate(new Date());
                sysUserService.insertData(entry);
            } else {
                sysUserService.updateData(entry);
            }
            map.put("code",1);
            map.put("msg","保存成功");
        } catch (Exception e) {
            logger.error("",e);
            map.put("code",0);
            map.put("msg","保存失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public Map del(@Param("id") Integer id) {
        Map map = new HashMap<String,Object>();
        try {
            sysUserService.deleteData(id);
            map.put("code",1);
            map.put("msg","删除成功");
        } catch (Exception e) {
            logger.error("",e);
            map.put("code",0);
            map.put("msg","删除失败");
        }
        return map;
    }
}
