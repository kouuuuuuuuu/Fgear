package com.fgear.webapp.service.impl;

import com.fgear.webapp.dao.UserMapper;
import com.fgear.webapp.domain.Role;
import com.fgear.webapp.domain.User;
import com.fgear.webapp.responseType.UserResponse;
import com.fgear.webapp.service.interf.RoleService;
import com.fgear.webapp.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServerImp implements UserDetailsService, UserService {
    private final UserMapper userMapper;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserById(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Role userRole = roleService.findRoleName(user.getRoleID());
        authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        return new org.springframework.security.core.userdetails.User(user.getUserID(), user.getPassword(), authorities);
    }

    @Override
    public User findUserByID(String userID) {
        log.debug("find user by ID");
        return userMapper.findUserById(userID);
    }

    @Override
    public List<UserResponse> getListUserWithRoleUser() {
        log.debug("get list user");
        return userMapper.getListUserWithRoleUser();
    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException {
        log.debug("find user by email");
        User user = userMapper.findUserByEmail(email);
        if(user==null){
            log.error("user not found!");
            throw new UsernameNotFoundException("User not found in the database");
        }
        return user;
    }

    @Override
    public void createUserByNotFound(User user) {
        log.debug("create user not found!");
        userMapper.createUserByNotFound(user);
    }
}
