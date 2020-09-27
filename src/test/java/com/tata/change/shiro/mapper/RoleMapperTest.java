package com.tata.change.shiro.mapper;

import com.tata.change.ChangeStart;
import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.mybatis.resolver.MybatisDemoResolver;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.demo.Role;
import com.tata.change.shiro.service.RoleService;
import com.tata.change.user.demo.User;
import com.tata.change.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChangeStart.class)
class RoleMapperTest {
    @Autowired
    MybatisDemoResolver resolver;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    RoleService roleService;

    @Test
    void rr(){
       roleService.baseSearchAll();
        System.out.println("******************************");
        roleService.baseSearchAll();
    }
    @Test
    void roleByPermission() {
        System.out.println(userMapper.count());
    }

    @Test
    void roleDeletePermission() {
        roleMapper.roleMenuAdd(1,2);
    }

    @Test
    void deleteRole() {
        User user = new User();
        user.setName("ssssss");

        userMapper.baseInsert(user);
    }

    @Test
    void userRoleAdd() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
       roleMapper.userRoleAdd(1L,list);
    }
    @Test
    void user(){
        ObjectProvider<BaseMapper> beanProvider = applicationContext.getBeanProvider(BaseMapper.class);
        beanProvider.forEach(e->{
            System.out.println(e);
            for (Class<?> c : e.getClass().getInterfaces()) {
                System.out.println(c.getName());
            }
        });
    }
}