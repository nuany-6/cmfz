package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;


public interface ArticleService {
    Map<String, Object> queryAll(Integer page, Integer rows);

    List<Article> query(String val);
    Integer count();

    void add(Article article);

    void update(Article article);

    void del(String[] id, String oper);
}
