package com.fgear.webapp.responseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseByRoleUser {
    private List<UserResponse> users;
    private String errorMessage;
}
