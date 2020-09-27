package com.tata.change.shiro.service;

import com.tata.change.base.service.Service;
import com.tata.change.shiro.demo.Permission;
import com.tata.change.base.demo.Query;
import com.tata.change.util.result.ResultJson;

public interface PermissionService extends Service<Permission> {
    ResultJson data(Query<Permission> query);
}
