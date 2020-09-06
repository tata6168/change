package com.tata.change.user.mapper;

import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.user.demo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    //根据用户名查询
    User verifyName(String username);

    //用户获取权限
    List<String> userIdRetrievePermission(Long userId);
}
