package com.baizhi.service;

import com.baizhi.entity.Chapter;
import com.baizhi.entity.Special;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> findAllPage(Integer page, Integer rows,String id) {
        Integer count = chapterMapper.count(id);
        Integer start = (page - 1) * rows;
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        List<Chapter> all = chapterMapper.findAllPage(start, rows,id);
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", all);
        maps.put("records", count);
        maps.put("page", page);
        maps.put("total", total);
        return maps;
    }

    @Override
    public void add(Chapter chapter) {
        chapterMapper.add(chapter);
    }

    @Override
    public void updatePath(String src,String size,String date,String id) {
        chapterMapper.updatePath(src, size, date, id);
    }

    @Override
    public void update(Chapter chapter) {
        chapterMapper.update(chapter);
    }

    @Override
    public void del(String[] id) {
        chapterMapper.del(id);
    }

}
