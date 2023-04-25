package com.fgear.webapp.service.interf;

import com.fgear.webapp.domain.User;
import com.fgear.webapp.responseType.UserResponse;

import java.util.List;

public interface UserService {
    User findUserByID(String userID);
    List<UserResponse> getListUserWithRoleUser();
    User findUserByEmail(String email);
    void createUserByNotFound(User user);
}
