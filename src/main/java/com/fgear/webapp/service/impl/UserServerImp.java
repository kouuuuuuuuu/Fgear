package com.fgear.webapp.service.impl;

import com.fgear.webapp.dao.UserMapper;
import com.fgear.webapp.domain.Role;
import com.fgear.webapp.domain.User;
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
        return null;
    }
}
