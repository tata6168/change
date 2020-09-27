package com.tata.change.util.shiro;

import com.tata.change.shiro.demo.Permission;
import com.tata.change.shiro.demo.Role;
import java.util.HashMap;
import java.util.Map;

public class UrlUtilBuilder {
    Map<String,Role> map;

    public UrlUtilBuilder(HashMap<String, Role> data) {
        map=data;
    }

    public void urlTransitionShiro(String u) throws Exception {
        if(u==null|u.length()==0){
            throw new Exception("parameter allUrl not null");
        }
        //url format path/{param}
        if(u.indexOf("{")!=-1){
            u=u.substring(0,u.indexOf("{")-1);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(u);
        //删除头尾 '/'
        if(builder.indexOf("/")==0)
            builder.deleteCharAt(0);
        int i = builder.length() - 1;
        if(builder.lastIndexOf("/")==i)
            builder.deleteCharAt(i);
        //拿出前缀做 roleName
       String prefix = getUrlPrefix(builder);
       Permission permission = new Permission();
       Permission manager = null;
       if(map.containsKey(prefix)){
           permission.setPath(u);
           permission.setSn(replace(builder));
           map.get(prefix).getPermissionList().add(permission);
       }else {
           Role role = new Role();
           //添加管理权限
           if(builder.indexOf("/")!=-1) {
               manager = new Permission();
               manager.setSn(prefix+":*");
               role.getPermissionList().add(manager);
           }
           role.setRoleName(prefix);
           role.getPermissionList().add(permission);
           permission.setPath(u);
           permission.setSn(replace(builder));
           map.put(prefix,role);
       }
       builder.setLength(0);
    }
    //替换 '/'
    private String replace(StringBuilder builder){
        if(builder.indexOf("/")==-1)
            return builder.insert(builder.length(),":*").toString();
        int y = 1;
        while (builder.indexOf("/")!=-1){
            builder.setCharAt(builder.indexOf("/",y),':');
            y++;
        }
        return builder.toString();
    }
    //prefix
    private String getUrlPrefix(StringBuilder builder){
        if(builder.indexOf("/")==-1)
            return builder.toString();
        return builder.substring(0,builder.indexOf("/"));
    }
}
