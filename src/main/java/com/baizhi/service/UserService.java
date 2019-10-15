package com.baizhi.service;

import com.baizhi.entity.Maps;

import java.util.List;

public interface UserService {
    List<Maps> queryAll();

    List<Integer> findAll();
}
