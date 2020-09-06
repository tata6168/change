package com.tata.change.shiro.mapper;

import com.tata.change.ChangeStart;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.util.Query;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ChangeStart.class)
@RunWith(SpringRunner.class)
class PermissionMapperTest {
    @Autowired
    PermissionMapper permissionMapper;
    @Test
    void query() {
        Query<Permission> permissionQuery = new Query<>();
//        permissionQuery.setKeyWords("123");
        permissionQuery.setCurrentPage(0L);
        permissionQuery.setPageSize(10L);
        permissionMapper.query(permissionQuery);
    }
}