package com.tata.change.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forward")
public class ForwardPageController {
    @RequestMapping("/main")
    public String main(){
        return "layui/main";
    }
    @RequestMapping("/permstable")
    public String permsTable(){
        return "layui/shiro/permission";
    }
    @RequestMapping("/roletable")
    public String roleTable(){
        return "layui/shiro/role";
    }
}
