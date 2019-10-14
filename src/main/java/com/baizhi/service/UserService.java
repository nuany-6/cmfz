package com.baizhi.service;

import com.baizhi.entity.Maps;
import com.baizhi.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<Maps> queryAll();
    List<Integer> findAll();
}
