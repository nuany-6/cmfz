package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import com.baizhi.entity.Special;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    List<Chapter> findAllPage(@Param("start") Integer start, @Param("rows") Integer rows,@Param("id") String id);
    Integer count(String id);
    void add(Chapter chapter);
    void updatePath(@Param("src")String src,@Param("size")String size,@Param("date")String date,@Param("id")String id);
    void update(Chapter chapter);
    void del(String[] id);

}
