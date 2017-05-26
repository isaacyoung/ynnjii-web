package com.ynnjii.sys.controller;

import com.ynnjii.base.ApiResult;
import com.ynnjii.base.BaseController;
import com.ynnjii.utils.ImageCodeUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 后台登录页
 * @author yzh
 * Created on 2016/8/29.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {
    public static final String VER_CODE = "VER_CODE";

    @RequestMapping(value = "/login")
    public String login(Model model,
                        @RequestParam(required = false) String loginErrorMsg,
                        @RequestParam(required = false) String username) {
        if (!StringUtils.isNullOrEmpty(loginErrorMsg)) {
            model.addAttribute("loginErrorMsg",loginErrorMsg);
        }
        if (!StringUtils.isNullOrEmpty(username)) {
            model.addAttribute("username",username);
        }
        return "admin/login";
    }

    @RequestMapping(value = "/logining")
    public String save(Model model,HttpServletRequest request,
                       @RequestParam(value = "username", required = false) String userName,
                       @RequestParam(value = "remember", required = false) boolean remember,
                       @RequestParam(value = "captcha", required = false) String captcha,
                       @RequestParam(value = "password", required = false) String password) {
        if (StringUtils.isNullOrEmpty(userName) ||
                StringUtils.isNullOrEmpty(password) ) {
            logger.info("填写为空");
            model.addAttribute("loginErrorMsg", "用户名|密码|验证码 填写不能为空");
            return "redirect:/admin/login";
        }

        /*if (request.getSession().getAttribute(VER_CODE) == null) {
            model.addAttribute("loginErrorMsg", "服务器异常，请稍后重试");
            return "redirect:/admin/login";
        }

        Boolean isResponseCorrect = captcha.equalsIgnoreCase(request.getSession().getAttribute(VER_CODE).toString());

        if (!isResponseCorrect) {
            logger.info("验证码错误 提交为 ：{} ,正确为:{} ", captcha, request.getSession().getAttribute(VER_CODE));
            model.addAttribute("loginErrorMsg", "验证码错误");
            model.addAttribute("username", userName);
            return "redirect:/admin/login";
        }*/

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
            model.addAttribute("username", userName);
            return "redirect:/admin/login";
        }catch (DisabledAccountException a){
            logger.error("user:" + userName + "[登录失败]",a);
            model.addAttribute("loginErrorMsg", "该账号已禁用!");
            model.addAttribute("username", userName);
            return "redirect:/admin/login";
        }catch (Exception e){
            logger.error("user:" + userName + "[登录失败]",e);
            model.addAttribute("loginErrorMsg", "密码或者账号有问题!");
            model.addAttribute("username", userName);
            return "redirect:/admin/login";
        }

        return "redirect:/admin/index";
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = ImageCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute(VER_CODE);
        session.setAttribute(VER_CODE, verifyCode.toLowerCase());
        //生成图片
        int w = 117, h = 48;
        ImageCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
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
