package com.baizhi.mapper;

import com.baizhi.entity.Slideshow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    List<Slideshow> findAll(@Param("start") Integer start, @Param("rows") Integer rows);
    List<Slideshow> queryAll();
    void add(Slideshow slideshow);
    void update(Slideshow slideshow);
    void updateSrc(@Param("src") String src, @Param("id") String id);
    Integer count();
    void delete(String[] id);
}
