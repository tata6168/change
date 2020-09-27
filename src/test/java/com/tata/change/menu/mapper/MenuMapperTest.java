package com.tata.change.menu.mapper;

import com.tata.change.ChangeStart;
import com.tata.change.menu.demo.Menu;
import com.tata.change.menu.service.MenuService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ChangeStart.class)
@RunWith(SpringRunner.class)
public class MenuMapperTest {
    @Autowired
    MenuService menuService;
    @Autowired
    MenuMapper menuMapper;
    @Test
    public void insert(){
        Menu menu = new Menu();
        menu.setMenuName("testMenu");
        menuService.baseInsert(menu);
    }
    @Test
    public void roleByMenu(){
        List<Integer> id = new ArrayList<>();
        id.add(1);
        id.add(3);
        List<Menu> menus = menuMapper.roleByMenu(id);
        menus.forEach(e->{
            System.out.println("Parent:--------------------");
            System.out.println(e);
        });
    }
}