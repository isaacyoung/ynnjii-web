package com.ynnjii.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yzh
 * Created on 2016/8/28.
 */
@Controller
public class FrontIndexController {

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("message","Hello world!");
        return "front/index";
    }
}
