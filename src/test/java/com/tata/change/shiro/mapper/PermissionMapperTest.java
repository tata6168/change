package com.tata.change.shiro.mapper;

import com.tata.change.ChangeStart;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.base.demo.Query;
import com.tata.change.user.demo.UserQuery;
import com.tata.change.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ChangeStart.class)
@RunWith(SpringRunner.class)
class PermissionMapperTest {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    UserMapper userMapper;
    @Value("${lucene.directory}")
    String dic;
    @Value("${lucene.path}")
    String path;
    @Test
    void query() {
        System.out.println(dic);
        System.out.println(path);
    }
}