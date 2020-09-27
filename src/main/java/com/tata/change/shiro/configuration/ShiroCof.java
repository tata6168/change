package com.tata.change.shiro.configuration;

import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.mapper.PermissionMapper;
import com.tata.change.shiro.mapper.RoleMapper;
import com.tata.change.shiro.service.PermissionService;
import com.tata.change.user.mapper.UserMapper;
import com.tata.change.util.shiro.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.apache.shiro.mgt.SecurityManager;

import java.util.HashMap;
import java.util.List;

import com.tata.change.user.demo.User;
@Configuration
public class ShiroCof {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionService permissionService;
    @Lazy
    @Autowired
    @Qualifier("webSecurityManager")
    DefaultWebSecurityManager securityManager;
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //algorithm:算法
        //iterations:迭代次数/重复
        //Stored Credentials Hex Encoded：存储凭证十六进制编码
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(MD5Util.ENCRYPTIONNUM);
        //matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }
    @Bean("AuthorizingRealm")
    public Realm getRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher matcher){
        return new AuthorizingRealm() {
            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                List<Integer> integers = roleMapper.userByRoleId(user.getUserId());
                List<Permission> permissions = permissionMapper.roleByPermission(integers);
                user.setRoleId(integers);
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                permissions.forEach(e->info.addStringPermission(e.getSn()));
                return info;
            }
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
                UsernamePasswordToken token1 = (UsernamePasswordToken) token;
                String username = token1.getUsername();
                User user = userMapper.verifyName(username);
                if(user==null)
                    return null;
                ByteSource salt = ByteSource.Util.bytes(MD5Util.SALT);
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassWord(),salt,"AuthorizingRealm");
                return info;
            }
            @Override
            public String getName() {
                return "AuthorizingRealm";
            }
            @Override
            public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
                super.setCredentialsMatcher(matcher);
            }
        };
    }
    @Bean("webSecurityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("AuthorizingRealm")Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager( securityManager);

        factoryBean.setSuccessUrl("/success");
        factoryBean.setLoginUrl("/login");
        HashMap<String, String> map = new HashMap<>();
        //map.put("/**","anon");
        List<Permission> permissions = permissionService.baseSearchAll();
        permissions.forEach(e->{
            if(e.getPath()!=null&&e.getPath()!="")
            map.put(e.getPath(),"perms["+e.getSn()+"]");
        });


        //role
        map.put("/test/result2","anon");
        map.put("/test/result","anon");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }
}
