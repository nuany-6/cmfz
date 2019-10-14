package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = articleService.queryAll(page, rows);
        return map;
    }
    @RequestMapping("add")
    public void add(Article article){
        String id = UUID.randomUUID().toString().replace("-", "");
        article.setId(id);
        article.setDate(new Date());
        articleService.add(article);
    }
    @RequestMapping("update")
    public void update(Article article){
        articleService.update(article);
    }
    @RequestMapping("edit")
    public String edit(String[] id,String oper){
        articleService.del(id, oper);
        return null;
    }
}
