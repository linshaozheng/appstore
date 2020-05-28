package com.linsz.demo.dao;

import com.linsz.demo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AyUserDao {
    AyUser findByNameAndPassword(@Param("name")String name,
            @Param("password")String password);

    AyUser findByUserName(@Param("name") String name);
}
