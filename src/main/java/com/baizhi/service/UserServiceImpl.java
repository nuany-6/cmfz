package com.baizhi.service;

import com.baizhi.entity.Maps;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Maps> queryAll() {
        List<Maps> list = userMapper.queryAll();

        return list;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Integer> findAll() {
        List<Integer> all = userMapper.findAll();
        return all;
    }
}
