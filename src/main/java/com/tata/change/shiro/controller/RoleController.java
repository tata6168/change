package com.tata.change.shiro.controller;

import com.tata.change.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @RequestMapping("/userRoleAdd")
    public void userRoleAdd(Long userId,List<Integer> roleId){
        roleService.userRoleAdd(userId,roleId);
    }
}
