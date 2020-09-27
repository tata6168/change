package com.tata.change.menu.demo;

import com.tata.change.base.demo.Demo;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class Menu extends Demo {
    private Integer menuId;
    private Integer parentId;
    private String menuName;
    private String path;
    private List<Menu> children;
}

