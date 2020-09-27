package com.tata.change.shiro.controller;

import com.tata.change.user.demo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shiro")
public class ShiroController {
    @PostMapping("/login")
    public String login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassWord());
        //2.如果没有登录，放它登录
        if(!subject.isAuthenticated()){
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                System.out.println("unknownAccount");
                return  "用户名出错";
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                System.out.println("IncorrectCredentials");
                return  "用户名或者密码错误/错误的凭证";
            }catch (AuthenticationException e) {
                System.out.println("神秘错误");
                return  "神秘错误";
            }
        }
        return "success!!!!";
    }
}
