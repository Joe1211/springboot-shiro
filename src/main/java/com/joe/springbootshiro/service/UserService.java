package com.joe.springbootshiro.service;

import com.joe.springbootshiro.dao.User;
import com.joe.springbootshiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUser(String name){
        return userMapper.findByName(name);
    }

    public User getUserId(Integer id){
        return userMapper.findById(id);
    }

}
