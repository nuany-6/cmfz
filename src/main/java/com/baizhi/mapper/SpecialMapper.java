package com.baizhi.mapper;

import com.baizhi.entity.Special;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialMapper {
    List<Special> queryAll(@Param("start") Integer start, @Param("rows") Integer rows);
    Integer count();
    void add(Special special);
    void updatePath(@Param("src")String src,@Param("id")String id);
    void update(Special special);
    void del(String[] id);
    void delChapter(String[] id);
    void updateCount(@Param("count") Integer count,@Param("id") String id);
    Integer selectById(String id);
}
