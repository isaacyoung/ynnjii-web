package com.ynnjii.sys.controller;

import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台登录页
 * @author yzh
 * Created on 2016/8/29.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

    @RequestMapping(value = "/login")
    public String login(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/logining")
    public String save(Model model,
                       @RequestParam(value = "username", required = false) String userName,
                       @RequestParam(value = "remember", required = false) boolean remember,
                       @RequestParam(value = "captcha", required = false) String captcha,
                       @RequestParam(value = "password", required = false) String password) {
        if (StringUtils.isNullOrEmpty(userName) ||
                StringUtils.isNullOrEmpty(password) ) {
            logger.info("填写为空");
            model.addAttribute("loginErrorMsg", "用户名|密码|验证码 填写不能为空");
            return "admin/login";
        }

        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(userName);
        token.setPassword(password.toCharArray());
        token.setRememberMe(remember);

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            logger.error("user:" + userName + "[登录失败]",e);
            model.addAttribute("loginErrorMsg", "密码或者账号有问题!");
            model.addAttribute("account", userName);
            return "admin/login";
        }catch (DisabledAccountException a){
            logger.error("user:" + userName + "[登录失败]",a);
            model.addAttribute("loginErrorMsg", "该账号已禁用!");
            model.addAttribute("account", userName);
            return "admin/login";
        }catch (Exception e){
            logger.error("user:" + userName + "[登录失败]",e);
            model.addAttribute("loginErrorMsg", "密码或者账号有问题!");
            model.addAttribute("account", userName);
            return "admin/login";
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return ApiResult.newInstance().returnSuccessResult("注销成功");
        } catch (Exception e) {
            logger.error("注销异常",e);
        }

        return ApiResult.newInstance().returnFailureResult();
    }

}
