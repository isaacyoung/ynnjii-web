package com.ynnjii.sys.controller;

import com.ynnjii.base.BaseController;
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
    @RequestMapping(value = "/index")
    public String login(Model model) {
        return "admin/index";
    }
}
