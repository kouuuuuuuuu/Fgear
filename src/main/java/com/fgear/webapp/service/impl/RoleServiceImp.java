package com.fgear.webapp.service.impl;

import com.fgear.webapp.dao.RoleMapper;
import com.fgear.webapp.domain.Role;
import com.fgear.webapp.service.interf.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImp implements RoleService {
    private  final RoleMapper roleMapper;

    @Override
    public Role findRoleName(int roleID) {
        return roleMapper.findRoleName(roleID);
    }
}
