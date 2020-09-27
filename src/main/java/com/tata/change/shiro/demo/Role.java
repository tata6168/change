package com.tata.change.shiro.demo;

import com.tata.change.base.demo.Demo;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Role extends Demo {
    private Integer roleId;
    private String roleName;
    private List<Permission> permissionList = new ArrayList<>();

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        String ss = "";
        Iterator<Permission> iterator = permissionList.iterator();
        while (iterator.hasNext()){
            ss = ss+iterator.next().toString();
        }
        return  "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", permissionList=" + ss+
                '}';
    }
}
