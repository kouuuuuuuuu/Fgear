package com.fgear.webapp.dao;

import com.fgear.webapp.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserById(String userID);
}
