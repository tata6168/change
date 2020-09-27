package com.tata.change.menu.mapper;

import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.menu.demo.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> roleByMenu(List<Integer> roleIds);
}
