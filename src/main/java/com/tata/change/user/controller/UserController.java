package com.tata.change.user.controller;

import com.tata.change.user.demo.User;
import com.tata.change.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/register")
    public void register(User user){
        userService.baseInsert(user);
    }
}
