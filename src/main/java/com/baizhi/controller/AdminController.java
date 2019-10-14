package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String> login(HttpSession session, String username, String password, String code){
        System.out.println("session = [" + session + "], username = [" + username + "], password = [" + password + "], code = [" + code + "]");
        Map<String, String> login = adminService.login(username, password, code, session);
        return login;
    }
    @RequestMapping("/edit")
    public String edit(HttpSession session){
        session.invalidate();
        return "redirect:/login/login.jsp";
    }

    @RequestMapping(value="image",method= RequestMethod.GET)
    public void authImage(HttpSession session, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");

        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());

        // 生成图片
        int w = 70, h = 30;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }
}
