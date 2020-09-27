package com.tata.change.menu.controller;

import com.tata.change.menu.demo.Menu;
import com.tata.change.menu.service.MenuService;
import com.tata.change.shiro.util.ShiroUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    //得到菜单
    @PostMapping("/rolebymenu")
    public List<Menu> roleByMenu(){
        //主体内拿到RoleId
        List<Integer> roleId = ShiroUserContext.getUser("").getRoleId();
        return menuService.roleByMenu(roleId);
    }
}
