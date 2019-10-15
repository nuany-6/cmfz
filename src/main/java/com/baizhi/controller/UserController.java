package com.baizhi.controller;

import com.baizhi.entity.Maps;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("queryAll")
    public List<Maps> queryAll() {
        List<Maps> users = userService.queryAll();
        return users;
    }

    @RequestMapping("findAll")
    public List<Integer> findAll() {
        List<Integer> users = userService.findAll();
        return users;
    }
}
