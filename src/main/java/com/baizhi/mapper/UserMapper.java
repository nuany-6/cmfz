package com.baizhi.mapper;


import com.baizhi.entity.Maps;

import java.util.List;

public interface UserMapper {
    List<Maps> queryAll();

    List<Integer> findAll();
}
