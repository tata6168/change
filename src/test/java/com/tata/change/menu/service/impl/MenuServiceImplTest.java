package com.tata.change.menu.service.impl;

import com.tata.change.menu.demo.Menu;
import com.tata.change.menu.mapper.MenuMapper;
import com.tata.change.menu.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuServiceImplTest {
    @Autowired
    MenuService menuService;
    @Autowired
    MenuMapper menuMapper;
    @Test
    public void roleByMenu() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        menuMapper.roleByMenu(list);
//        menuService.roleByMenu(list).forEach(e->{
//            System.out.println(e);
//        });
    }
}