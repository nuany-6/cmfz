package com.baizhi.service;

import com.baizhi.entity.Special;

import java.util.Map;

public interface SpecialService {
    Map<String, Object> queryAll(Integer page, Integer rows);

    void add(Special special);

    void updatePath(String src, String id);

    void update(Special special);

    void del(String[] id);

    void delChapter(String[] id);

    void updateCount(Integer count, String id);

    Integer selectById(String id);
}
