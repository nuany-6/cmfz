package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String,Object> findAllPage(Integer page, Integer rows,String id);
    void add(Chapter chapter);
    void updatePath(String src,String size,String date,String id);
    void update(Chapter chapter);
    void del(String[] id);

}
