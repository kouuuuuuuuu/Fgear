package com.fgear.webapp.dao;

import com.fgear.webapp.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    Role findRoleName(int roleID);
}
