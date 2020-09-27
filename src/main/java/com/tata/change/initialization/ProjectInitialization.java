package com.tata.change.initialization;

import com.tata.change.base.demo.Demo;
import com.tata.change.base.mapper.BaseMapper;
import com.tata.change.lucene.resolver.LuceneDemoResolver;
import com.tata.change.menu.demo.Menu;
import com.tata.change.menu.service.MenuService;
import com.tata.change.shiro.demo.Role;
import com.tata.change.mybatis.DataCount;
import com.tata.change.shiro.mapper.TruncateMapper;
import com.tata.change.shiro.mapper.PermissionMapper;
import com.tata.change.shiro.mapper.RoleMapper;
import com.tata.change.shiro.service.PermissionService;
import com.tata.change.shiro.service.RoleService;
import com.tata.change.util.classfile.GetAliasClass;
import com.tata.change.util.shiro.UrlUtilBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//boot start success processes
@Component
public class ProjectInitialization implements ApplicationRunner {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Value("${mybatis.type-aliases-package}")
    String[] dbTableName;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    MenuService menuService;
    @Autowired
    TruncateMapper truncateMapper;
    @Value("${project.status}")
    String dev;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        permissionAndRoleTableInitialization();
    }
    private void luceneInitialization(){
        //创建解析对象
        LuceneDemoResolver resolver = new LuceneDemoResolver();
        //解析包名，获得class
        Class[] aClass = new GetAliasClass().getClass("com.tata.change.*.demo");
        //解析所有class
        for (Class aClass1 : aClass) {
            resolver.resolve(aClass1);
        }
    }
    //检查权限的表是否创建以及表中是否有数据
    private void permissionAndRoleTableInitialization(){
        //先检查表中是否有数据
        Long rc = roleMapper.count();
        Long pc = permissionMapper.count();
        System.out.println("dev============================"+dev);
        try{
            //判断是否需要刷新表
            if((rc==0||pc==0)||dev.equals("dev")) {
                truncateMapper.truncate();
                RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
                //拿到所有url
                HashMap<String, Role> data = new HashMap<>();
                UrlUtilBuilder builder = new UrlUtilBuilder(data);
                handlerMethods.entrySet().forEach(e -> {
                    e.getKey().getPatternsCondition().getPatterns().forEach(u -> {
                        try {
                            builder.urlTransitionShiro(u);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                });
                data.entrySet().forEach(e -> {
                    //添加角色
                    roleService.baseInsert(e.getValue());
                    //添加父菜单 添加后获得menuId
                    Menu menu = new Menu();
                    menu.setMenuName(e.getKey());
                    menuService.baseInsert(menu);
                    List<Integer> integers = new ArrayList<>();
                    e.getValue().getPermissionList().forEach(p -> {
                        //添加权限
                        permissionService.baseInsert(p);
                        integers.add(p.getPermissionId());
                        if(p.getPath()!=null&&!("".equals(p.getPath()))) {
                            //添加子菜单
                            Menu m = new Menu();
                            m.setMenuName(p.getSn());
                            m.setPath(p.getPath());
                            m.setParentId(menu.getMenuId());
                            menuService.baseInsert(m);
                        }
                    });
                    //添加中间表
                    roleService.roleMenuAdd(e.getValue().getRoleId(),menu.getMenuId());
                    roleService.rolePermissionAdd(e.getValue().getRoleId(), integers);

                });
                //写入数据库后返回数据id 缓存

            }
        }catch(Exception e){
            truncateMapper.truncate();
            e.printStackTrace();
        }
    }
    private void dataCountInitialization(){
        //获取实现BaseMapper接口的bean
        ObjectProvider<BaseMapper> provider = applicationContext.getBeanProvider(BaseMapper.class);
        provider.forEach(e->{
            //获取Mapper实现的所有接口
            for (Class<?> aClass : e.getClass().getInterfaces()) {
                //获取接口泛型Type
                for (Type t : aClass.getGenericInterfaces()) {
                    //获取泛型类名
                    String s = t.getTypeName();
                    try {
                        //加载泛型类名的class对象
                        Class<?> demo = Class.forName(s.substring(s.indexOf("<") + 1, s.lastIndexOf(">")));
                        //判断泛型是否继承Demo（规范类）
                        if(Demo.class.isAssignableFrom(demo)){
                            //初始化数据
                            DataCount.InsertCount((Class<? extends Demo>) demo,e.count());
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}

