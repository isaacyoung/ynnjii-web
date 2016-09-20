package com.ynnjii.controller.admin;

import com.ynnjii.entity.SysUser;
import com.ynnjii.service.SysUserService;
import com.ynnjii.common.PageResult;
import com.ynnjii.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yzh
 * Created on 2016/9/20.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminSysUserController {

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
            e.printStackTrace();
        }
        return new PageResult();
    }


}
