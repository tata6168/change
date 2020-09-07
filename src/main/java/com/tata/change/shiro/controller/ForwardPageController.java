package com.tata.change.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shiro")
public class ForwardPageController {
    @RequestMapping("/permstable")
    public String permsTable(){
        return "/shiro/permission";
    }
    @RequestMapping("/roletable")
    public String roleTable(){
        return "/shiro/role";
    }
}
