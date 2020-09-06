package com.tata.change.shiro.controller;

import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.mapper.PermissionMapper;
import com.tata.change.shiro.service.PermissionService;
import com.tata.change.util.Query;
import com.tata.change.util.result.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController("/permission")
public class PermissionController {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionService permissionService;

    @PostMapping("/data")
    public ResultJson data(Query<Permission> query){
        return permissionService.data(query);
    }
}
