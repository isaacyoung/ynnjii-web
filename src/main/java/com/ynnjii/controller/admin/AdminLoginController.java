package com.ynnjii.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台登录页
 * @author yzh
 * Created on 2016/8/29.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "admin/login";
    }


    public String save(Model model) {
        return "admin/login";
    }

}
