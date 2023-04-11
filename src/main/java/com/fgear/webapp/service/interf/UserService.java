package com.fgear.webapp.service.interf;

import com.fgear.webapp.domain.User;

public interface UserService {
    User findUserByID(String userID);
}
