package com.joe.springbootshiro.mapper;

import com.joe.springbootshiro.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where name = #{name}")
    public User findByName(String name);

    @Select("select * from User where id = #{id}")
    public User findById(Integer id);


}
