package com.tata.change.shiro.service.impl;

import com.tata.change.base.service.impl.ServiceImpl;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.mapper.PermissionMapper;
import com.tata.change.shiro.service.PermissionService;
import com.tata.change.mybatis.DataCount;
import com.tata.change.base.demo.Query;
import com.tata.change.util.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl extends ServiceImpl<Permission> implements PermissionService  {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public ResultJson data(Query<Permission> query) {
        List<Permission> data = permissionMapper.query(query);
        Long count = DataCount.get(Permission.class);
        return new ResultJson(count,data,"");
    }

}
