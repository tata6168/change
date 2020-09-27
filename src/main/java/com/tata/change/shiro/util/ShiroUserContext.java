package com.tata.change.shiro.util;

import com.tata.change.user.demo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUserContext {
    public static String sign = "1";
    //添加到session
    public static void put(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        subject.getSession().setAttribute(sign,user);
    }
    //获取user
    public static User getUser(String sign){
        return (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroUserContext.sign);
    }
}
