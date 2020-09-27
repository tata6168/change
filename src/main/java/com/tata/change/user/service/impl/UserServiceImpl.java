package com.tata.change.user.service.impl;

import com.tata.change.base.service.impl.ServiceImpl;
import com.tata.change.menu.mapper.MenuMapper;
import com.tata.change.shiro.mapper.PermissionMapper;
import com.tata.change.user.demo.User;
import com.tata.change.user.mapper.UserMapper;
import com.tata.change.user.service.UserService;
import com.tata.change.util.shiro.MD5Util;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<User> implements UserService  {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public void baseInsert(User user) {
        user.setPassWord(MD5Util.encryption(user.getPassWord()));
        userMapper.baseInsert(user);
    }

}
