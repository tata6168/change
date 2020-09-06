package com.tata.change.user.service.impl;

import com.tata.change.base.service.impl.ServiceImpl;
import com.tata.change.user.demo.User;
import com.tata.change.user.mapper.UserMapper;
import com.tata.change.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<User> implements UserService  {
    @Autowired
    UserMapper userMapper;


    @Override
    public void baseInsert(User user) {
        //添加注册用户信息
        userMapper.baseInsert(user);
        //返回用户id
        user.getUserId();
        //添加用户基本角色
    }

}
