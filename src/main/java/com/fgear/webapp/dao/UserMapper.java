package com.fgear.webapp.dao;

import com.fgear.webapp.domain.User;
import com.fgear.webapp.responseType.UserResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findUserById(String userID);
    List<UserResponse> getListUserWithRoleUser();
    User findUserByEmail(String email);
    void createUserByNotFound(User user);

}
