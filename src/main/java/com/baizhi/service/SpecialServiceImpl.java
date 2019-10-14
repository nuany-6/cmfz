package com.baizhi.service;

import com.baizhi.entity.Slideshow;
import com.baizhi.entity.Special;
import com.baizhi.mapper.SpecialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Character.getType;

@Transactional
@Service
public class SpecialServiceImpl implements SpecialService {
    @Autowired
    SpecialMapper specialMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Integer count = specialMapper.count();
        Integer stats = (page - 1) * rows;
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        List<Special> all = specialMapper.queryAll(stats, rows);
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", all);
        maps.put("records", count);
        maps.put("page", page);
        maps.put("total", total);
        return maps;
    }

    @Override
    public void add(Special special) {
        specialMapper.add(special);
    }

    @Override
    public void updatePath(String src, String id) {
        specialMapper.updatePath(src, id);
    }

    @Override
    public void update(Special special) {
        specialMapper.update(special);
    }

    @Override
    public void del(String[] id) {
        specialMapper.del(id);
    }

    @Override
    public void delChapter(String[] id) {
        specialMapper.delChapter(id);
    }

    @Override
    public void updateCount(Integer count, String id) {
        specialMapper.updateCount(count, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer selectById(String id) {
        Integer integer = specialMapper.selectById(id);
        return integer;
    }
}
