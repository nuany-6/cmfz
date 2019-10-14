package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AdminService {
    Map<String,String> login(String username, String password, String code, HttpSession session);
}
