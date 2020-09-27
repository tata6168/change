package com.tata.change.shiro.demo;

import com.tata.change.base.demo.Demo;
import lombok.ToString;

@ToString
public class Permission extends Demo {
    private Integer permissionId;
    private String permissionName;
    private String path;
    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
