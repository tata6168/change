package com.tata.change.shiro.service.impl;

import com.tata.change.base.service.impl.ServiceImpl;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.demo.Role;
import com.tata.change.shiro.mapper.PermissionMapper;
import com.tata.change.shiro.mapper.RoleMapper;
import com.tata.change.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class RoleServiceImpl extends ServiceImpl<Role> implements RoleService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Permission> roleByPermission(Integer roleId) {
        return roleMapper.roleByPermission(roleId);
    }

    @Override
    public void roleDeletePermission(List<Integer> id) {
        roleMapper.roleDeletePermission(id);
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleMapper.deleteRole(roleId);
    }

    @Override
    public void userRoleAdd(Long userId, List<Integer> roleId) {
        roleMapper.userRoleAdd(userId,roleId);
    }

    @Override
    public void rolePermissionAdd(Integer roleId, List<Integer> permissionId) {
        roleMapper.rolePermissionAdd(roleId,permissionId);
    }

    @Override
    public void roleMenuAdd(Integer roleId, Integer menuId) {
        roleMapper.roleMenuAdd(roleId,menuId);
    }


}
