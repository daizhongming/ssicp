package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.request.RolePrivilegesAdd;
import com.zhilianhua.ssicp.entity.request.UserPrivilegesAdd;
import com.zhilianhua.ssicp.entity.response.PrivilegesVo;

public interface PrivilegeService {
    void addUserPrivileges(UserPrivilegesAdd userPrivilegesAdd);

    PrivilegesVo findUserPrivileges(String userName);

    void deleteUserPrivileges(String userName);

    void deleteRolePrivileges(String roleName);

    PrivilegesVo findRolePrivileges(String roleName);

    void addRolePrivileges(RolePrivilegesAdd rolePrivilegesAdd);
}
