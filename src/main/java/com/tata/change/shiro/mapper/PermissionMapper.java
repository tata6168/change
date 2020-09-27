package com.tata.change.shiro.mapper;

import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.shiro.demo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> roleByPermission(List<Integer> roleId);
}
