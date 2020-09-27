package com.tata.change.menu.service.impl;

import com.tata.change.base.service.impl.ServiceImpl;
import com.tata.change.menu.demo.Menu;
import com.tata.change.menu.mapper.MenuMapper;
import com.tata.change.menu.service.MenuService;
import com.tata.change.shiro.demo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<Menu> implements MenuService{
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> roleByMenu(List<Integer> roleIds) {
        List<Menu> menus = menuMapper.roleByMenu(roleIds);
        return menus;
    }


}
