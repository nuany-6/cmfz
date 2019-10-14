package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, String> login(String username, String password, String code, HttpSession session) {
        String verCode = (String) session.getAttribute("verCode");
        System.out.println(verCode+"--------");
        Admin admin = adminMapper.login(username);
        session.setAttribute("admin",admin);

        HashMap<String, String> map = new HashMap<>();
        if (!verCode.equals(code)){
            map.put("msg","验证码错误");
        }else if(admin==null){
            map.put("msg","用户名不存在");
        }else if(!admin.getPassword().equals(password)){
            map.put("msg","密码错误");
        }
        return map;
    }
}
