package com.baizhi.service;

import com.baizhi.entity.Slideshow;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BannerService {
    Map<String, Object> findAll(Integer page, Integer rows);
    void queryAll(HttpServletRequest request, HttpServletResponse response);
    void add(Slideshow slideshow);
    void update(Slideshow slideshow);
    void updateSrc( String src, String id);
    int count();
    void delete(String[] id);
}
