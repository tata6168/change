package com.tata.change.shiro.mapper;

import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.demo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Permission> roleByPermission(Integer roleId);
    void roleDeletePermission(List<Integer> id);
    void deleteRole(Integer roleId);
    void userRoleAdd(@Param("userId") Long userId,@Param("roleId") List<Integer> roleId);
}
