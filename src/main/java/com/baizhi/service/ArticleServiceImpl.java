package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.Special;
import com.baizhi.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Integer count = articleMapper.count();
        Integer stats = (page - 1) * rows;
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        List<Article> all = articleMapper.queryAll(stats, rows);
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", all);
        maps.put("records", count);
        maps.put("page", page);
        maps.put("total", total);
        return maps;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer count() {
        return articleMapper.count();
    }

    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void del(String[] id, String oper) {
        if (oper.equals("del")){
            articleMapper.del(id);
        }
    }
}
