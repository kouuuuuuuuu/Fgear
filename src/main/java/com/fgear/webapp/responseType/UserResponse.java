package com.fgear.webapp.responseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userID;
    private String userName;
    private String phone;
    private String email;
    private String facebook;
    private String image;
    private int status;
    private int addressID;
}
