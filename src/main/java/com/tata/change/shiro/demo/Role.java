package com.tata.change.shiro.demo;

import com.tata.change.base.demo.Demo;

public class Role extends Demo {
    Integer roleId;
    String roleName;

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
}
