package com.tata.change.menu.service;

import com.tata.change.base.service.Service;
import com.tata.change.menu.demo.Menu;

import java.util.List;

public interface MenuService extends Service<Menu> {
    List<Menu> roleByMenu(List<Integer> roleIds);
}
