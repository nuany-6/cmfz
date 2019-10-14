package com.baizhi.controller;

import com.baizhi.entity.Maps;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("queryAll")
    public List<Maps> queryAll(){
        List<Maps> users = userService.queryAll();
        return users;
    }
    @RequestMapping("findAll")
    public List<Integer> findAll(){
        List<Integer> users = userService.findAll();
        return users;
    }
}
