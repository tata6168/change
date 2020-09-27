package com.tata.change.shiro.service;

import com.tata.change.base.service.Service;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.demo.Role;

import java.util.List;

public interface RoleService extends Service<Role> {
    List<Permission> roleByPermission(Integer roleId);
    void roleDeletePermission(List<Integer> id);
    void deleteRole(Integer roleId);
    void userRoleAdd(Long userId, List<Integer> roleId);


    void rolePermissionAdd(Integer roleId,List<Integer> permissionId);

    void roleMenuAdd(Integer roleId, Integer menuId);
}
